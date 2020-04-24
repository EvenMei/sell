package com.meiyukai.dto;

import com.meiyukai.domain.OrderDetail;
import lombok.Data;

import java.util.List;

@Data
public class Item {
    String openid;
    List<OrderDetail> orderDetailList;

}
