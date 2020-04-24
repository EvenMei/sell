package com.meiyukai.utils;

import com.meiyukai.enums.TransactionStatusEnum;

public class TransactionStatusUtils {
    public static TransactionStatusEnum getTransactionStatusEnum(Integer payStatus , Integer orderStatus) {
               //待付款
        if (payStatus.equals(0) && orderStatus.equals(0)) {
            return TransactionStatusEnum.TRANSACTION_WAIT_TO_PAY;
        }
        //待发货
        if (payStatus.equals(1) && orderStatus.equals(0)) {
            return TransactionStatusEnum.TRANSACTION_WAIT_TO_DELIVER;
        }
        //待收货(已经发货)
        if (payStatus.equals(1) && orderStatus.equals(1)) {
            return TransactionStatusEnum.TRANSACTION_WAIT_TO_RECEIVE;
        }

        //交易成功
        if (payStatus.equals(1) && orderStatus.equals(3)) {
            return TransactionStatusEnum.TRANSACTION_SUCCESS;
        }

        //交易取消

        if (orderStatus.equals(2)) {
            return TransactionStatusEnum.TRANSACTION_CANCEL;
        } else{
            return null;
        }

    }



}
