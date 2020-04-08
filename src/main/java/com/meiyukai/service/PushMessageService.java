package com.meiyukai.service;

import com.meiyukai.dto.OrderDTO;

/**
 * 微信模板消息推送
 */

public interface PushMessageService {

    void orderStatus(OrderDTO  orderDTO) ;


    void newOrder(OrderDTO orderDTO);

    void paid(OrderDTO orderDTO);

    void buyerCancel(OrderDTO orderDTO);

    void sellerCancel(OrderDTO orderDTO);



}
