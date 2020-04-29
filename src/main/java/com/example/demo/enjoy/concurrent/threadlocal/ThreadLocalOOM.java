package com.example.demo.enjoy.concurrent.threadlocal;

import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * [ThreadLocal造成的内存泄漏演示]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/28
 */
public class ThreadLocalOOM {
    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 8, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

    private static class LocalValue {
        private byte[] bytes = new byte[10 * 1024 * 1024];
    }

    private static ThreadLocal<LocalValue> threadLocal;

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            poolExecutor.execute(()->{
                threadLocal = new ThreadLocal<>();
                threadLocal.set(new LocalValue());
                System.out.println(Thread.currentThread().getName()+" use local value");
            });
        }
        Thread.sleep(1000);
        System.out.println("pool execute over");
    }
}
