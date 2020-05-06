package com.example.demo.enjoy.concurrent.pool;

import com.example.demo.utils.SleepTools;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import javafx.concurrent.Worker;
import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.*;

/**
 * [扩展线程池的使用范例]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class ThreadPoolExt {
    private static class Task implements Runnable {

        String name;

        public Task(String name) {
            this.name = name;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始执行任务 " + name);
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder().build(), new ThreadPoolExecutor.DiscardOldestPolicy()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("before Execute "+((Task)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("afteer Execute"+((Task)r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程退出");
            }
        };

        for (int i = 0; i < 5; i++) {
            System.out.println("提交任务" + i + "号");
            pool.execute(new Task("任务" + i + "号"));
        }
        pool.shutdown();
    }
}
