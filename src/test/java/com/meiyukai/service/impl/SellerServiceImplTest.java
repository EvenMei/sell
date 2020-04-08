package com.meiyukai.service.impl;

import com.meiyukai.domain.SellerInfo;
import com.meiyukai.service.SellerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    @Resource(name = "sellerService")
    private SellerService sellerService;

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerService.findByOpenid("openid_meimei");
        System.out.println("sellerInfo :  " + sellerInfo);

    }

    @Test
    public void findByUsername(){
        System.out.println("userInfo : " + sellerService.findByUsername("meimei"));
    }
}