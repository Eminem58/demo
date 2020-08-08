package com.example.demo.enjoy.concurrent.actual.job.core;

import com.example.demo.enjoy.concurrent.actual.job.vo.TaskResult;

/**
 * 实现可查询进度的并发任务执行框架
 *
 * 要求框架使用者实现的任务接口，因为任务的性质在调用时才知道，
 * 所以传入的参数和方法的返回值均使用泛型
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public interface ITaskProcesser<T, R> {
    TaskResult<R> taskExecute(T t);
}
