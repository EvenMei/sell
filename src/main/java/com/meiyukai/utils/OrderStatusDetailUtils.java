package com.meiyukai.utils;

import com.meiyukai.domain.OrderMaster;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.dto.OrderStatusDetail;

import java.util.List;

public class OrderStatusDetailUtils {

    public static OrderStatusDetail getOrderStatusDetail(List<OrderMaster> orderMasterList){
        OrderStatusDetail orderStatusDetail = new OrderStatusDetail();
        orderStatusDetail.setTotalAmount(orderMasterList.size());
        for (OrderMaster orderMaster : orderMasterList){
            Integer payStatus  = orderMaster.getPayStatus();
            Integer orderStatus = orderMaster.getOrderStatus();
            //待付款
            if (payStatus.equals(0) && orderStatus.equals(0)){
                orderStatusDetail.setWaitToPay(orderStatusDetail.getWaitToPay()+1);
            }
            //待发货
            if(payStatus.equals(1) && orderStatus.equals(0)){
                orderStatusDetail.setWaitToDeliver(orderStatusDetail.getWaitToDeliver()+1);
            }
            //待收货
            if (payStatus.equals(1) && orderStatus.equals(1)){
                orderStatusDetail.setWaitToReceive(orderStatusDetail.getWaitToReceive()+1);
            }
        }
        return orderStatusDetail;
    }



    public static OrderStatusDetail getOrderStatusDetails(List<OrderDTO> orderDTOList){
        OrderStatusDetail orderStatusDetail = new OrderStatusDetail();
        orderStatusDetail.setTotalAmount(orderDTOList.size());
        for (OrderDTO orderDTO : orderDTOList){
            Integer payStatus  = orderDTO.getPayStatus();
            Integer orderStatus = orderDTO.getOrderStatus();
            //待付款
            if (payStatus.equals(0) && orderStatus.equals(0)){
                orderStatusDetail.setWaitToPay(orderStatusDetail.getWaitToPay()+1);
            }
            //待发货
            if(payStatus.equals(1) && orderStatus.equals(0)){
                orderStatusDetail.setWaitToDeliver(orderStatusDetail.getWaitToDeliver()+1);
            }
            //待收货
            if (payStatus.equals(1) && orderStatus.equals(1)){
                orderStatusDetail.setWaitToReceive(orderStatusDetail.getWaitToReceive()+1);
            }
        }
        return orderStatusDetail;
    }



}
