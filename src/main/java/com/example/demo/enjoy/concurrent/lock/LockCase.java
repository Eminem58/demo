package com.example.demo.enjoy.concurrent.lock;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * [使用Lock的范例/1 ]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/3
 */
public class LockCase {
    private Lock lock = new ReentrantLock();
    private int i = 0;

    public void increase() {
        lock.lock();
        try {
            i++;
        }finally {
            lock.unlock();
        }
    }

    public void decrease(){
        lock.lock();
        try {
            i--;
        }finally {
            lock.unlock();
        }
    }


    private static class Work implements Runnable{
        private LockCase lockCase;
        public Work(LockCase lockCase){
            this.lockCase=lockCase;
        }

        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                lockCase.increase();
            }
        }
    }


    @SneakyThrows
    public static void main(String[] args) {
        LockCase lockCase = new LockCase();
        new Thread(new Work(lockCase),"work").start();
        for (int i = 0; i < 10000; i++) {
            lockCase.decrease();
        }
        Thread.sleep(1000);
        System.out.println(lockCase.i);
    }
}
