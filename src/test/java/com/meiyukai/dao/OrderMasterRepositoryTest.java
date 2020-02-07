package com.meiyukai.dao;

import com.meiyukai.domain.OrderMaster;
import com.meiyukai.enums.OrderStatusEnum;
import com.meiyukai.enums.PayStatusEnum;
import org.hibernate.annotations.DynamicUpdate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Resource(name = "orderMasterRepository")
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void findAll(){

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        for (OrderMaster orderMaster :  orderMasterList){
            System.out.println("orderMaster  :  " +orderMaster);
        }

    }

    @Test
    public void testSaveOrderMaster(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("002");
        orderMaster.setBuyerAddress("江苏省泰州市兴化市");
        orderMaster.setBuyerName("鸡翅");
        orderMaster.setBuyerOpenid("邵师傅");
        orderMaster.setBuyerPhone("15861052637");
        orderMaster.setOrderAmount(new BigDecimal(2));
        /*orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());*/

        orderMasterRepository.save(orderMaster);

    }


    @Test
    public void findByBuyerOpenidTest(){
        PageRequest pageable = PageRequest.of(0,3);
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid("evenmei" , pageable);
        System.out.println("orderMasterPage  :   " + orderMasterPage.getContent());
    }


}