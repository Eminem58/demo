package com.example.demo.enjoy.concurrent.actual.job;

import lombok.SneakyThrows;

import java.util.Random;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public class MyTask implements ITaskProcesser<Integer, Integer> {

    @SneakyThrows
    @Override
    public TaskResult<Integer> taskExecute(Integer integer) {
        int time = new Random().nextInt(500);
        Thread.sleep(time);
        if (time <= 300) {
            return new TaskResult<>(TaskResultType.SUCCESS, integer.intValue() + time, null);
        } else if (time > 300 && time <= 400) {
            return new TaskResult<>(TaskResultType.FAILE, -1, "业务异常");
        } else {
            try {
                throw new Exception("异常发生了！！");
            } catch (Exception e) {
                return new TaskResult<>(TaskResultType.EXCEPTION, -1, e.getMessage());
            }
        }
    }
}
