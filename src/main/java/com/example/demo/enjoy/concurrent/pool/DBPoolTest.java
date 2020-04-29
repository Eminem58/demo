package com.example.demo.enjoy.concurrent.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/21
 */
public class DBPoolTest {

    /**
     * 连接池
     */
    private static DBPool pool = new DBPool(10);
    /**
     * 控制main线程将会等待所有Woker结束后才能继续执行
     */
    private static CountDownLatch countDownLatch;

    private static class Worker implements Runnable {
        private int times;
        private AtomicInteger got;
        private AtomicInteger notgot;

        public Worker(int times, AtomicInteger got, AtomicInteger notgot) {
            this.times = times;
            this.got = got;
            this.notgot = notgot;
        }

        @Override
        public void run() {
            while (times > 0) {
                try {
                    Connection connection = pool.fetchConnection(50);
                    if (connection != null) {
                        try {
                            connection.createStatement();
//                            PreparedStatement preparedStatement = connection.prepareStatement("select user_id from user");
//                            preparedStatement.execute();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notgot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + "等待连接超时");
                    }
                } catch (Exception ex) {

                } finally {
                    times--;
                }
            }
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int times = 20;
        int threadCount = 50;
        countDownLatch = new CountDownLatch(threadCount);
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notgot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(times, got, notgot), "worker" + i);
            thread.start();
        }
        countDownLatch.await();
        System.out.println("总共尝试了：" + times * threadCount);
        System.out.println("拿到连接的次数：" + got);
        System.out.println("没拿到连接的次数：" + notgot);
    }
}
