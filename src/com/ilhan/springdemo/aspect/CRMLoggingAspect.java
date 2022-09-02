package com.ilhan.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger
    private Logger myLogger = Logger.getLogger(CRMLoggingAspect.class.getName());
    //setup pointcut declarations
    @Pointcut("execution(* com.ilhan.springdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.ilhan.springdemo.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.ilhan.springdemo.dao.*.*(..))")
    private void forDAOPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forCRMLoggingAdvice(){}

    //add @Before advice
    @Before("forCRMLoggingAdvice()")
    public void beforeLogger(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        myLogger.info("@Before Advice: Method signature is "+ methodSignature);

        //get the args
        Object[] args = joinPoint.getArgs();
        for(Object arg: args){
            myLogger.info(" ====> argument: "+ arg);
        }
    }
    //add @AfterReturning advice

}
