package com.example.demo.enjoy.concurrent.future;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * [演示Future等的使用]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/29
 */
public class UseFuture {
    private static class Task implements Callable<Integer> {

        private int sum;

        @Override
        public Integer call() {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("callable线程中断");
                return null;
            }
            for (int i = 0; i < 101; i++) {
                sum += i;
                System.out.println("sum=" + sum);
            }
            return sum;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Task());
        new Thread(integerFutureTask).start();
        Thread.sleep(100);
        if (new Random().nextInt(10) > 5) {
            System.out.println("结果 = " + integerFutureTask.get());
        } else {
            System.out.println("cancle..........");
            integerFutureTask.cancel(true);
        }
    }

}
