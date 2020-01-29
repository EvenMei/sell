package com.meiyukai.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

   /* @Column(name = "create_time")
     private Date createTime ; //  创建时间

    @Column(name = "update_time")
     private Date updateTime ; // 更新时间
*/



   /* public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }*/


    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType='" + categoryType + '\'' +
                '}';
    }
}
