package com.example.demo.transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 〈事务AOP〉
 *
 * @author jinbiao
 * @create 2019.06.17
 */
@Aspect
public class TransactionAop {
    @Autowired
    private ExtTransactionUtil extTransactionUtil;

    @AfterThrowing("execution(* com.example.demo..*.*(..))")
    public void exception(){
        extTransactionUtil.rollback();
    }

    @Around("execution(* com.example.demo..*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        1、定义aop拦截方法
//        2、检查加了注解
//        2.1、加环绕通知处理、异常处理
//        2.1.1、事务utils
        extTransactionUtil.begin();
        proceedingJoinPoint.proceed();
        extTransactionUtil.commit();
    }
}
