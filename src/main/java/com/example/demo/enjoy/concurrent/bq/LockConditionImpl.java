package com.example.demo.enjoy.concurrent.bq;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class LockConditionImpl<E> implements IBoundedBuffer<E> {

    private List<E> list = new LinkedList<>();
    private int limit;

    public LockConditionImpl(int limit) {
        this.limit = limit;
    }

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    @SneakyThrows
    @Override
    public void put(E data) {
        lock.lock();
        try {
            while (list.size() >= limit) {
                notFull.await();
            }
            list.add(data);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }


    }

    @SneakyThrows
    @Override
    public E take() {
        lock.lock();
        try {
            E remove;
            while (list.size() <= 0) {
                notEmpty.await();
            }
            remove = list.remove(0);
            notFull.signalAll();
            return remove;
        } finally {
            lock.unlock();
        }
    }
}
