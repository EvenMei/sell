package com.meiyukai.dao;

import com.meiyukai.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "productCategoryRepository")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory , Integer> , JpaSpecificationExecutor<ProductCategory> {

    /**
     * 根据 类目查询产品的类型
     * @param categoryTypeList
     * @return
     */

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory findByCategoryType(Integer categoryType);

    @Query(value = "select category_id from product_category" , nativeQuery = true)
    List<Integer> findCategoryIdList();



}
