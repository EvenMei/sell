package com.meiyukai.enums;


import lombok.Getter;

@Getter
public enum TransactionStatusEnum {
    TRANSACTION_WAIT_TO_PAY(001 , "待付款"),
    TRANSACTION_WAIT_TO_DELIVER(002, "待发货"),
    TRANSACTION_WAIT_TO_RECEIVE(003 , "已发货"),
    TRANSACTION_SUCCESS(004 , "交易成功"),
    TRANSACTION_CANCEL(005,"交易取消");
    ;
    private String msg;
    private Integer code;

    TransactionStatusEnum(Integer code , String msg ) {
        this.msg = msg;
        this.code = code;
    }

}
