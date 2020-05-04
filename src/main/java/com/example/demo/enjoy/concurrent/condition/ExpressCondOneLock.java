package com.example.demo.enjoy.concurrent.condition;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/4
 */
public class ExpressCondOneLock {
    private Lock lock = new ReentrantLock();
    private Condition siteCondition = lock.newCondition();
    private Condition kmCondition = lock.newCondition();

    private static final String DESTINATION = "上海";
    private String site;

    public ExpressCondOneLock(String site) {
        this.site = site;
    }

    @SneakyThrows
    public void waitSiteEnd() {
        lock.lock();
        try{
            while (!DESTINATION.equals(site)) {
                siteCondition.await();
                System.out.println("Check Site thread[" + Thread.currentThread().getId() + "] is be notified。 site=" + site);
            }
            System.out.println("快递到了，通知客户。");
        }finally {
            lock.unlock();
        }
    }

    public void changeSiteAndNotifyCustomer() {
        lock.lock();
        try{
            site = "上海";
            siteCondition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
