package com.example.demo.enjoy.concurrent.bq;

/**
 * [有界缓冲池接口，实现阻塞队列]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public interface IBoundedBuffer<T> {
    void put(T data);
    T take();
}
