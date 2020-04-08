package com.meiyukai.service.impl;

import com.meiyukai.dao.SellerInfoRepository;
import com.meiyukai.domain.SellerInfo;
import com.meiyukai.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value = "sellerService")
public class SellerServiceImpl implements SellerService   {

    @Resource(name = "sellerInfoRepository")
    private SellerInfoRepository sellerInfoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public SellerInfo findByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }

    @Override
    public SellerInfo findByUsername(String username) {
        return sellerInfoRepository.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SellerInfo sellerInfo = sellerInfoRepository.findByUsername(username);
//        System.out.println("【sellerInfo --- User  】 : " + sellerInfo);
        User user = new User(username , passwordEncoder.encode(sellerInfo.getPassword()) , getAuthorities());
        return user;
    }


    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }


}
