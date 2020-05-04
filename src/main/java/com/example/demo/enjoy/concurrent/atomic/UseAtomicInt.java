package com.example.demo.enjoy.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * [原子操作类]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/3
 */
public class UseAtomicInt {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) {
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.compareAndSet(0,2));
        System.out.println(atomicInteger.get());
    }
}
