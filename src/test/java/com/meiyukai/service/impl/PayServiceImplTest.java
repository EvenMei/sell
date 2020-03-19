package com.meiyukai.service.impl;

import com.lly835.bestpay.model.PayResponse;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.service.OrderService;
import com.meiyukai.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PayServiceImplTest {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource(name = "payService")
    private PayService payService;

    @Test
    public void  testCreate(){
        OrderDTO orderDTO  = orderService.findOne("1584520926965632078");
        log.info("【查询订单】orderDTO = {}" , orderDTO);
        payService.create(orderDTO);
    }


    @Test
    public  void  refundTest(){
        OrderDTO orderDTO = orderService.findOne("1584544790050369843");
        payService.refund(orderDTO);
    }




}