package com.meiyukai.exception;

import lombok.Data;

@Data
public class MyException extends RuntimeException {

    private String msg;

    private Integer code;

    public MyException(String msg, Integer code) {
       super(msg);
        this.code = code;
    }

    public MyException(String message, String msg, Integer code) {
        super(message);
        this.msg = msg;
        this.code = code;
    }



}
