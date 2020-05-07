package com.example.demo.enjoy.concurrent.pool;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * [CompletionService可以并行把结果放进结果集]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class CompletionCase {
    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int TASK_SIZE = POOL_SIZE * 10;

    @SneakyThrows
    public void testByQueue() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(TASK_SIZE));
        LinkedBlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<>();
        AtomicInteger sum = new AtomicInteger(0);

        long start = System.currentTimeMillis();
        for (int i = 0; i < TASK_SIZE; i++) {
            Future<Integer> future = pool.submit(new WorkTask());
            queue.add(future);
        }
        for (int i = 0; i < queue.size(); i++) {
            sum.addAndGet(queue.take().get());
        }
        pool.shutdown();
        System.out.println("testByQueue  sleep=" + sum + "ms---------spend=" + (System.currentTimeMillis() - start) + "ms");
    }


    @SneakyThrows
    public void testByCompletionCase() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(TASK_SIZE));
        LinkedBlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<>();
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(pool);
        AtomicInteger sum = new AtomicInteger(0);

        long start = System.currentTimeMillis();
        for (int i = 0; i < TASK_SIZE; i++) {
            queue.add(completionService.submit(new WorkTask()));
        }
        for (int i = 0; i < queue.size(); i++) {
            sum.addAndGet(queue.take().get());
        }
        pool.shutdown();
        System.out.println("testByCompletionCase  sleep=" + sum + "ms---------spend=" + (System.currentTimeMillis() - start) + "ms");
    }

    public static void main(String[] args) {
        CompletionCase completionCase = new CompletionCase();
        completionCase.testByQueue();
        completionCase.testByCompletionCase();
    }
}
