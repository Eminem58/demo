package com.example.demo.enjoy.concurrent.atomic;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * [演示带版本戳的原子操作类]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/3
 */
public class UseAtomicStampedReference {
    /**
     * 共享的原子操作类
     */
    private static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("A", 0);

    @SneakyThrows
    public static void main(String[] args) {
        //版本号
        final int stamp = atomicStampedReference.getStamp();
        //内容
        final String reference = atomicStampedReference.getReference();


        Thread thread1 = new Thread(() ->
                System.out.println(Thread.currentThread().getName() + " 当前版本号=" + stamp + "； 当前内容=" + reference
                        + "； 修改结果=" + atomicStampedReference.compareAndSet(reference, "a", stamp, stamp + 1))
        );

        Thread thread2 = new Thread(() ->
                System.out.println(Thread.currentThread().getName() + " 当前版本号=" + atomicStampedReference.getStamp() + "； 当前内容=" + atomicStampedReference.getReference()
                        + "； 修改结果=" + atomicStampedReference.compareAndSet(reference, "a", stamp, stamp + 1)));


        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
    }
}
