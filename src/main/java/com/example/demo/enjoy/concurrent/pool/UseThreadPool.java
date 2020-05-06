package com.example.demo.enjoy.concurrent.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * [线程池的使用范例]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class UseThreadPool {
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


    private static class TaskCall implements Callable<String> {
        private String name;

        public TaskCall(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "开始执行任务 " + name);
            Thread.sleep(500);
            return name + " 执行结果";
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder().build(), new ThreadPoolExecutor.AbortPolicy());

        ExecutorService pool1 = Executors.newSingleThreadExecutor();
        ExecutorService pool2 = Executors.newFixedThreadPool(2);
        ExecutorService pool3 = Executors.newCachedThreadPool();
        ExecutorService pool4 = Executors.newWorkStealingPool();
        ScheduledExecutorService pool5 = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService poo6 = Executors.newSingleThreadScheduledExecutor();

        for (int i = 0; i < 5; i++) {
            System.out.println("提交任务" + i + "号");
            pool.execute(new Task("任务" + i + "号"));
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("提交回调任务" + i + "号");
            Future<String> result = pool.submit(new TaskCall("回调任务" + i + "号"));
            System.out.println(result.get());
        }
        pool.shutdown();
    }


}
