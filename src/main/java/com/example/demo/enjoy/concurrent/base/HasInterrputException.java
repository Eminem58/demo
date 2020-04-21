package com.example.demo.enjoy.concurrent.base;

/**
 * [阻塞方法中抛出InterruptedException异常后，如果需要继续中断，需要手动再中断一次]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/19
 */
public class HasInterrputException {
    private static class UseThread extends Thread{
        public UseThread(String name){
            super(name);
        }
        @Override
        public void run() {
            while (!isInterrupted()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+" interruptFlag:"+isInterrupted());
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println("useThread is running");
            }
            System.out.println(Thread.currentThread().getName()+" interruptFlag:"+isInterrupted());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread useThread = new UseThread("useThread");
        useThread.start();
        Thread.sleep(500);
        useThread.interrupt();
    }
}
