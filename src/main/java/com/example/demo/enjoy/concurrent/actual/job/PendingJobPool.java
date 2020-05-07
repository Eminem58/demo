package com.example.demo.enjoy.concurrent.actual.job;

import lombok.SneakyThrows;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.concurrent.*;

/**
 * [框架的主体类，也是调用者主要使用的类]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public class PendingJobPool {
    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors();
    private static ExecutorService pool = new ThreadPoolExecutor(THREAD_SIZE, THREAD_SIZE, 3000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5000));
    /**
     * 工作信息的存放容器
     */
    private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap = new ConcurrentHashMap<>();

    private PendingJobPool() {
    }

    private static class PendingJobPoolHolder {
        private static PendingJobPool pendingJobPool = new PendingJobPool();
    }

    public static PendingJobPool getInstance() {
        return PendingJobPoolHolder.pendingJobPool;
    }


    /**
     * 对工作中的任务进行包装，提交给线程池使用，
     * 并将处理任务的结果，写入缓存以供查询
     */
    private static class PendingTask<T, R> implements Runnable {
        private T data;
        private JobInfo<R> jobInfo;

        public PendingTask(T data, JobInfo<R> jobInfo) {
            this.data = data;
            this.jobInfo = jobInfo;
        }

        @Override
        public void run() {
            ITaskProcesser<T, R> taskProcesser = (ITaskProcesser<T, R>) jobInfo.getTaskProcesser();
            jobInfo.addTaskResult(taskProcesser.taskExecute(data));
        }
    }

    /*根据工作名称检索工作*/
    @SneakyThrows
    private <R> JobInfo<R> getJob(String jobName) {
        JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);
        if (jobInfo == null) {
            throw new Exception("没有该job:" + jobName);
        }
        return jobInfo;
    }

    /*获得工作的整体处理进度*/
    public <R> String getTaskProgess(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTotalProcess();
    }

    /*获得每个任务的处理详情*/
    public <R> List<TaskResult<R>> getTaskDetail(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getJobResult();
    }

    /**
     * 调用者提交工作中的任务
     */
    public <T, R> void putTask(String jobName, T data) {
        JobInfo<T> jobInfo = getJob(jobName);
        PendingTask<T, R> pendingTask = new PendingTask(data, jobInfo);
        pool.execute(pendingTask);
    }

    /**
     * 调用者注册工作，如工作名，任务的处理器等等
     */
    public <R> void registerJob(String jobName, int jobLength, long expireTime, ITaskProcesser<?, ?> taskProcesser) {
        JobInfo<R> jobInfo = new JobInfo<>(jobName, jobLength, expireTime, taskProcesser);
        if (jobInfoMap.putIfAbsent(jobName, jobInfo) != null) {
            throw new RuntimeException(jobName + "已经注册了");
        }
    }

    public static ConcurrentHashMap<String, JobInfo<?>> getJobInfoMap() {
        return jobInfoMap;
    }

}
