package com.example.demo.enjoy.concurrent.safe.safeclass;

/**
 * [类不可变]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class ImmutableClass {
    private final int a;
    private final int b;
    private final UserVo user = new UserVo(2);//不安全

    public int getA() {
        return a;
    }

    public UserVo getUser() {
        return user;
    }


    public ImmutableClass(int a) {
        this.a = a;
        this.b = a;
    }

    public static class User{
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
