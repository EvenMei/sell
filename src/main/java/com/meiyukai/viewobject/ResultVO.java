package com.meiyukai.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * http 返回的最外层对象
 */
@Data
public class ResultVO<T> implements Serializable {

    @JsonProperty(value = "code")
    private Integer code; // 错误码 ， 0 成功

    @JsonProperty(value = "msg")
    private String msg; // 消息 ， 提示信息

    @JsonProperty(value = "data")
    private T data; // 返回的具体内容




}
