package com.example.demo.enjoy.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/30
 */
public class SumArray extends RecursiveTask<Integer> {
    private static final int THRESHOLD = MakeArray.ARRAY_LENGTH / 100;
    private int[] arr;
    private int fromIndex;
    private int toIndex;

    public SumArray(int[] arr, int fromIndex, int toIndex) {
        this.arr = arr;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    protected Integer compute() {
        int mid = (toIndex + fromIndex) / 2;
        if ((toIndex - fromIndex) > THRESHOLD) {
            SumArray sumArrayLeft = new SumArray(arr, fromIndex, mid);
            SumArray sumArrayRight = new SumArray(arr, mid + 1, toIndex);
            invokeAll(sumArrayLeft, sumArrayRight);
            return sumArrayLeft.join() + sumArrayRight.join();
        } else {
            int result = 0;
            for (int i = fromIndex; i <= toIndex; i++) {
                result += arr[i];
            }
            return result;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] makeArray = MakeArray.makeArray();
        SumArray sumArrayTask = new SumArray(makeArray, 0, makeArray.length - 1);
        long start = System.currentTimeMillis();
        forkJoinPool.invoke(sumArrayTask);
        System.out.println("sum=" + sumArrayTask.join() + "  fork join线程耗时=" + (System.currentTimeMillis() - start) + "ms");
    }
}
