package com.meiyukai.service.impl;

import com.meiyukai.domain.OrderDetail;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.enums.OrderStatusEnum;
import com.meiyukai.enums.PayStatusEnum;
import com.meiyukai.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Resource(name = "orderService")
    private OrderService orderService;

    private final String BUYER_OPENID  =   "openid-123321" ;


    @Test
    public void createOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerName("evenmei");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("13218821996");

        //购物车
        List<OrderDetail> orderDetailList   =   new ArrayList<>();
        OrderDetail o1 =  new OrderDetail();
        o1.setProductId("003");
        o1.setProductQuantity(1);

        OrderDetail o2 =  new OrderDetail();
        o2.setProductId("002");
        o2.setProductQuantity(1);

        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);

        orderService.createOrder(orderDTO);
    }

    @Test
    public void findOne() {
        String orderId =  "1580907410938979155";
        OrderDTO orderDTO = orderService.findOne(orderId);
        System.out.println("orderDTO  :  " + orderDTO);

    }

    @Test
    public void findOrderList() {
        Page<OrderDTO> orderList = orderService.findOrderList(BUYER_OPENID, PageRequest.of(0, 2));
//        System.out.println("orderList :   " + orderList.getContent());
        System.out.println("=======================   =======================  ======================= ");
        for (OrderDTO orderDTO : orderList.getContent()){
            System.out.println("----- orderDTO---  " + orderDTO);
        }
        System.out.println("=======================   =======================  ======================= ");

    }

    @Test
    public void cancel() {
        String orderId =  "1580907410938979155";
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO canceledOrderDTO = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode() , orderDTO.getOrderStatus());
        System.out.println("canceledOrderDTO  :  " + canceledOrderDTO);
    }

    @Test
    public void finish() {

    }

    @Test
    public void paid() {

    }
}