package com.meiyukai.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *  商品详情
 */
@Data
public class ProductInfoVO implements Serializable {


    @JsonProperty(value = "id")
    private String productId;

    @JsonProperty(value = "name")
    private String productName;

    @JsonProperty(value = "price")
    private BigDecimal productPrice;

    @JsonProperty(value = "description")
    private String productDescription;

    @JsonProperty(value = "icon")
    private String productIcon;






}
