package com.nick.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger

    private Logger logger = Logger.getLogger(getClass().getName());

    //setup pointcut declaration for controller

    @Pointcut("execution(* com.nick.spring.controller.*.*(..))")
    private void forControllerPackage(){}

    //setup pointcut declaration for service

    @Pointcut("execution(* com.nick.spring.service.*.*(..))")
    private void forServicePackage(){}

    //setup pointcut declaration for dao

    @Pointcut("execution(* com.nick.spring.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}


    //add @Before advice

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        //display a method we are calling

        String method = joinPoint.getSignature().toShortString();
        logger.info("=======>> in @Before: calling method: " + method);

        //display the arguments to the method

        //get the arguments

        Object[] args = joinPoint.getArgs();

        //loop thru and display arguments

        for(Object arg : args){
            logger.info("======>> argument: " + arg);
        }

    }

    // add @AfterReturning annotation
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result){

        //display method we are returning from
        String method = joinPoint.getSignature().toShortString();
        logger.info("=======>> in @AfterReturning: from method: " + method);

        // display data return

        logger.info("=======>> result: " + result);

    }

}
