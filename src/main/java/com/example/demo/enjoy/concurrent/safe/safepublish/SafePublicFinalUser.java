package com.example.demo.enjoy.concurrent.safe.safepublish;

import com.example.demo.enjoy.concurrent.safe.safeclass.UserVo;

/**
 * [委托给线程安全的类来做，貌似有问题，委托模式不是很理解？]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class SafePublicFinalUser {
    private SynUserVo synUserVo;

    public SafePublicFinalUser(UserVo userVo) {
        this.synUserVo = new SynUserVo(userVo);
    }

    public SynUserVo getUserVo() {
        return synUserVo;
    }

    private static class SynUserVo {
        private final UserVo userVo;
        private final Object lock = new Object();

        public SynUserVo(UserVo userVo) {
            this.userVo = userVo;
        }

        public int getAge() {
            synchronized (lock) {
                return userVo.getAge();
            }
        }

        public void setAge(int age) {
            synchronized (lock) {
                userVo.setAge(age);
            }
        }
    }
}
