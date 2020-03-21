package com.meiyukai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/nav")
public class navController {

    @GetMapping(value = "/test")
    public ModelAndView nav(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("common/nav");
        return mav;
    }


}
