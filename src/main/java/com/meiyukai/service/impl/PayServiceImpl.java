package com.meiyukai.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import com.meiyukai.service.OrderService;
import com.meiyukai.service.PayService;
import com.meiyukai.utils.JsonUtil;
import com.meiyukai.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service(value = "payService")
@Slf4j
public class PayServiceImpl implements PayService {


    @Autowired
    private BestPayServiceImpl bestPayService;

    @Resource(name = "orderService")
    private OrderService orderService;



    @Override
    public PayResponse create(OrderDTO orderDTO) {


        //  获取用户订单的名称
        List<String> orderName = orderService.getOrderName(orderDTO);
        if (CollectionUtils.isEmpty(orderName)){
            log.info(" 【获取订单名称】订单名称 为空 orderId = {}" ,orderDTO.getOrderId() );
            throw new SellException(ResultEnum.ORDER_NAME_EMPTY);
        }

        // 生成付款请求
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(orderName.toString());
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MP);

        log.error("【微信支付】微信支付请求  ，  payRequest={}" , JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.error("【微信支付 】 微信支付结果 ： payResponse ={}" ,JsonUtil.toJson(payResponse));

        return payResponse;

    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证请求
        //2.支付状态
        //3.支付的金额
        //4.支付的人(下单的人 ==？支付的人)


        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付 】异步通知  payResponse = {}" , JsonUtil.toJson(payResponse));

        //查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        if(orderDTO == null){
            log.error(" 【微信支付 】订单不存在 ： orderId ={}"  , orderDTO.getOrderId() );
            throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }


        //判断金额是否一致
        if(!MathUtil.equals(orderDTO.getOrderAmount().doubleValue() , payResponse.getOrderAmount())  ){
            log.error("【微信支付】 金额不一致  微信通知金额={} , 系金额={}" , orderDTO.getOrderId() , orderDTO.getOrderAmount() );
            throw new SellException(ResultEnum.WECHAT_NOTIFY_MONEY_VARIFY_ERROR);
        }


        //修改订单
        orderService.paid(orderDTO);

        return payResponse;
    }


    /**
     * 微信退款
     * @param orderDTO
     * @return
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MP);
        log.info("【微信退款】 refundRequest = {}"  , JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info(" 【微信退款 】 refundResponse = {}" , JsonUtil.toJson(refundResponse));
        return refundResponse;
    }



}
