package com.meiyukai;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.management.snmp.util.MibLogger;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Slf4j
public class LoggerTest {
    private MibLogger log;


//    private final Logger logger  = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test01(){

        String name = "hello ";
        String password = "123456";
       // log.info("name : {}  ,  password: {} " , name , password );   // { } 是一个占位符

       /* logger.debug("logger ... ... ");
        logger.info("info ... ... ");
        logger.warn("warn .. .. .. ");
        logger.error("error ... ... ");*/


//       log.debug("debuf .. ... ....");
//       log.info("info .... .... ");
//       log.error("error ... ...");
//       log.warn("warn ... ...");


    }





}
