package com.meiyukai.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/*@Aspect
@Component
@Slf4j*/
public class SellerAuthorizeAspect {

    @Pointcut(value = "execution(* com.meiyukai.controller.*.*(..))")
    public void verify(){}

    @Before(value = "verify()")
    public void doVerify(JoinPoint joinPoint){
       Class clazz = joinPoint.getTarget().getClass(); //获取要访问的类的字节码
       String methodName = joinPoint.getSignature().getName(); //获取要访问的方法名
       // log.info("【正在访问 controller 层 】 clazz = {}  , methodName  ={} " , clazz.getName() , methodName);

        // 先获取 ServletRequestAttributes
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Cookie[] cookies = request.getCookies();
      //  log.info("【cookies】 cookies = {}" , cookies);
    }

}
