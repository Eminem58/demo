package com.example.demo.enjoy.concurrent.base;

/**
 * [中断线程]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/19
 */
public class EndThread {
    /*private static class UseThread extends Thread{

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+" interrrupt flag ="+isInterrupted());
            while(!isInterrupted()){
                //while(!Thread.interrupted()){
                //while(true){
                System.out.println(threadName+" is running");
                System.out.println(threadName+"inner interrrupt flag ="
                        +isInterrupted());
            }
            System.out.println(threadName+" interrrupt flag ="+isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();//中断线程，其实设置线程的标识位true


    }*/
}
