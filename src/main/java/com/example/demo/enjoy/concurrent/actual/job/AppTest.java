package com.example.demo.enjoy.concurrent.actual.job;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Random;

/**
 * [模拟一个应用程序，提交工作和任务，并查询任务进度]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public class AppTest {
    private static final String JOB_NAME = "计算数值";
    private static final int JOB_SIZE = 100;

    private static class Work implements Runnable {

        private PendingJobPool pool;

        public Work(PendingJobPool pool) {
            this.pool = pool;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                List<TaskResult<Object>> taskDetail = pool.getTaskDetail(JOB_NAME);
                if (!taskDetail.isEmpty()) {
                    System.out.println(pool.getTaskProgess(JOB_NAME) + "            " + taskDetail);
                }
                Thread.sleep(100);
            }
        }
    }

    public static void main(String[] args) {
        PendingJobPool pool = PendingJobPool.getInstance();
        //注册一个重复job
        pool.registerJob(JOB_NAME, JOB_SIZE, 50, new MyTask());
        //提交任务进去
        for (int i = 0; i < JOB_SIZE; i++) {
            pool.putTask(JOB_NAME, new Random().nextInt(1000));
        }
        new Thread(new Work(pool)).start();
    }

}
