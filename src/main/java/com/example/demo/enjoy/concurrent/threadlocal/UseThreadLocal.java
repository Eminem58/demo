package com.example.demo.enjoy.concurrent.threadlocal;

import lombok.SneakyThrows;

/**
 * [测试线程，线程的工作是将ThreadLocal变量的值变化，并写回，看看线程之间是否会互相影响]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/27
 */
public class UseThreadLocal {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 100);

    private static class Task implements Runnable {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            Integer integer = threadLocal.get();
            integer += i;
            threadLocal.set(integer);
            System.out.println(Thread.currentThread().getName()+" i=" + threadLocal.get());
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Task(i),"thread-"+i).start();
            Thread.sleep(50);
        }
        Thread.sleep(1000);
    }
}
