package com.example.demo.enjoy.jvm;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/8
 */
public class Test {
    public static void main(String[] args)
            throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
            int i = 1/0;
            return 100;
        });
        //future.get();
        future.join();
    }
}
