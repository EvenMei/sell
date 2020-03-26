package com.meiyukai.service.impl;

import com.meiyukai.dao.ProductCategoryRepository;
import com.meiyukai.domain.ProductCategory;
import com.meiyukai.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {


    @Resource(name = "productCategoryRepository")
    private ProductCategoryRepository productCategoryRepository;


    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory findProductCategoryById(Integer categoryId) {
        return productCategoryRepository.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> productCategoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(productCategoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public void deleteProductCategoryById(Integer categoryId) {
        productCategoryRepository.deleteById(categoryId);
    }

    @Override
    public ProductCategory findProductCategoryByProductCategoryType(Integer productCategoryType) {
        return productCategoryRepository.findByCategoryType(productCategoryType);
    }


}
