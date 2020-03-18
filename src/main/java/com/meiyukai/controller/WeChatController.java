package com.meiyukai.controller;

import com.meiyukai.config.WechatMpConfig;
import com.meiyukai.enums.ResultEnum;
import com.meiyukai.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

@Controller
@RequestMapping(value = "/wechat")
@Slf4j
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;

    /*@GetMapping(value = "/auth")
    public void auth(@RequestParam String code ){
        log.info("code =={}" , code);

        //新的 appid 待修改替换进去
        String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response = {}" ,response);

        *//**
         * 正确时返回的JSON数据包如下：
         *
         * {
         *   "access_token":"ACCESS_TOKEN",
         *   "expires_in":7200,
         *   "refresh_token":"REFRESH_TOKEN",
         *   "openid":"OPENID",
         *   "scope":"SCOPE"
         * }
         *//*

    }*/


    /**
     *
     * @param returnUrl 返回的路径地址
     * @return
     */
    @GetMapping(value = "/authorize")
    public String  auth(@RequestParam(value = "returnUrl") String returnUrl){
        //配置
        //调用方法
        String url = "http://xiaomei.natapp1.cc/sell/wechat/userInfo"; //TODO 待修改redirect 的地址
        String state = URLEncoder.encode(returnUrl);
        System.out.println("------ returnUrl :    ------" +state);
        String scope = WxConsts.OAUTH2_SCOPE_USER_INFO;
         String redirectUrl  = wxMpService.oauth2buildAuthorizationUrl(url, scope, state);
        log.info("【微信网页授权】获取code ， result={}" , redirectUrl);
        return "redirect:"+redirectUrl;

    }


    /**
     *
     * @param code 换取的票据
     * @param returnUrl 就是state 传过去的数据，原封不动的传回来
     * @return
     */
    @GetMapping(value = "/userInfo")
    public String userInfo(@RequestParam(value = "code" )String code , @RequestParam(value = "state") String returnUrl){
        System.out.println("--- 进入userInfo ---- ");
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try{
             wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        }catch(WxErrorException e){
            log.error("【微信网页授权】  {}" , e );
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode() , e.getError().getErrorMsg());
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();
        log.info("  【网页授权】openid = {}", openid);
        return "redirect:"+returnUrl+"?openid="+openid;
    }


    




}
