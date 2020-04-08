package com.meiyukai.dao;

import com.meiyukai.domain.SellerInfo;
import com.meiyukai.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Resource(name = "sellerInfoRepository")
    private SellerInfoRepository sellerInfoRepository;


    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setPassword("meimei");
        sellerInfo.setOpenid("ooo");
        sellerInfo.setUsername("meimei");
        sellerInfoRepository.save(sellerInfo);
    }


    @Test
    public void findByOpenidTest(){
        String openid = "openid_meimei";
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(openid);
        System.out.println("sellerInfo : " + sellerInfo);
    }

}
