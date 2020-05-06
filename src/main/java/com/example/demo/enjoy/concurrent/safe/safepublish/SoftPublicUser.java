package com.example.demo.enjoy.concurrent.safe.safepublish;

import com.example.demo.enjoy.concurrent.safe.safeclass.UserVo;

/**
 * [仿Collections对容器的包装，将内部成员对象进行线程安全包装]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class SoftPublicUser {

    private final UserVo userVo;

    public SoftPublicUser(UserVo userVo) {
        this.userVo = new SynUserVo(userVo);
    }

    public UserVo getUserVo() {
        return userVo;
    }

    private static class SynUserVo extends UserVo {
        private final UserVo userVo;
        private final Object lock = new Object();

        public SynUserVo(UserVo userVo) {
            this.userVo = userVo;
        }

        @Override
        public void setAge(int age) {
            synchronized (lock) {
                userVo.setAge(age);
            }
        }

        @Override
        public int getAge() {
            synchronized (lock) {
                return userVo.getAge();
            }
        }
    }
}
