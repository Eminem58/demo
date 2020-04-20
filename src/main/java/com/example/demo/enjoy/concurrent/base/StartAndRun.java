package com.example.demo.enjoy.concurrent.base;

import lombok.SneakyThrows;

/**
 * [Start和Run的区别]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/19
 */
public class StartAndRun {
    private static class ThreadRun extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "：is running " + i);
            }
        }
    }

    public static void main(String[] args) {
        ThreadRun threadRun = new ThreadRun();
        threadRun.setName("threadRun");
        threadRun.run();
//        threadRun.start();
    }
}
