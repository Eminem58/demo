package com.example.demo.enjoy.concurrent.safe.safeclass;

import lombok.Data;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class UserVo {
    private int age;

    public UserVo() {
    }

    public UserVo(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
