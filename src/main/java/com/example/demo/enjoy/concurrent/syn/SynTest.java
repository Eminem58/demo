package com.example.demo.enjoy.concurrent.syn;

import lombok.SneakyThrows;

/**
 * [synchronized关键字的使用方法]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/26
 */
public class SynTest {
    private static int i;
    private Object object = new Object();

    public void increase() {
        //i++;
        synchronized (object) {
            i++;
        }
    }

    public synchronized void increase2() {
        i++;
    }

    public void increase3() {
        synchronized (this) {
            i++;
        }
    }

    private static class Worker implements Runnable {
        private SynTest synTest;

        public Worker(SynTest synTest) {
            this.synTest = synTest;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                synTest.increase();
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        SynTest synTest = new SynTest();
        new Thread(new Worker(synTest)).start();
        new Thread(new Worker(synTest)).start();
        Thread.sleep(1000);
        System.out.println("i=" + i);
    }

}
