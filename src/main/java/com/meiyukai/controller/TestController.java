package com.meiyukai.controller;

import com.meiyukai.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {
    @Autowired
    private StringRedisTemplate redisTemplate;


    @GetMapping(value = "/test")
    public String  test(){
        throw new MyException("msg" , 001);
    }

    /**
     * 测试redis的save功能
     * @return
     */
    @GetMapping(value = "/save")
    public String saveStringForRedis(){
        redisTemplate.opsForValue().set("meiyukai"  , "666");
        return "success";
    }

    /**
     * 测试redis 的get 功能
     * @return
     */
    @GetMapping(value = "/get")
    public String getStringForRedis(){
        String value = redisTemplate.opsForValue().get("meiyukai");
        return value;
    }





}
