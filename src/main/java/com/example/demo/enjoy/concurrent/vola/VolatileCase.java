package com.example.demo.enjoy.concurrent.vola;

import lombok.SneakyThrows;

/**
 * [演示Volatile的提供的可见性]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/26
 */
public class VolatileCase {

    private static volatile boolean flag;

    private static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Task is running.......");
            while (!flag) {
                //加这一句就可见了，什么情况？
                //System.out.println("running");
            }
            System.out.println("============================");
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        new Thread(new Task()).start();
        Thread.sleep(1000);
        flag = true;
        Thread.sleep(5000);
        System.out.println("main is end");
    }
}
