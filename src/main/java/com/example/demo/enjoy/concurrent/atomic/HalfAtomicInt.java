package com.example.demo.enjoy.concurrent.atomic;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/3
 */
public class HalfAtomicInt {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void increament() {
        int i = atomicInteger.get();
        for (; ; ) {
            if (atomicInteger.compareAndSet(i, ++i)) {
                break;
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increament();
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(atomicInteger.get());
    }
}
