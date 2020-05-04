package com.example.demo.enjoy.concurrent.aqs;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Timer;
import java.util.TimerTask;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMyLock {
    private static class Work implements Runnable {
        private SelfLock selfLock;

        public Work(SelfLock selfLock) {
            this.selfLock = selfLock;
        }

        @SneakyThrows
        @Override
        public void run() {
            selfLock.lock();
            try {
                //do something
                Thread.sleep(1000);
            } finally {
                selfLock.unlock();
            }
        }
    }

    @SneakyThrows
    @Test
    public void test() {
        final SelfLock selfLock = new SelfLock();
        for (int i = 0; i < 5; i++) {
            new Thread(new Work(selfLock)).start();
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

        Thread.sleep(5000);
    }


    private static class Work2 implements Runnable {
        private SelfLock selfLock;

        public Work2(SelfLock selfLock) {
            this.selfLock = selfLock;
        }

        @SneakyThrows
        @Override
        public void run() {
            selfLock.lock();
            try {
                //do something
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + ":递归层级:" + 10);
                for (int i = 0; i < 10; i++) {
                    run();
                }
            } finally {
                selfLock.unlock();
            }
        }
    }

    /**
     * 测试不可重入
     */
    @SneakyThrows
    @Test
    public void test2() {
        final SelfLock selfLock = new SelfLock();
        for (int i = 0; i < 5; i++) {
            new Thread(new Work2(selfLock)).start();
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

        Thread.sleep(5000);
    }
}
