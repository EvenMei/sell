package com.meiyukai.utils;

import com.meiyukai.enums.CodeEnum;

public class EnumUtils {
    public static  <T extends CodeEnum<Integer>> T getByCode(Integer code , Class <T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if (each.getCode().equals(code)){
                return each;
            }
        }
        return null;
    }
}
