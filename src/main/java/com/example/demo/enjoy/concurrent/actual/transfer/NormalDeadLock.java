package com.example.demo.enjoy.concurrent.actual.transfer;

import lombok.SneakyThrows;
import org.junit.Test;

/**
 * [演示普通账户的死锁和解决]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class NormalDeadLock {
    private final Object firstLock = new Object();
    private final Object secondLock = new Object();

    @SneakyThrows
    public void transfer() {
        synchronized (firstLock) {
            System.out.println(Thread.currentThread().getName() + " get firstLock");
            Thread.sleep(1000);
            synchronized (secondLock) {
                System.out.println(Thread.currentThread().getName() + " get secondLock");
                System.out.println("执行转账");
            }
        }
    }

    @SneakyThrows
    public void transfer2() {
        synchronized (secondLock) {
            System.out.println(Thread.currentThread().getName() + " get secondLock");
            Thread.sleep(1000);
            synchronized (firstLock) {
                System.out.println(Thread.currentThread().getName() + " get firstLock");
                System.out.println("执行转账");
            }
        }
    }

    @SneakyThrows
    @Test
    public void test() {
        new Thread(() -> transfer(), "线程1").start();
        new Thread(() -> transfer2(), "线程2").start();
        Thread.sleep(5000);
    }
}
