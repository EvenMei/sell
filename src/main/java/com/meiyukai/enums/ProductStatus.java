package com.meiyukai.enums;

import lombok.Getter;

/**
 * 商品的状态
 */

@Getter
public enum ProductStatus {


    UP(0,"在架") ,
    DOWN(1,"下架");

    private Integer code;
    private String message;


    ProductStatus(Integer code  , String message) {
        this.code  =  code;
        this.message  = message;
    }


}
