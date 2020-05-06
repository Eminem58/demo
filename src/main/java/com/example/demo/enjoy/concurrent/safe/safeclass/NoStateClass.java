package com.example.demo.enjoy.concurrent.safe.safeclass;

/**
 * [无状态的类，无类变量，安全的]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class NoStateClass {
    public int add(int a, int b) {
        return a + b;
    }

    public void handleUser(UserVo userVo) {
        //本类是线程安全的，但是userVo就得自己保证线程安全了
        userVo.setAge(19);
    }
}
