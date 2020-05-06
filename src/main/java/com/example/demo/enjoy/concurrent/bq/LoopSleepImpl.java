package com.example.demo.enjoy.concurrent.bq;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.List;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class LoopSleepImpl<E> implements IBoundedBuffer<E> {
    private List<E> list = new LinkedList<>();
    private int limit;

    public LoopSleepImpl(int limit) {
        this.limit = limit;
    }

    @SneakyThrows
    @Override
    public void put(E data) {
        while (true) {
            synchronized (this) {
                if (list.size() < limit) {
                    list.add(data);
                    return;
                }
            }
            Thread.sleep(50);
        }
    }

    @SneakyThrows
    @Override
    public E take() {
        E data;
        while (true) {
            synchronized (this){
                if (list.size() > 0) {
                    data = list.remove(0);
                    return data;
                }
            }
            Thread.sleep(50);
        }
    }
}
