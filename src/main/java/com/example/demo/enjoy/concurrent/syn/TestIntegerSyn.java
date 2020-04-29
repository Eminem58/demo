package com.example.demo.enjoy.concurrent.syn;

import lombok.SneakyThrows;

/**
 * [错误的加锁和原因分析]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/21
 */
public class TestIntegerSyn {

    public static void main(String[] args) {
        Task task = new Task(2);
        for (int i = 0; i < 3; i++) {
            //new Thread(new Task(2)).start();
            new Thread(task).start();
        }
    }

    private static class Task implements Runnable {
        Integer i;
        private Object object = new Object();

        public Task(Integer i) {
            this.i = i;
        }

        @SneakyThrows
        @Override
        public void run() {
            synchronized (object) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " i=" + i + " hashCode=" + System.identityHashCode(i));
                i++;
                System.out.println(name + " i=" + i + " hashCode=" + System.identityHashCode(i));
            }
        }
    }
}
