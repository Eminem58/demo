package com.example.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * [堆内存溢出]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/2/20
 */
public class HeapOom {
    byte[] b = new byte[1024 * 100];

    public static void main(String[] args) throws InterruptedException {
        List<HeapOom> list = new ArrayList<>();
        while (true){
            list.add(new HeapOom());
            Thread.sleep(10);
        }
    }

}
