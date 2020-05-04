package com.example.demo.enjoy.concurrent.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * [演示引用类型的原子操作类]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/3
 */
public class UseAtomicReference {
    public static void main(String[] args) {
        User zs = new User("zs",18);
        User li = new User("ls",24);
        AtomicReference<User> atomicReference = new AtomicReference<>(zs);
        atomicReference.compareAndSet(zs,li);
        System.out.println(atomicReference.get());
        System.out.println(zs);
    }


    @Data
    @AllArgsConstructor
    private static class User{
        private String name;
        private Integer age;
    }
}
