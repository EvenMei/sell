package com.meiyukai.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meiyukai.domain.OrderDetail;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.dto.Product;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 将 orderForm 转换成 orderDTO
 */

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert( OrderForm orderForm){
        Gson gson =  new Gson();

        OrderDTO orderDTO =  new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
             orderDetailList = gson.fromJson(orderForm.getItems() , new TypeToken<List<OrderDetail>>(){}.getType()); //使用 gson 转换成对象
//            orderDetailList = gson.fromJson(orderForm.getItems() , new TypeToken<List<OrderDetail>>(){}.getType()); //使用 gson 转换成对象

        }catch (Exception e){
            log.error("【gson】 items 转换出错  items={} "  , orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR); // items 参数不正确
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}
