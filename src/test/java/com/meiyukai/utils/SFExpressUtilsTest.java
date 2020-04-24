package com.meiyukai.utils;

import com.meiyukai.domain.Info;
import com.meiyukai.domain.SFExpress;
import org.junit.Test;

import static org.junit.Assert.*;

public class SFExpressUtilsTest {

    @Test
    public void getJsonData(){
        String jsonData = SFExpressUtils.getJsonData("295529467258","193251","d4b4da5480b54c2c8a70c4b5bb37342b","0653");
        SFExpress sfExpress = SFExpressUtils.getSFExpress(jsonData);
        for (Info info : sfExpress.getShowapi_res_body().getData()){
            System.out.println("info : " + info);
        }

    }
}