package com.example.demo.enjoy.concurrent.pool;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * [连接池的实现]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/21
 */
public class DBPool {
    /**
     * 容器，存放连接
     */
    private static LinkedList<Connection> queue = new LinkedList<>();

    public DBPool(int initSize) {
        if (initSize > 0) {
            for (int i = 0; i < initSize; i++) {
                queue.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    @SneakyThrows
    public Connection fetchConnection(long timeout) {
        Connection connection = null;
        //等待时间
        long remain = timeout;
        long future = System.currentTimeMillis() + timeout;
        synchronized (queue) {
            while (queue.isEmpty() && remain > 0) {
                //阻塞
                queue.wait(remain);
                //唤醒一次重新计算等待时间
                remain = future - System.currentTimeMillis();
            }
            if (!queue.isEmpty()) {
                connection = queue.removeFirst();
            }
        }
        return connection;
    }

    /**
     * 释放连接,通知其他的等待连接的线程
     */
    public void releaseConnection(Connection connection){
        if(connection!=null){
            synchronized(queue){
                queue.addLast(connection);
                //通知其他等待连接的线程
                queue.notifyAll();
            }
        }
    }
}
