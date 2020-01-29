package com.meiyukai.service;

import com.meiyukai.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    /**
     * 查询所有的产品
     * @return
     */
    public List<ProductCategory> findAll();


    /**
     * 查询一条产品的记录
     */

    ProductCategory findProductCategoryById(Integer categoryId);

    /**
     * 根据productCategoryType 查询所有的产品信息
     */

    List<ProductCategory> findByCategoryTypeIn(List<Integer> productCategoryTypeList);

    /**
     * 保存productCategory
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);


    /**
     * 删除一个 ProductCategory
     */

    public void deleteProductCategoryById(Integer categoryId);









}