package com.meiyukai.dao;

import com.meiyukai.domain.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class OrderDetailRepositoryTest {
    @Resource(name = "orderDetailRepository")
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testFindAll(){
        List<OrderDetail> orderDetailList = orderDetailRepository.findAll();
        for (OrderDetail orderDetail : orderDetailList){
            System.out.println("orderDetail :   " + orderDetail);
        }
    }


    @Test
    public void testSave(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("001");
        orderDetail.setOrderId("001");
        orderDetail.setProductPrice(new BigDecimal(10.0));
        orderDetail.setProductIcon("http://XXXXX.jpg");
        orderDetail.setProductName("洗面奶");
        orderDetail.setProductQuantity(1000);
        orderDetail.setProductId("001");
        orderDetailRepository.save(orderDetail);
    }

    @Test
    public void findByOrderIdTest(){
        List<OrderDetail> orderDetailList =  orderDetailRepository.findByOrderId("001");
        for(OrderDetail orderDetail :  orderDetailList){
            System.out.println("orderDetail :  " +   orderDetail);
        }
    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void deleteByOrderIdTest(){
        orderDetailRepository.deleteByOrderId("222");
    }





}