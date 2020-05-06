package com.example.demo.enjoy.concurrent.actual.transfer;

import lombok.SneakyThrows;

import java.math.BigDecimal;

/**
 * [不安全的转账动作的实现]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class NotSafeOperate implements ITransfer{


    @SneakyThrows
    @Override
    public void transfer(UserAccount from, UserAccount to, BigDecimal amount) {
        synchronized (from){
            synchronized (to){
                System.out.println(Thread.currentThread().getName() + "："+from.getAccountName()+"  转给 " + to.getAccountName()+amount);
                from.subtractMoney(amount);
                to.addMoney(amount);
                System.out.println("转账完成，from=" + from + "--------to=" + to);
            }
        }
    }
}
