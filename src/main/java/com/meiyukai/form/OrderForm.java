package com.meiyukai.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meiyukai.dto.CartDTO;
import lombok.Data;

import javax.validation.MessageInterpolator;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 进行表单验证
 */
@Data
public class OrderForm implements Serializable {
    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "电话必填")
    private String phone;

    @NotEmpty(message = "地址必填写")
    private String address;

    @NotEmpty(message = "微信号必填写")
    private String openid;

    @NotEmpty(message = "购物车必填写")
    private String items;



}
