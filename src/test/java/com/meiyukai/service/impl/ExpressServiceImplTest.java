package com.meiyukai.service.impl;

import com.meiyukai.service.ExpressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExpressServiceImplTest {

    @Resource(name = "expressService")
    private ExpressService expressService;

    @Test
    public void findAll() {
    }

    @Test
    public void findExpressById() {
    }

    @Test
    public void findByOrderId() {

    }

    @Test
    @Transactional
    @Rollback(value =true)
    public void addNewExpress() {
        expressService.addNewExpress("000001111" , "顺丰快递"  , "SF10010110");
    }




}