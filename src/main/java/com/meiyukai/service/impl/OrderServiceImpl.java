package com.meiyukai.service.impl;

import com.lly835.bestpay.model.RefundResponse;
import com.meiyukai.converter.OrderMaster2OrderDTOConverter;
import com.meiyukai.dao.OrderDetailRepository;
import com.meiyukai.dao.OrderMasterRepository;
import com.meiyukai.domain.OrderDetail;
import com.meiyukai.domain.OrderMaster;
import com.meiyukai.domain.ProductInfo;
import com.meiyukai.dto.CartDTO;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.enums.OrderStatusEnum;
import com.meiyukai.enums.PayStatusEnum;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.service.OrderService;
import com.meiyukai.service.PayService;
import com.meiyukai.service.ProductCategoryService;
import com.meiyukai.service.ProductInfoService;
import com.meiyukai.utils.JsonUtil;
import com.meiyukai.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "orderService")
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource(name = "productInfoService")
    private ProductInfoService productInfoService;
    
    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;

    @Resource(name = "orderDetailRepository")
    private OrderDetailRepository orderDetailRepository;

    @Resource(name = "orderMasterRepository")
    private OrderMasterRepository orderMasterRepository;

    @Resource(name = "payService")
    private PayService payService;


            
            

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);
//        ArrayList<CartDTO> cartDTOList = new ArrayList<>();


        // 1. 查询商品的数量 价格
        for(OrderDetail orderDetail  :  orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findProductInfoById(orderDetail.getProductId());

            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }


            // 2. 计算总价
            BeanUtils.copyProperties(productInfo , orderDetail);
            orderAmount = orderAmount.add(orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));


            // 订单详情入库

            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetailRepository.save(orderDetail);


            /*CartDTO cartDTO =  new CartDTO(orderDetail.getProductId() ,  orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);*/
        }



        // 3. 写入订单数据库(orderMaster  和  orderDetail)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO ,  orderMaster);
        System.out.println("orderMaster ======= >  " + orderMaster);
        orderMasterRepository.save(orderMaster);



        //4. 扣除库存
        List<CartDTO> cartDTOList  = orderDTO.getOrderDetailList().stream().map(e-> new CartDTO(e.getProductId() , e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }


    @Override
    public OrderDTO findOne(String orderID) {
        OrderDTO orderDTO =  new OrderDTO();
        OrderMaster orderMaster = orderMasterRepository.findById(orderID).get();
        if(orderMaster == null){
            log.error("【订单不存在】orderId = {}" , orderID);
            throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderID);

        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXISTS);
        }
        BeanUtils.copyProperties(orderMaster , orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findOrderList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList, pageable , orderMasterPage.getTotalElements() );
    }

    @Override
    public Page<OrderDTO> findAllOrderList(Pageable pageable) {
        Page<OrderMaster> orderList = orderMasterRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderList.getContent());
        Page page = new PageImpl(orderDTOList , pageable , orderList.getTotalElements());
         return  page;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断一下订单的状态 : 只有新订单才可以取消
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.info("【取消订单】订单状态不正确  orderId = {} , orderStatus={}  " ,orderDTO.getOrderId() , orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO , orderMaster);
        OrderMaster orderMasterCanceled = orderMasterRepository.save(orderMaster);  // 更新订单状态
        System.out.println("--- 【orderMasterCanceled  :  】--- " + orderMasterCanceled);  //

        if(orderMasterCanceled == null){
            log.error("【取消订单】 更新失败   orderMaster={}  " , orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }

        // 返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.info("【取消订单】 返回库存失败 orderDetailList = {}" , orderDTO.getOrderDetailList());
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        // 如果已经支付 就退款
        if(orderDTO.getPayStatus().equals( PayStatusEnum.SUCCESS.getCode())){
            //TODO  退款
            System.out.println("OrderId :  " +orderDTO.getOrderId()  +  "      " +"--- ---  此处应当退款给 ： " +orderDTO.getBuyerName());
            payService.refund(orderDTO);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】 订单状态错误  orderDTO={}" , orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }


        //修改订单状态
        OrderMaster orderMaster =  new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO , orderMaster);
        OrderMaster orderMasterUpdate = orderMasterRepository.save(orderMaster);
        if (orderMasterUpdate == null){
            log.error("【完结订单】更新失败  orderMaster={}" , orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {

        // 判断订单的状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【支付】 订单状态错误  orderDTO={} " , orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付的状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【支付】支付状态错误 orderDTO = {} " , orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付的状态
        //TODO 支付订单
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO , orderMaster);
        OrderMaster paidOrderMaster = orderMasterRepository.save(orderMaster);
        if(paidOrderMaster == null){
            log.error("【支付】 订单支付失败  orderDTO={} " , orderDTO);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    @Override
    public List<String> getOrderName(OrderDTO orderDTO) {
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        List<String> orderName = orderDetailList.stream().map(e -> e.getProductName()).collect(Collectors.toList());
        return orderName;
    }

}
