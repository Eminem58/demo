package com.example.demo.anotation;

import java.lang.reflect.Method;

/**
 * 〈〉
 *
 * @author jinbiao
 * @create 2019/6/18
 * @since 1.0.0
 */
public class AnotionTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clz = Class.forName("com.example.demo.anotation.User");
        Method[] methods = clz.getDeclaredMethods();
        for (Method m : methods) {
            MyAnnotation annotation = m.getDeclaredAnnotation(MyAnnotation.class);


            System.out.println(annotation.toString());
        }
    }
}
