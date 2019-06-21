package com.example.demo.transaction;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * 〈手写事务〉
 *
 * @author jinbiao
 * @create 2019.06.12
 */
@Component
@Aspect
public class ExtTransactionUtil {


    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    public static void main(String[] args) {
//        1、定义aop拦截方法
//        2、检查加了注解
//        2.1、加环绕通知处理、异常处理
//        2.1.1、事务utils
    }

    private TransactionStatus transactionStatus=null;


    public TransactionStatus begin() {
        transactionStatus=dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    public void commit(){
        dataSourceTransactionManager.commit(transactionStatus);
    }

    public void rollback(){
        dataSourceTransactionManager.rollback(transactionStatus);
    }
}
