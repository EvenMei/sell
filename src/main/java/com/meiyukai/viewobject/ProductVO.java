package com.meiyukai.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meiyukai.domain.ProductInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品包含类目
 */
@Data
public class ProductVO implements Serializable {

    @JsonProperty(value = "name")  // 将对象序列化返回给前端 就是 name
    private String categoryName; //  类目的名字

    @JsonProperty(value = "type")
    private Integer categoryType;

    @JsonProperty(value = "foods")
    private List<ProductInfoVO>  productInfoVOList;




}
