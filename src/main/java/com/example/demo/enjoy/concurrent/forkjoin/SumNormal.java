package com.example.demo.enjoy.concurrent.forkjoin;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/30
 */
public class SumNormal {
    public static void main(String[] args) {
        int sum = 0;
        int[] makeArray = MakeArray.makeArray();
        long startMs = System.currentTimeMillis();
        for (int i = 0; i < makeArray.length; i++) {
            sum += makeArray[i];
        }
        System.out.println("sum="+sum+"  单线程耗时="+(System.currentTimeMillis()-startMs)+"ms");

    }
}
