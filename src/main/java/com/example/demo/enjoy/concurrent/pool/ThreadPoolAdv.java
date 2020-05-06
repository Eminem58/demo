package com.example.demo.enjoy.concurrent.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * [自定义线程池中线程的创建方式，把线程设置为守护线程]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class ThreadPoolAdv {
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

    private static class MyThreadFactory implements ThreadFactory {

        private AtomicInteger i = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "Mark_" + i.getAndIncrement());
            thread.setDaemon(true);
            System.out.println("创建线程 " + thread);
            return thread;
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new MyThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 5; i++) {
            System.out.println("提交任务" + i + "号");
            pool.execute(new Task("任务" + i + "号"));
        }
        pool.shutdown();
    }

}
