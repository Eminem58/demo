package com.example.demo.enjoy.concurrent.wn;

import lombok.SneakyThrows;

/**
 * [测试wait/notify/notifyAll]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/28
 */
public class TestWN {
    private static class Task implements Runnable{
        private Express express;

        public Task(Express express) {
            this.express = express;
        }

        @Override
        public void run() {
            express.waitSiteEnd();
        }
    }


    @SneakyThrows
    public static void main(String[] args) {
        Express express = new Express("广州");
        for (int i = 0; i < 3; i++) {
            new Thread(new Task(express)).start();
        }

        Thread.sleep(1000);
        express.changeSiteAndNotifyCustomer();
    }
}
