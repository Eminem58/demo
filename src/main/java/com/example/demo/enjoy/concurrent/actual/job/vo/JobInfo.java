package com.example.demo.enjoy.concurrent.actual.job.vo;

import com.example.demo.enjoy.concurrent.actual.job.core.ITaskProcesser;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * [提交给框架执行的工作实体类,工作：表示本批次需要处理的同性质任务(Task)的一个集合]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public class JobInfo<R> {
    private final String jobName;
    private final Integer jobSize;
    private final long expireTime;
    private AtomicInteger successCount;
    private AtomicInteger taskProcessCount;
    private final ITaskProcesser<?, ?> taskProcesser;
    private LinkedBlockingDeque<TaskResult<R>> taskResultQueue;


    public JobInfo(String jobName, Integer jobSize, long expireTime, ITaskProcesser<?, ?> taskProcesser) {
        this.jobName = jobName;
        this.jobSize = jobSize;
        this.expireTime = expireTime;
        this.successCount = new AtomicInteger(0);
        this.taskProcessCount = new AtomicInteger(0);
        this.taskProcesser = taskProcesser;
        this.taskResultQueue = new LinkedBlockingDeque<>(jobSize);
    }

    public String getTotalProcess() {
        return successCount.get() + "/" + taskProcessCount.get() + "---------job总数=" + jobSize;
    }

    /**
     * 提供工作中所有任务的处理结果
     */
    public List<TaskResult<R>> getJobResult() {
        List<TaskResult<R>> taskResultList = new LinkedList<>();
        TaskResult<R> taskResult;
        //弹出队首是否null
        while ((taskResult = taskResultQueue.pollFirst()) != null) {
            taskResultList.add(taskResult);
        }
        return taskResultList;
    }

    /**
     * 每个任务处理完成后，记录任务的处理结果，因为从业务应用的角度来说，
     * 	 对查询任务进度数据的一致性要不高
     * 	我们保证最终一致性即可，无需对整个方法加锁
     */
    public void addTaskResult(TaskResult<R> taskResult){
        if(TaskResultType.SUCCESS.equals(taskResult.getResultType())){
            successCount.incrementAndGet();
        }
        taskProcessCount.incrementAndGet();
        //尾插
        taskResultQueue.addLast(taskResult);
        if(taskProcessCount.get()==jobSize){

        }
    }

    public Integer getFailedCount() {
        return taskProcessCount.get() - successCount.get();
    }

    public String getJobName() {
        return jobName;
    }

    public Integer getJobSize() {
        return jobSize;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public AtomicInteger getSuccessCount() {
        return successCount;
    }

    public AtomicInteger getTaskProcessCount() {
        return taskProcessCount;
    }

    public ITaskProcesser<?, ?> getTaskProcesser() {
        return taskProcesser;
    }

    public LinkedBlockingDeque<TaskResult<R>> getTaskResultQueue() {
        return taskResultQueue;
    }
}
