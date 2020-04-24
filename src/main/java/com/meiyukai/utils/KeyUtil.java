package com.meiyukai.utils;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 自动生成主键
 * 格式 ： 时间 + 毫秒数
 */
public class KeyUtil {

    public static  synchronized String getUniqueKey(){
        Random random =  new Random();
        Integer a  =  random.nextInt(900000)+100000; // 生成六位随机数
        return System.currentTimeMillis() + String.valueOf(a);
    }



    public static synchronized Integer getUniqueKey(List<Integer> ids ){
        Integer base = (Integer)new Random().nextInt(1000)+10000;
        while(ids.contains(base)){
            base = (Integer)new Random().nextInt(1000)+10000;
        }
        return base;
    }



}
