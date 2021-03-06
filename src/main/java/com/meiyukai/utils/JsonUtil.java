package com.meiyukai.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder() ;
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }


    public static Object fromJson(String resource , Class clazz){
        GsonBuilder gsonBuilder =  new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(resource , clazz);
    }

    public static String toJsonCommon(Object object){
        return new Gson().toJson(object);
    }





}
