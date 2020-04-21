package com.example.demo.enjoy.concurrent.base;

/**
 * [守护线程的使用]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/19
 */
public class DaemonThread {
    private static class UseThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " is running");
                }
            } finally {
                //守护线程中finally不一定起作用
                System.out.println(".............finally");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
        useThread.setName("useThread");
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(5);
        //useThread.interrupt();
    }
}
