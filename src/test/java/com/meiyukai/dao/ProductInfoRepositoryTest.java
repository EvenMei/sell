package com.meiyukai.dao;

import com.meiyukai.domain.ProductInfo;
import com.meiyukai.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Resource(name = "productInfoRepository")
    private ProductInfoRepository productInfoRepository;

    @Test
    public void testFindAll(){
        List<ProductInfo> productInfos =  productInfoRepository.findAll();
        for(ProductInfo productInfo :  productInfos){
            System.out.println("productInfo :  " + productInfo);
        }
    }


    @Test
    public void testSaveProductInfo(){
        ProductInfo pi = new ProductInfo();
        pi.setProductId("product_id");
        pi.setCategoryType(1);
        pi.setProductDescription("product_description");
        pi.setProductIcon("product_icon");
        pi.setProductPrice(new BigDecimal(20.0));
        pi.setProductStock(88);
        pi.setProductStatus(0);
        pi.setProductName("product_name");
        productInfoRepository.save(pi);
    }

    @Test
    public void findByProductStatusTest(){
        List<ProductInfo> productInfoStatus = productInfoRepository.findByProductStatus(0);
        System.out.println("-------- -------statusSize  :   -------- -------" +productInfoStatus.size() );
    }

    @Test
    public void  findByProductStatusAndAndCategoryTypeTest(){
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatusAndAndCategoryType(0,1);
        System.out.println("productInfoList: " + JsonUtil.toJson(productInfoList));
    }


}