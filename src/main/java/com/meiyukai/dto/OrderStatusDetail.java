package com.meiyukai.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderStatusDetail implements Serializable {

    private Integer waitToPay = 0; //代支付
    private Integer waitToDeliver = 0; // 待发货
    private Integer waitToReceive = 0;  // 待收货
    private Integer totalAmount = 0;  //全部订单数
}
