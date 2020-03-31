package com.meiyukai.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product_category")
@DynamicUpdate
@Data
public class ProductCategory implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自动增长类型
    @Id
    @Column(name = "category_id")
    private Integer categoryId; // 类目ID

    @Column(name = "category_name")
    private String categoryName; // 类目名字

    @Column(name = "category_type")
    private Integer categoryType ;  // 类目类型

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;





}
