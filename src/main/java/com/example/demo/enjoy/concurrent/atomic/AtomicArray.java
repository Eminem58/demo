package com.example.demo.enjoy.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/3
 */
public class AtomicArray {
    private static int[] arr = new int[]{1, 2};
    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(arr);
    public static void main(String[] args) {
        atomicIntegerArray.compareAndSet(1,2,0);
        atomicIntegerArray.incrementAndGet(0);
        System.out.println(atomicIntegerArray.get(1));
        System.out.println(atomicIntegerArray.toString());
        //原始数组不变
        System.out.println(arr[1]);
    }


}
