package com.meiyukai.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meiyukai.domain.OrderDetail;
import com.meiyukai.dto.Item;
import com.meiyukai.dto.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JsonUtilTest {

    @Test
    public void toJson() {
        Gson gson = new Gson();
        Item item = new Item();
        item.setOpenid("openid-abcccac");
        List<OrderDetail> list = new ArrayList<>();
        OrderDetail product1 = new OrderDetail();
        product1.setProductId("1111");
        product1.setProductQuantity(10);


        OrderDetail product2 = new OrderDetail();
        product2.setProductId("1111");
        product2.setProductQuantity(10);
        list.add(product1);
        list.add(product2);
        item.setOrderDetailList(list);
        String jsonItem = JsonUtil.toJson(item);

        System.out.println("item : " + JsonUtil.toJson(item));
        System.out.println("------------------------------------");
        Object o = JsonUtil.fromJson(jsonItem, Item.class);
        System.out.println("object  : " + o);

        List<OrderDetail> orderDetailList  =gson.fromJson(JsonUtil.toJson(item.getOrderDetailList()),  new TypeToken<List<OrderDetail>>(){}.getType());
        System.out.println(" ---- ----- orderDetaiList ------ ----- : " + JsonUtil.toJsonCommon(orderDetailList));

    }

}