package com.meiyukai.enums;

import lombok.Getter;

/**
 * 返回给前端提示的枚举类型
 */

@Getter
public enum  ResultEnum {

    PARAM_ERROR(1,"参数错误"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ORDER_NOT_EXISTS(12,"订单不存在"),
    ORDERDETAIL_NOT_EXISTS(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态错误"),
    ORDER_UPDATE_ERROR(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"支付状态错误"),
    CART_EMPTY(18,"购物车不能为空"),
    ORDER_OWNER_ERROR(19,"openid 不一致"),
    WECHAT_MP_ERROR(20,"微信公众号错误"),
    WECHAT_NOTIFY_MONEY_VARIFY_ERROR(21,"微信异步通知校验金额不通过"),
    ORDER_NAME_EMPTY(22 , "订单名称为空"),
    DELETE_ICON_ERROR(23,"图片删除失败！") ,
    CATEGORY_TYPE_ERROR(24  , "类目状态错误，需要正整数"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
