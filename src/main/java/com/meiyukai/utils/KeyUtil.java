package com.meiyukai.utils;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 * 自动生成主键
 * 格式 ： 时间 + 毫秒数
 */
public class KeyUtil {

    public static  synchronized String genUniqueKey(){
        Random random =  new Random();
        Integer a  =  random.nextInt(900000)+100000; // 生成六位随机数
        return System.currentTimeMillis() + String.valueOf(a);

    }

}
