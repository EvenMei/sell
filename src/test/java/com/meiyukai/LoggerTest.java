package com.meiyukai;

import com.meiyukai.dao.IUser;
import com.meiyukai.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {


//    private final Logger logger  = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test01(){

        String name = "hello ";
        String password = "123456";
        log.info("name : {}  ,  password: {} " , name,password );   // { } 是一个占位符

       /* logger.debug("logger ... ... ");
        logger.info("info ... ... ");
        logger.warn("warn .. .. .. ");
        logger.error("error ... ... ");*/


       log.debug("debuf .. ... ....");
       log.info("info .... .... ");
       log.error("error ... ...");
       log.warn("warn ... ...");


    }





}
