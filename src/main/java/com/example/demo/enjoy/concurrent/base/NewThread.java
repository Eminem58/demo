package com.example.demo.enjoy.concurrent.base;

/**
 * [创建线程2种方式]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/19
 */
public class NewThread {

    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            System.out.println("useThread");
            String name = Thread.currentThread().getName();
            while (!isInterrupted()) {
                System.out.println(name + "---interruptFlag:" + isInterrupted());
            }
            System.out.println(name + "---interruptFlag:" + isInterrupted());
        }
    }

    private static class UseRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("useRunnable");
            String name = Thread.currentThread().getName();
            //这个不行
            //boolean interrupted = Thread.currentThread().isInterrupted();
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(name + "---interruptFlag:" + Thread.currentThread().isInterrupted());
            }
            System.out.println(name + "---interruptFlag:" + Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread("useThread");
        useThread.start();
        //主线程等下子线程
        Thread.sleep(10);
        useThread.interrupt();
        Thread thread = new Thread(new UseRunnable(),"useRunnable");
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
    }
}
