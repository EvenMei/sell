package com.meiyukai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "url")
public class ProjectUrl {


    /**
     * 微信公众号授权
     */
    public String wechatMpAuthorize;


    /**
     * 点餐系统
     */
    public String sell;



}
