package com.meiyukai.dto;

import lombok.Data;

/**
 * 定义购物车对象
 */
@Data
public class CartDTO {

    private String productId;   // 商品的id

    private Integer productQuantity; // 商品的数量

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
