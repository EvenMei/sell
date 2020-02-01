package com.meiyukai.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product_info")
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "product_stock")
    private Integer productStock;

    @Column(name = "product_description")
    private String  productDescription;

    @Column(name = "product_icon")
    private String productIcon;

    @Column(name = "category_type")
    private Integer categoryType;

    @Column(name = "product_status")
    private Integer productStatus;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;


}
