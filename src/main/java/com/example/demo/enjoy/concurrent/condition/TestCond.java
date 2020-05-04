package com.example.demo.enjoy.concurrent.condition;

import com.example.demo.enjoy.concurrent.wn.Express;
import com.example.demo.enjoy.concurrent.wn.TestWN;
import lombok.SneakyThrows;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/3
 */
public class TestCond {
    private static class Task implements Runnable{
        private ExpressCond express;

        public Task(ExpressCond express) {
            this.express = express;
        }

        @Override
        public void run() {
            express.waitSiteEnd();
        }
    }


    @SneakyThrows
    public static void main(String[] args) {
        ExpressCond express = new ExpressCond("广州");
        for (int i = 0; i < 3; i++) {
            new Thread(new Task(express)).start();
        }

        Thread.sleep(1000);
        express.changeSiteAndNotifyCustomer();
    }
}
