package com.meiyukai.service.impl;

import com.meiyukai.domain.ProductInfo;
import com.meiyukai.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Resource(name = "productInfoService")
    private ProductInfoService productInfoService;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productInfoService.findUpAll();
        for (ProductInfo  productInfo :  list){
            System.out.println("productInfo :  -------  "  + productInfo);
        }
    }

    @Test
    public void findAllByPage() {
        PageRequest pageable = new PageRequest(0,1);
//        Pageable request = PageRequest.of(0,1);   // 上面的方法过期了， 可以使用这个方法代替
        Page<ProductInfo> list = productInfoService.findAllByPage(pageable);
        System.out.println("list  :  " + list.getContent());

    }

    @Test
    public void findProductInfoById() {
        ProductInfo productInfo = productInfoService.findProductInfoById("product_id");
        System.out.println("product ---  ---- " +productInfo);
    }

    @Test
    public void saveProductInfo() {

        ProductInfo pi = new ProductInfo();
        pi.setProductId("002");
        pi.setCategoryType(1);
        pi.setProductDescription("很好吃的皮蛋瘦肉粥");
        pi.setProductIcon("http://XXX.jpeg");
        pi.setProductPrice(new BigDecimal(20.0));
        pi.setProductStock(88);
        pi.setProductStatus(0);
        pi.setProductName("皮蛋瘦肉粥");
        productInfoService.saveProductInfo(pi);

    }


}