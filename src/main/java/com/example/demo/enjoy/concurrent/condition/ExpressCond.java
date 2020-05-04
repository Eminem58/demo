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
 * @date 2020/5/3
 */
public class ExpressCond {
    private Lock siteLock = new ReentrantLock();
    private Lock kmLock = new ReentrantLock();
    private Condition siteCondition = siteLock.newCondition();
    private Condition kmCondition = kmLock.newCondition();

    private static final String DESTINATION = "上海";
    private String site;

    public ExpressCond(String site) {
        this.site = site;
    }

    @SneakyThrows
    public void waitSiteEnd() {
        siteLock.lock();
        try{
            while (!DESTINATION.equals(site)) {
                siteCondition.await();
                System.out.println("Check Site thread[" + Thread.currentThread().getId() + "] is be notified。 site=" + site);
            }
            System.out.println("快递到了，通知客户。");
        }finally {
            siteLock.unlock();
        }
    }

    public void changeSiteAndNotifyCustomer() {
        siteLock.lock();
        try{
            site = "上海";
            siteCondition.signalAll();
        }finally {
            siteLock.unlock();
        }
    }
}
