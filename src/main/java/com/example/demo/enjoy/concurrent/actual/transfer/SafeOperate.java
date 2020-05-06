package com.example.demo.enjoy.concurrent.actual.transfer;

import java.math.BigDecimal;

/**
 * [不会产生死锁的安全转账]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class SafeOperate implements ITransfer {
    //第三把锁
    private static Object lock = new Object();

    @Override
    public void transfer(UserAccount from, UserAccount to, BigDecimal amount) {
        int fromHashCode = System.identityHashCode(from);
        int toHashCode = System.identityHashCode(to);
        if (fromHashCode < toHashCode) {
            synchronized (from) {
                synchronized (to) {
                    System.out.println(Thread.currentThread().getName() + "："+from.getAccountName()+"  转给 " + to.getAccountName()+amount);
                    from.subtractMoney(amount);
                    to.addMoney(amount);
                    System.out.println("转账完成，from=" + from + "--------to=" + to);
                }
            }
        } else if (fromHashCode > toHashCode) {
            synchronized (to) {
                synchronized (from) {
                    System.out.println(Thread.currentThread().getName() + "："+from.getAccountName()+"  转给 " + to.getAccountName()+amount);
                    from.subtractMoney(amount);
                    to.addMoney(amount);
                    System.out.println("转账完成，from=" + from + "--------to=" + to);
                }
            }
        } else {
            synchronized (lock){
                synchronized (from) {
                    synchronized (to) {
                        System.out.println(Thread.currentThread().getName() + "："+from.getAccountName()+"  转给 " + to.getAccountName()+amount);
                        from.subtractMoney(amount);
                        to.addMoney(amount);
                        System.out.println("转账完成，from=" + from + "--------to=" + to);
                    }
                }
            }
        }
    }
}
