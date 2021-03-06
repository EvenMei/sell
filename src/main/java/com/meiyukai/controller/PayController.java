package com.meiyukai.controller;

import com.lly835.bestpay.model.PayResponse;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.enums.OrderStatusEnum;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.service.OrderService;
import com.meiyukai.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/pay")
@Slf4j
public class PayController {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource(name = "payService")
    private PayService payService;


    @GetMapping(value = "/create")
    public ModelAndView create(@RequestParam(value = "orderId") String orderId ,
                                                        @RequestParam(value = "returnUrl") String returnUrl ,
                                                            Map<String , Object> map){

        ModelAndView mav = new ModelAndView();

        // 1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);

        try{
            if (orderDTO == null){
                log.error("【查询订单】订单不存在 orderId ={} " , orderId);
                throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
            }

            if (orderDTO.getOrderStatus()!= OrderStatusEnum.NEW.getCode()){
                log.error("【微信支付】订单状态错误 orderStaus={}" , orderDTO.getOrderStatus());
                throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
            }
        }catch(Exception e){
            map.put("msg" , e.getMessage());
            map.put("url" , returnUrl.substring(0,returnUrl.lastIndexOf("#")));
            log.info("【returnUrl】 returnUrl = {}" , returnUrl.substring(0,returnUrl.lastIndexOf("#")));
            mav.setViewName("common/error");
            return mav;
        }


        //2.发起支付
        PayResponse payResponse = payService.create(orderDTO);

        map.put("appId" , payResponse.getAppId());
        map.put("timeStamp" , payResponse.getTimeStamp());
        map.put("nonceStr" , payResponse.getNonceStr());
        map.put("package" , payResponse.getPackAge());
        map.put("signType" , payResponse.getSignType());
        map.put("paySign" , payResponse.getPaySign());
        map.put("returnUrl" , returnUrl);
        mav.addAllObjects(map);
        mav.setViewName("pay/create");
        return mav;
    }


    @PostMapping(value = "/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        ModelAndView mav = new ModelAndView();
        // 返回给微信支付 支付结果
        mav.setViewName("pay/success");
        return mav;
    }



}
