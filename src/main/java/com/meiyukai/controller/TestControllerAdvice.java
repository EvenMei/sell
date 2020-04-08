package com.meiyukai.controller;

import com.meiyukai.exception.MyException;
import com.meiyukai.exception.SellException;
import com.meiyukai.utils.ResultVOUtil;
import com.meiyukai.viewobject.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class TestControllerAdvice {


    /**
     * @ExceptionHandler + @ControllerAdvice 的作用是 捕获控制层的异常
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    public ModelAndView toFailure(){
        ModelAndView  mav  =  new ModelAndView();
        mav.setViewName("login/failure");
        return mav;
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
//    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO response(SellException e){
        return new ResultVOUtil().error(e.getCode()  , e.getMessage());
    }





}
