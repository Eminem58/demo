package com.example.demo.enjoy.concurrent.actual.transfer;

import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;

/**
 * [不会产生死锁的安全转账第二种方法]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class SafeOperateToo implements ITransfer{

    @SneakyThrows
    @Override
    public void transfer(UserAccount from, UserAccount to, BigDecimal amount) {
        Lock fromLock = from.getLock();
        Lock toLock = to.getLock();
        while (true){
            if(fromLock.tryLock()){
                try {
                    if(toLock.tryLock()){
                        try {
                            System.out.println(Thread.currentThread().getName() + "："+from.getAccountName()+"  转给 " + to.getAccountName()+amount);
                            from.subtractMoney(amount);
                            to.addMoney(amount);
                            System.out.println("转账完成，from=" + from + "--------to=" + to);
                            break;
                        }finally {
                            toLock.unlock();
                        }
                    }
                }finally {
                    fromLock.unlock();
                }
            }
            Thread.sleep(10);
        }
    }
}
