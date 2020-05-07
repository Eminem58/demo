package com.example.demo.enjoy.concurrent.pool;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class WorkTask implements Callable<Integer> {
    private String name;

    public WorkTask() {
    }

    public WorkTask(String name) {
        this.name = name;
    }
    @Override
    public Integer call() throws Exception {
        int sleepTime = new Random().nextInt(1000);
        Thread.sleep(sleepTime);
        return sleepTime;
    }
}
