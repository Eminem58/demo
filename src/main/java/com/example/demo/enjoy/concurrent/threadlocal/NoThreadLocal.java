package com.example.demo.enjoy.concurrent.threadlocal;

import lombok.SneakyThrows;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/27
 */
public class NoThreadLocal {
    private static int integer = 100;

    private static class Task implements Runnable {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            integer += i;
            System.out.println(Thread.currentThread().getName() + " i=" + integer);
        }
    }

    @SneakyThrows
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(new Task(i), "thread-" + i).start();
            Thread.sleep(50);
        }
        Thread.sleep(1000);
    }
}
