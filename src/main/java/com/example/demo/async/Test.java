package com.example.demo.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  [] 
 *  @author 金彪
 *  @date 2019年11月19日
 *  @version 1.0
 *  
 */
public class Test {

    public static void main(String[] args) {

        System.out.println("main函数开始执行");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
            System.out.println("===task start===");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("===task finish===");
            return 3;
        }, executor);
        future.thenAccept(e -> System.out.println(e));
        System.out.println("main函数执行结束");
    }
}
