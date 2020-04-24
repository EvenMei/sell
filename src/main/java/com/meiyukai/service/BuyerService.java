package com.meiyukai.service;

import com.meiyukai.domain.OrderMaster;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.dto.OrderStatusDetail;

import java.util.List;

/**
 *
 */
public interface BuyerService {

     // 查询一个订单
    OrderDTO findOrderOne (String openid , String orderId);

    // 取消订单
    OrderDTO cancelOrder(String openid , String orderId);

    //根据openid 查询所有的订单

    OrderStatusDetail find(List<OrderMaster> orderMasterList);

    OrderStatusDetail find2(List<OrderDTO> orderDTOList);

    List<OrderDTO> findAllWait2Pay(String openid );

    List<OrderDTO> findAllWait2Deliver(String openid);

    List<OrderDTO> findAllWait2Receive(String openid);








}
