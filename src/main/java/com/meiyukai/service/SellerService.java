package com.meiyukai.service;


import com.meiyukai.domain.SellerInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SellerService  extends UserDetailsService {

    SellerInfo findByOpenid(String openid);

    SellerInfo findByUsername(String username);



}
