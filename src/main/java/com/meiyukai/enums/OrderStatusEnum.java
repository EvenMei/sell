package com.meiyukai.enums;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum<Integer> {

    NEW(0,"新订单"),
    DELIVERED(1,"已发货"),
    CANCEL(2,"已取消"),
    RECEIVED(3,"已收货"),
    ;
    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }





}
