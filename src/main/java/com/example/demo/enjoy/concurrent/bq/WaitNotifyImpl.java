package com.example.demo.enjoy.concurrent.bq;

import lombok.SneakyThrows;

import java.util.LinkedList;

/**
 * [等待通知模式实现阻塞队列]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class WaitNotifyImpl<E> implements IBoundedBuffer<E>{

    private LinkedList<E> linkedList = new LinkedList<>();
    private int limit;

    public WaitNotifyImpl(int limit) {
        this.limit = limit;
    }

    @SneakyThrows
    @Override
    public synchronized void put(E data) {
        while (linkedList.size()>=limit){
            wait();
        }
        linkedList.addLast(data);
        notifyAll();
    }

    @SneakyThrows
    @Override
    public synchronized E take() {
        while (linkedList.size()<=0){
            wait();
        }
        E first = linkedList.removeFirst();
        notifyAll();
        return first;
    }
}
