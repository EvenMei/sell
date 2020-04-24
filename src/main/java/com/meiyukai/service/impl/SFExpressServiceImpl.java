package com.meiyukai.service.impl;

import com.meiyukai.config.SFExpressConfig;
import com.meiyukai.domain.SFExpress;
import com.meiyukai.service.SFExpressService;
import com.meiyukai.utils.SFExpressUtils;
import org.apache.ibatis.annotations.Arg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "sfExpressService")
public class SFExpressServiceImpl implements SFExpressService {
    @Autowired
    private SFExpressConfig sfExpressConfig;

    @Override
    public SFExpress getSFExpress(String sfExpressNumber, String phoneNumber) {
        String jsonData = SFExpressUtils.getJsonData(sfExpressNumber , sfExpressConfig.getAppid() , sfExpressConfig.getSign(),phoneNumber.substring(7));
        return SFExpressUtils.getSFExpress(jsonData);
    }
}
