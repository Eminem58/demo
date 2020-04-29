package com.example.demo.enjoy.concurrent.vola;

import lombok.SneakyThrows;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/27
 */
public class NotSafe {
    private volatile int i;
    public void increase(){
        i++;
    }
    private static class Task implements Runnable{

        private NotSafe notSafe;
        public Task(NotSafe notSafe){
            this.notSafe=notSafe;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                notSafe.increase();
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        NotSafe notSafe = new NotSafe();
        new Thread(new Task(notSafe)).start();
        new Thread(new Task(notSafe)).start();
        Thread.sleep(1000);
        System.out.println(notSafe.i);
    }
}
