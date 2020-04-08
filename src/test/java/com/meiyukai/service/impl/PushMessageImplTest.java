package com.meiyukai.service.impl;

import com.meiyukai.dto.OrderDTO;
import com.meiyukai.service.OrderService;
import com.meiyukai.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageImplTest {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource(name = "pushMessage")
    private PushMessageService pushMessageService;


    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1586165722534898865");
        pushMessageService.orderStatus(orderDTO);
    }

    @Test
    public void newOrder(){
        OrderDTO orderDTO = orderService.findOne("1586165722534898865");
        pushMessageService.newOrder(orderDTO);
    }




}