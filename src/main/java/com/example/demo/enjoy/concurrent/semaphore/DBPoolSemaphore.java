package com.example.demo.enjoy.concurrent.semaphore;

import com.example.demo.enjoy.concurrent.pool.SqlConnectImpl;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.Semaphore;

/**
 * [信号量：实现流控，协调线程]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/30
 */
public class DBPoolSemaphore {
    /**
     * 空闲连接和忙碌连接
     */
    private final Semaphore useful, useless;
    private static LinkedList<Connection> pool = new LinkedList<>();

    public DBPoolSemaphore() {
        this.useful = new Semaphore(10);
        this.useless = new Semaphore(0);
    }

    //初始化10个连接
    static {
        for (int i = 0; i < 10; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }

    /**
     * 拿连接
     */
    @SneakyThrows
    public Connection getConnection() {
        //-1等待wait
        useful.acquire();

        Connection connection;
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        //+1通知notify
        useless.release();
        return connection;
    }

    /**
     * 归还连接
     */
    @SneakyThrows
    public void putConnection(Connection connection) {
        if (!ObjectUtils.isEmpty(connection)) {
            System.out.println("当前有" + useful.getQueueLength() + "个线程等待数据库连接，可用连接数=" + useful.availablePermits());
            useless.acquire();
            synchronized (pool) {
                pool.addLast(connection);
            }
            useful.release();
        }
    }


}
