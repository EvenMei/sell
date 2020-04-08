package com.meiyukai.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@RequestMapping(value = "/seller/login")
public class LoginController {

    @GetMapping(value = "/toLogin")
    public ModelAndView toLogin(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login/login");
        return mav;
    }


    @GetMapping(value = "/toFailure")
    public ModelAndView toFailure(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login/failure");
        return mav;
    }

    @PostMapping(value = "/processLogin")
    public void login(@RequestParam (value = "username") String usernames,
                                    @RequestParam(value = "password") String passwords){
        log.info("usernames = {}" , usernames);
        log.info("passwords = {}" , passwords);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("order/list");
//        return mav;
    }




}
