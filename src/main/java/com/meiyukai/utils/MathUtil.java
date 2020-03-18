package com.meiyukai.utils;

import java.lang.management.MonitorInfo;

public class MathUtil {

    private static Double MONEY_RANGE = 0.01;
    //比较 两个 double 的数值是否相等 比如 0.1 和 0.01
    public static Boolean equals(Double d1 , Double d2){

        Double result = Math.abs(d1-d2);
        if (result < MONEY_RANGE){
        return true;
        }
        else return false;
    }


}
