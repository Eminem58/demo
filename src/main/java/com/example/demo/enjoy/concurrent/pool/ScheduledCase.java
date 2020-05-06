package com.example.demo.enjoy.concurrent.pool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * [演示ScheduledThreadPoolExecutor的用法]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class ScheduledCase {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        //延时Runnable任务（仅执行一次）
        scheduledThreadPoolExecutor.schedule(() -> System.out.println("任务只执行一次"), 1000, TimeUnit.MILLISECONDS);

        //固定延时间隔执行的任务，完成后开始计时
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            System.out.println("固定延时间隔执行的任务start：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("固定延时间隔执行的任务end：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        }, 0, 2000, TimeUnit.MILLISECONDS);

        //固定时间间隔执行的任务,理论上第二次任务在5000ms后执行，第三次在5000*2ms后执行
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            System.out.println("-----固定时间间隔执行的任务start：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            try {
                Thread.sleep(new Random().nextInt(3000));
                //Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----固定时间间隔执行的任务end：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }, 0, 5000, TimeUnit.MILLISECONDS);


    }
}
