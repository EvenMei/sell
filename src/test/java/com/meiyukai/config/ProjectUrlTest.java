package com.meiyukai.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectUrlTest {

    @Autowired
    private ProjectUrl projectUrl;

    @Test
    public void getMpAuthorizeTest(){
        System.out.println("mpauthorize : " + projectUrl.getWechatMpAuthorize());
    }

}