package com.meiyukai.service.impl;

import com.meiyukai.config.WechatAccountConfig;
import com.meiyukai.domain.Express;
import com.meiyukai.dto.OrderDTO;
import com.meiyukai.service.ExpressService;
import com.meiyukai.service.OrderService;
import com.meiyukai.service.PushMessageService;
import com.meiyukai.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 微信模板消息推送
 */
@Service(value = "pushMessage")
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {


    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource(name = "expressService")
    private ExpressService expressService;



    @Override
    public void orderDeliver(OrderDTO orderDTO) {
        Express express = expressService.findByOrderId(orderDTO.getOrderId());
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("orderDeliver"));
        wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());
//        wxMpTemplateMessage.setToUser("oRKFQs8sCCRMn-i-652xmvTQ6kr4");
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first" , "您好，您在胖阿姨家买的红膏大闸蟹已发货"  ),
                new WxMpTemplateData("keyword1" , express.getExpressNumber()),
                new WxMpTemplateData("keyword2" , express.getExpressName()),
                new WxMpTemplateData("remark" ,"如有疑问请联系客服 18221616376")
        );

        wxMpTemplateMessage.setData(data);
        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        }catch(WxErrorException e){
            log.error("【微信模版消息】 e = {}"  ,e.getMessage());
        }
    }



    /**
     *新的订单通知 商家（ava）
     * @param orderDTO
     */

    @Override
    public void newOrder(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("newOrder"));
        wxMpTemplateMessage.setToUser(wechatAccountConfig.getTemplateId().get("avaOpenid"));
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first" , "您好，您有一份新的订单，订单编号："+orderDTO.getOrderId()),
                new WxMpTemplateData("keyword1" , orderService.getOrderName(orderDTO).toString()),
                new WxMpTemplateData("keyword2" , orderDTO.getOrderAmount().toString()),
                new WxMpTemplateData("keyword3" , "姓名："+orderDTO.getBuyerName()+"    电话："+orderDTO.getBuyerPhone()+"     地址："+orderDTO.getBuyerAddress()),
                new WxMpTemplateData("keyword4" , "微信支付 ： "+orderDTO.getPayStatusEnum()),
//                new WxMpTemplateData("keyword5" , ""),
                new WxMpTemplateData("remark" , "请及时发货哦")
        );
        wxMpTemplateMessage.setData(data);
        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        }catch(Exception e){
            log.error("【微信模板消息】 新订单 ：e = {}" , e.getMessage());
        }
    }

    /**
     * 用户下单成功后通知用户
     * @param orderDTO
     */
    @Override
    public void paid(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("paid"));
        wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first" , "您已成功下达订单，已通知胖阿姨为您打包"),
                new WxMpTemplateData("keyword1" , orderDTO.getOrderId()),
                new WxMpTemplateData("keyword2" , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())),
                new WxMpTemplateData("remark" , "请核对以下信息：【"+"收货人："+orderDTO.getBuyerName()+"   |  联系电话："+orderDTO.getBuyerPhone()+"  |  收货地址："+orderDTO.getBuyerAddress()+"】"+"如有疑问请联系客服 : 18221616376")
        );
        wxMpTemplateMessage.setData(data);
        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        }catch (Exception e){
            log.error(" 【 微信模板消息】用户下单成功  e= {}" ,  e.getMessage());
        }
    }

    /**
     * 买家自行退单
     * @param orderDTO
     */
    @Override
    public void buyerCancel(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("buyerCancel"));
        wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","您有一个订单已经成功取消"),
                new WxMpTemplateData("keyword1",orderDTO.getOrderId()),
                new WxMpTemplateData("keyword2",orderDTO.getCreateTime().toString()),
                new WxMpTemplateData("keyword3",orderDTO.getOrderAmount().toString()),
                new WxMpTemplateData("keyword4",orderService.getOrderName(orderDTO).toString()),
                new WxMpTemplateData("remark" , "如需再次购买请返回主菜单！"));
        wxMpTemplateMessage.setData(data);
        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        }catch (Exception e){
            log.error("【微信模板】买家取消订单 e = {}"  , e.getMessage());
        }

    }

    /**
     * 通知商家有人退单
     * @param orderDTO
     */
    @Override
    public void sellerCancel(OrderDTO orderDTO) {

        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("sellerCancel"));
        wxMpTemplateMessage.setToUser(wechatAccountConfig.getTemplateId().get("avaOpenid"));
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","您有一个订单已被用户自行取消"),
                new WxMpTemplateData("keyword1",orderDTO.getCreateTime().toString()),
                new WxMpTemplateData("keyword2",orderDTO.getOrderAmount().toString()),
                new WxMpTemplateData("keyword3",orderService.getOrderName(orderDTO).toString()),
                new WxMpTemplateData("keyword4",orderDTO.getPayStatusEnum()),
                new WxMpTemplateData("keyword5", "其他"),
                new WxMpTemplateData("remark" , "他不买了，别准备了。。。")
        );
        wxMpTemplateMessage.setData(data);
        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        }catch (Exception e){
            log.error("【微信模板】买家取消订单 e = {}"  , e.getMessage());
        }

    }


}
