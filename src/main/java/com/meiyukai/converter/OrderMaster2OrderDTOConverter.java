package com.meiyukai.converter;

import com.meiyukai.domain.OrderMaster;
import com.meiyukai.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将 orderMaster 转 成 orderDTO 对象
 */

public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster ){
        OrderDTO orderDTO =  new OrderDTO();
        BeanUtils.copyProperties(orderMaster , orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
      /*  List<OrderDTO> orderDTOList =  new ArrayList<>();
        for (OrderMaster orderMaster :  orderMasterList){
            orderDTOList.add(convert(orderMaster));
        }
        return orderDTOList;*/

     return   orderMasterList.stream().map(e-> convert(e)).collect(Collectors.toList());
    }

}
