package com.example.demo.anotation;


/**
 * 〈〉
 *
 * @author jinbiao
 * @create 2019/6/10
 * @since 1.0.0
 */

public class User {
    @MyAnnotation(userId = 2, userName = "张三", arrays = { "a","b"})
    public void add() {
        System.out.println("addUser");
    }

}
