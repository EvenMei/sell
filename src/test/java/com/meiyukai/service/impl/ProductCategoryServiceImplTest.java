package com.meiyukai.service.impl;
import com.meiyukai.domain.ProductCategory;
import com.meiyukai.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;

    @Test
    public void testFindAll(){
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        for (ProductCategory productCategory : productCategoryList){
            System.out.println("productCategory :  "  +  productCategory );
        }
    }

    @Test
    public  void testFindOne(){
        ProductCategory productCategory  =  productCategoryService.findProductCategoryById(1);
        System.out.println("productCategory :   " + productCategory );
    }


    @Test
    public void testFindByProductCategoryType(){
        List list = new ArrayList<Integer>();
        list.add(1);
        list.add(4);
        List <ProductCategory>  categoryList = productCategoryService.findByCategoryTypeIn(list);
        for (ProductCategory  productCategory :  categoryList){
            System.out.println("productCategory  :    === === " + productCategory);
        }
    }


    @Test
    public void  testSaveProductCategory(){
        ProductCategory pc = new ProductCategory();
        pc.setCategoryType(11);
        pc.setCategoryName("temp_categoryType  ");
        productCategoryService.save(pc);
    }


    @Test
    public void testDeleteProductCategoryById(){
        productCategoryService.deleteProductCategoryById(5);
    }



}