package com.example.demo.enjoy.concurrent.forkjoin;

import java.util.Random;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/30
 */
public class MakeArray {
    public static final int ARRAY_LENGTH = 400000000;

    public static int[] makeArray() {
        int[] result = new int[ARRAY_LENGTH];
        Random random = new Random();
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(ARRAY_LENGTH * 3);
        }
        return result;
    }
}
