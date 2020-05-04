package com.example.demo.enjoy.concurrent.aqs;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/4
 */
public class TestReenterSelfLock {
    private static class Work implements Runnable {
        private int i = 1;
        private ReenterSelfLock reenterSelfLock;

        public Work(ReenterSelfLock reenterSelfLock) {
            this.reenterSelfLock = reenterSelfLock;
        }

        @SneakyThrows
        @Override
        public void run() {
            reenterSelfLock.lock();
            try {
                //do something
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + ":递归层级:" + i);
                if (++i > 3) return;
                run();
            } finally {
                reenterSelfLock.unlock();
            }
        }
    }

    @SneakyThrows
    @Test
    public void test() {
        final ReenterSelfLock reenterSelfLock = new ReenterSelfLock();
        for (int i = 0; i < 5; i++) {
            new Thread(new Work(reenterSelfLock)).start();
        }
        /**
         * 每秒换行
         */
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println();
            }
        }, 1000, 1000);

        Thread.sleep(8000);
    }
}
