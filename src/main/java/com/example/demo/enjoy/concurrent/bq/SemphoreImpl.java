package com.example.demo.enjoy.concurrent.bq;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class SemphoreImpl<E> implements IBoundedBuffer<E> {

    private Semaphore items, spaces;
    private List<E> list = new LinkedList<>();

    public SemphoreImpl(int capacity) {
        this.items = new Semaphore(0);
        this.spaces = new Semaphore(capacity);
    }

    @SneakyThrows
    @Override
    public void put(E data) {
        spaces.acquire();
        synchronized (this) {
            list.add(data);
        }
        items.release();
    }

    @SneakyThrows
    @Override
    public E take() {
        items.acquire();
        E remove;
        synchronized (this){
            remove = list.remove(0);

        }
        spaces.release();
        return remove;
    }
}
