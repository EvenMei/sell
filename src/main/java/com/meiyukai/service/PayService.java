package com.meiyukai.service;

import com.lly835.bestpay.model.PayResponse;
import com.meiyukai.dto.OrderDTO;

/**
 * 支付的逻辑
 */

public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

}
