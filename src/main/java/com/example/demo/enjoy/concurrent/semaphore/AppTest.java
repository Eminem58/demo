package com.example.demo.enjoy.concurrent.semaphore;

import com.example.demo.utils.SleepTools;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/30
 */
public class AppTest {
    private static DBPoolSemaphore pool = new DBPoolSemaphore();
    private static class Work implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Connection connection = pool.getConnection();
            System.out.println(Thread.currentThread().getName() + " 获取连接耗时=" + (System.currentTimeMillis() - start) + "ms");
            Thread.sleep(new Random().nextInt(100));
            System.out.println("查询数据库完成，归还连接。");
            pool.putConnection(connection);
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(new Work()).start();
        }
        Thread.sleep(2000);
    }
}
