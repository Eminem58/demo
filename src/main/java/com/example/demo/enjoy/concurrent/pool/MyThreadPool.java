package com.example.demo.enjoy.concurrent.pool;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * [自定义线程池实现]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class MyThreadPool {
    //默认工作线程3个
    private static final int WORK_THREAD_SIZE = 5;
    //默认任务数量100个
    private static final int TASK_QUEUE_SIZE = 100;
    private BlockingQueue<Runnable> taskQueue;
    private WorkThread[] workThreads;

    public MyThreadPool(int workThreadSize, int taskQueueSize) {
        if (workThreadSize <= 0) {
            workThreadSize = WORK_THREAD_SIZE;
        }
        if (taskQueueSize <= 0) {
            taskQueueSize = TASK_QUEUE_SIZE;
        }
        this.workThreads = new WorkThread[workThreadSize];
        this.taskQueue = new ArrayBlockingQueue<>(taskQueueSize);
        for (int i = 0; i < workThreadSize; i++) {
            new Thread(new WorkThread()).start();
        }
    }

    /**
     * 工作线程
     */
    private class WorkThread implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            Runnable task;
            while (!Thread.currentThread().isInterrupted()) {
                task = taskQueue.take();
                System.out.println("线程"+Thread.currentThread().getId() + "开始执行" + ((TestMyThreadPool.MyTask) task).getName());
                task.run();
            }
        }

        public void destroy() {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 提交任务
     */
    public void submit(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 销毁线程池
     */
    public void destroyPool() {
        for (int i = 0; i < workThreads.length; i++) {
            workThreads[i].destroy();
            workThreads[i] = null;
        }
        taskQueue.clear();
    }

    @Override
    public String toString() {
        return "wait task number:" + taskQueue.size();
    }
}
