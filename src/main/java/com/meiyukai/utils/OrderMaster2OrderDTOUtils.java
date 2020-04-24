package com.meiyukai.utils;

import com.meiyukai.dao.OrderDetailRepository;
import com.meiyukai.domain.OrderDetail;
import com.meiyukai.domain.OrderMaster;
import com.meiyukai.dto.OrderDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMaster2OrderDTOUtils {

    @Resource(name = "orderDetailRepository")
    private OrderDetailRepository orderDetailRepository;

    public OrderDTO convert(OrderMaster orderMaster ){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster  , orderDTO);
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    public List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }


}
