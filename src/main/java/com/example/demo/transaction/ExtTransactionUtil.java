package com.example.demo.transaction;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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



    @Around("execution(* com.example.demo..*.*(..))")
    public void around(){


    }











    public TransactionStatus begin() {
        TransactionStatus transactionStatus=dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    public void commit(TransactionStatus transactionStatus){
        dataSourceTransactionManager.commit(transactionStatus);
    }

    public void rollback(TransactionStatus transactionStatus){
        dataSourceTransactionManager.rollback(transactionStatus);
    }
}
