package com.meiyukai.exception;

import com.meiyukai.enums.ResultEnum;

/**
 *异常
 */
public class SellException  extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }



}
