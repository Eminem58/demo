package com.example.demo.utils;

import java.util.concurrent.TimeUnit;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/20
 */
public class SleepTools {
    /**
     * 按秒休眠
     * @param seconds 秒数
     */
    public static final void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }

    /**
     * 按毫秒数休眠
     * @param seconds 毫秒数
     */
    public static final void ms(int seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
