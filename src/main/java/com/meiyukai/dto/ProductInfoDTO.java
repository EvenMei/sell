package com.meiyukai.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class ProductInfoDTO {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String  productDescription;

    private String productIcon;

    private Integer categoryType;

    private Integer productStatus;

    private Date createTime;

    private Date updateTime;

    private String categoryName;


}
