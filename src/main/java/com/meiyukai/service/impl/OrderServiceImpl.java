package com.meiyukai.service.impl;

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
import com.meiyukai.service.*;
import com.meiyukai.utils.KeyUtil;
import com.meiyukai.utils.OrderMaster2OrderDTOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

    @Resource(name = "pushMessage")
    private PushMessageService pushMessageService;

    @Autowired
    private WebSocket webSocket;

    @Autowired
    private OrderMaster2OrderDTOUtils orderMaster2OrderDTOUtils;

            
            

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {

        String orderId = KeyUtil.getUniqueKey();
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
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
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

        //调用 webSocket 发送消息

       // webSocket.onMessage("您有一个新的订单");
        webSocket.sendMessage(orderDTO.getOrderId());

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
    public List<OrderDTO> findAllByOpenid(String openid) {
        List<OrderMaster> orderMasterList = orderMasterRepository.findAllByBuyerOpenid(openid);
        List<OrderDTO> orderDTOList = orderMaster2OrderDTOUtils.convert(orderMasterList);
        return orderDTOList;
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

        //微信模板消息推送
        pushMessageService.buyerCancel(orderDTO);  // 买家自行退单

        pushMessageService.sellerCancel(orderDTO);  // 通知商家有客户退单

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        // 判断订单状态
        if ( (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()) )  || (!orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) ){
            log.error("【完结订单】 订单状态错误  orderDTO={}" , orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }


        //修改订单状态
        OrderMaster orderMaster =  new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.DELIVERED.getCode());
        BeanUtils.copyProperties(orderDTO , orderMaster);
        OrderMaster orderMasterUpdate = orderMasterRepository.save(orderMaster);
        if (orderMasterUpdate == null){
            log.error("【订单发货】更新失败  orderMaster={}" , orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }

        //推送微信模板消息
        pushMessageService.orderDeliver(orderDTO);
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

        //推送微信模板消息
        pushMessageService.paid(orderDTO); // 通知客户成功下单
        pushMessageService.newOrder(orderDTO); // 通知商家有新的订单
        return orderDTO;
    }

    @Override
    public List<String> getOrderName(OrderDTO orderDTO) {
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        List<String> orderName = orderDetailList.stream().map(e -> e.getProductName()).collect(Collectors.toList());
        return orderName;
    }

    @Override
    public List<OrderMaster> findAll(String openid) {
        return orderMasterRepository.findAllByBuyerOpenid(openid);
    }

    /**
     * 根据 openid 和 订单号删除订单
     * @param orderId 订单号
     * @param openid
     */
    @Override
    public void delete(String orderId, String openid) {
       OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
       //先判断是否是自己的订单
       if (orderMaster.getBuyerOpenid().equals(openid)){
           List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
           try{
               //先删除从订单
               for (OrderDetail orderDetail : orderDetailList){
                   orderDetailRepository.deleteByOrderId(orderDetail.getOrderId());
               }
               //再删除主订单
               orderMasterRepository.deleteByOrderId(orderId);
           }catch(Exception e){
               log.error("【删除订单】 e= {}", e.getMessage());
           }
       }
    }




}
