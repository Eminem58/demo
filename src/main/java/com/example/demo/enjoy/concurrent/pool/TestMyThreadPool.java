package com.example.demo.enjoy.concurrent.pool;

import lombok.SneakyThrows;

import java.util.Random;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class TestMyThreadPool {
    public static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @SneakyThrows
        @Override
        public void run() {
            Thread.sleep(new Random().nextInt(1000)+2000);
            System.out.println("任务 " + name + " 完成");
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(2, 3);
        pool.submit(new MyTask("Task A"));
        pool.submit(new MyTask("Task B"));
        pool.submit(new MyTask("Task C"));
        pool.submit(new MyTask("Task D"));
        pool.submit(new MyTask("Task E"));
        System.out.println(pool);
    }
}
