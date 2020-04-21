package com.example.demo.enjoy.concurrent.base;

import com.example.demo.utils.SleepTools;
import lombok.SneakyThrows;

/**
 * [演示Join（）方法的使用]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/19
 */
public class UseJoin {
    private static class Athread implements Runnable{
        private Thread thread;
        public Athread(Thread thread){
            this.thread = thread;
        }
        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"开始排队打饭");
            if(thread!=null){
                thread.join();
            }
            System.out.println(Thread.currentThread().getName()+"打饭完成");
        }
    }
    private static class Bthread implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"开始排队打饭");
            System.out.println(Thread.currentThread().getName()+"打饭完成");
        }
    }
    @SneakyThrows
    public static void main(String[] args) {
        Thread bthread = new Thread(new Bthread(),"bthread线程");
        Thread athread = new Thread(new Athread(bthread),"athread线程");
        athread.start();
        bthread.start();
        System.out.println("main线程开始排队打饭");
        athread.join();
        SleepTools.second(2);
        System.out.println("main线程打饭完成");
    }
}
