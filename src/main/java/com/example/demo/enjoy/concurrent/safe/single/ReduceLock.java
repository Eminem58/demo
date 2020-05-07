package com.example.demo.enjoy.concurrent.safe.single;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * [缩小锁的范围]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public class ReduceLock {
    private Map<String, String> map = new HashMap<>();

    public synchronized boolean isMatch(String name, String regexp) {
        String string = map.get(name);
        return Pattern.matches(regexp, string);
    }

    public boolean isMatchReduce(String name, String regexp) {
        String string;
        synchronized (this) {
            string = map.get(name);
        }
        return Pattern.matches(regexp, string);
    }
}
