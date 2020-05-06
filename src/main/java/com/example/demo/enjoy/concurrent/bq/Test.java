package com.example.demo.enjoy.concurrent.bq;

import lombok.SneakyThrows;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.concurrent.DelayQueue;

/**
 * [延时队列测试]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class Test {
    @SneakyThrows
    @org.junit.Test
    public void test() {
        DelayQueue<ItemVo<Order>> delayQueue = new DelayQueue<>();
        new Thread(new PutOrder(delayQueue)).start();
        new Thread(new FetchOrder(delayQueue)).start();

        Thread.sleep(5000);
    }


    private static class PutWork implements Runnable {
        private IBoundedBuffer<Integer> boundedBuffer;

        public PutWork(IBoundedBuffer<Integer> boundedBuffer) {
            this.boundedBuffer = boundedBuffer;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                int item = new Random().nextInt(100);
                boundedBuffer.put(item);
                System.out.println(Thread.currentThread().getName() + "：list["+i+"] put=" + item);
                Thread.sleep(100);
            }
        }
    }

    private static class TackWork implements Runnable {
        private IBoundedBuffer<Integer> boundedBuffer;

        public TackWork(IBoundedBuffer<Integer> boundedBuffer) {
            this.boundedBuffer = boundedBuffer;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                Integer take = boundedBuffer.take();
                System.out.println(Thread.currentThread().getName() + "：take=" + take);
                Thread.sleep(100);
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        IBoundedBuffer boundedBuffer = new SemphoreImpl(3);
        for (int i = 0; i < 3; i++) {
            new Thread(new PutWork(boundedBuffer)).start();
        }
        new Thread(new TackWork(boundedBuffer)).start();
        Thread.sleep(500);
    }
}
