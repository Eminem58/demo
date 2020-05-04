package com.example.demo.enjoy.concurrent.lock;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * [读写锁]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/3
 */
public class UseRwLock implements IGoodsService{
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    private GoodsInfo goodsInfo;

    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @SneakyThrows
    @Override
    public Integer getNum() {
        readLock.lock();
        try {
            Thread.sleep(1000);
            return goodsInfo.getNum();
        } finally {
            readLock.unlock();
        }
    }

    @SneakyThrows
    @Override
    public void setNum(int num) {
        writeLock.lock();
        try {
            Thread.sleep(1000);
            goodsInfo.setNum(goodsInfo.getNum() + num);
        } finally {
            writeLock.unlock();
        }
    }


}
