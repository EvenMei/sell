package com.meiyukai.service.impl;

import com.meiyukai.dao.OrderMasterRepository;
import com.meiyukai.domain.OrderMaster;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.dto.OrderStatusDetail;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.service.BuyerService;
import com.meiyukai.service.OrderService;
import com.meiyukai.utils.OrderMaster2OrderDTOUtils;
import com.meiyukai.utils.OrderStatusDetailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "buyerService")
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource(name = "orderMasterRepository")
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderMaster2OrderDTOUtils orderMaster2OrderDTOUtils;



    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }


    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO =  checkOrderOwner(openid , orderId);
        if (orderDTO == null){
            log.error("【取消订单】订单不能为空  openid = {} , orderId = {}"  , openid , orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }
        return orderService.cancel(orderDTO);
    }


    /**
     * 查询 待付款 待发货 待收货 全部订单的信息
     * @param orderMasterList
     * @return
     */
    @Override
    public OrderStatusDetail find(List<OrderMaster> orderMasterList) {
        return OrderStatusDetailUtils.getOrderStatusDetail(orderMasterList);
    }


    @Override
    public OrderStatusDetail find2(List<OrderDTO> orderDTOList) {
        return OrderStatusDetailUtils.getOrderStatusDetails(orderDTOList);
    }

    @Override
    public List<OrderDTO> findAllWait2Pay(String openid) {
        List<OrderMaster> orderMasterList = orderMasterRepository.findAllByBuyerOpenidAndPayStatusAndOrderStatus(openid, 0, 0);
        return orderMaster2OrderDTOUtils.convert(orderMasterList);
    }

    @Override
    public List<OrderDTO> findAllWait2Deliver(String openid) {
        List<OrderMaster> orderMasterList  =  orderMasterRepository.findAllByBuyerOpenidAndPayStatusAndOrderStatus(openid , 1,0);
        return orderMaster2OrderDTOUtils.convert(orderMasterList);
    }

    @Override
    public List<OrderDTO> findAllWait2Receive(String openid) {
        List<OrderMaster> orderMasterList = orderMasterRepository.findAllByBuyerOpenidAndPayStatusAndOrderStatus(openid ,1,1);
        return orderMaster2OrderDTOUtils.convert(orderMasterList);
    }


    private OrderDTO checkOrderOwner(String openid , String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO==null){
            return null;
        }
        //判断是否属于自己的订单
        if (!orderDTO.getBuyerOpenid().equals(openid)){
            log.error("【订单查询】openid 不一致 currentOpenid={}   requiredOpenid={}" , openid , orderDTO.getBuyerOpenid());
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }




}
