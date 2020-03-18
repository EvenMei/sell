package com.meiyukai.service.impl;

import com.meiyukai.dto.OrderDTO;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.service.BuyerService;
import com.meiyukai.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "buyerService")
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Resource(name = "orderService")
    private OrderService orderService;


    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return chechOrderOwner(openid, orderId);
    }


    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO =  chechOrderOwner(openid , orderId);
        if (orderDTO == null){
            log.error("【取消订单】订单不能为空  openid = {} , orderId = {}"  , openid , orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }
        return orderService.cancel(orderDTO);
    }





    private OrderDTO chechOrderOwner(String openid , String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO==null){
            return null;
        }
        //判断是否属于自己的订单
        if (!orderDTO.getBuyerOpenid().equals(openid)){
            log.error("【订单查询】openid 不一致 currentOpenid={}   requiredOpenid={}" , openid , orderDTO.getBuyerOpenid());
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;

    }

}
