package com.meiyukai.service.impl;

import com.meiyukai.domain.SFExpress;
import com.meiyukai.service.SFExpressService;
import com.meiyukai.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SFExpressServiceImplTest {

    @Resource(name = "sfExpressService")
    private SFExpressService sfExpressService;

    @Test
    public void getSFExpress() {
        SFExpress sfExpress = sfExpressService.getSFExpress("295529467258" , "13218821996");
        String s = JsonUtil.toJson(sfExpress);
        System.out.println(s);
    }
}