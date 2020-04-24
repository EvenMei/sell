package com.meiyukai.dao;

import com.meiyukai.domain.Express;
import com.meiyukai.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExpressRepositoryTest {

    @Resource(name = "expressRepository")
    private ExpressRepository expressRepository;

    @Test
    @Transactional
    @Rollback(value =false)
    public void addTest(){
        Express express = new Express();
        express.setId(KeyUtil.getUniqueKey());
        express.setOrderId("1122334455");
        express.setExpressName("顺丰快递");
        express.setExpressNumber("SF100100110");
        expressRepository.save(express);
    }





}