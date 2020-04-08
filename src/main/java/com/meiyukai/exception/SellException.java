package com.meiyukai.exception;

import com.meiyukai.enums.ResultEnum;
import lombok.Data;

/**
 *异常
 */
@Data
public class SellException  extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code , String message) {
        super(message);
        this.code = code;
    }

}
