package com.example.demo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@Scope("prototype")
public class ExtTransactionUtil {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    private TransactionStatus transactionStatus;


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
