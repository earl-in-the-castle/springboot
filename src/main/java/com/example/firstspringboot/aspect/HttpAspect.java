package com.example.firstspringboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

 /*   @Before("execution(public * com.example.firstspringboot.controller.GirlController.getGirlList(..))")
    public void log(){
        System.out.println("1111");
    }*/


    @Pointcut("execution(public * com.example.firstspringboot.controller.GirlController.*(..))")
    public void log(){

    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();
        logger.info("doBefore");
        //url
        logger.info("url{}",request.getRequestURL());

        //请求方式 method
        logger.info("method{}",request.getMethod());
        //ip
        logger.info("ip{}",request.getRemoteAddr()+request.getRemoteHost());
        //请求的类方法
        logger.info("request_methood{}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //参数
        logger.info("args{}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(){
        logger.info("doAfter");
    }


    @AfterReturning(returning="object",pointcut = "log()")
    public void afterReturning(Object object){
        logger.info("return={}",object);
        logger.info("doAfter");
    }
}
