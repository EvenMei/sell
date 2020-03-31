package com.meiyukai.service;

import com.meiyukai.domain.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryService {

    /**
     * 查询所有商品种类信息
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

    /**
     * 根据 productCategoryType 查找productCategory
     */

    ProductCategory  findProductCategoryByProductCategoryType(Integer productCategoryType);


    /**
     * 分页查询类目
     */

    Page<ProductCategory> findAll(Pageable pageable);

    /**
     * 获得IID集合
     */

    public  List<Integer> getCategoryIdList();








}
