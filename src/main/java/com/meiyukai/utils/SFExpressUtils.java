package com.meiyukai.utils;

import com.google.gson.Gson;
import com.meiyukai.domain.SFExpress;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class SFExpressUtils {

    public static SFExpress getSFExpress(String jsonData){
        Gson gson =new Gson();
        return gson.fromJson(jsonData , SFExpress.class);
    }


    public static String getJsonData(String expressNumber , String appid , String sign , String phone){
        String url = "https://route.showapi.com/64-19";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();
        params.add("com"  , "shunfeng");
        params.add("nu" , expressNumber); //SF1019930279092
        params.add("showapi_appid" , appid);
        params.add("showapi_sign" , sign); //"d4b4da5480b54c2c8a70c4b5bb37342b"
        params.add("receiverPhone" , phone);
        params.add("showapi_res_gzip" , "0");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String ,Object >>entity = new HttpEntity<>(params, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(url, entity, String.class);   //当涉及到 entity 的时候 postForEntity getForEntity 使用 exchageForEntity
        return result.getBody();
    }

}
