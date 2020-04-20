package com.example.demo;

import com.example.demo.async.AsyncMethond;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 *  [] 
 *  @author 金彪
 *  @date 2019年11月19日
 *  @version 1.0
 *  
 */
public class ThreadTest extends DemoApplicationTests{
    @Autowired
    AsyncMethond asyncMethond;

    @Test
    public void test3() throws Exception {
        System.out.println("main函数开始执行");
        asyncMethond.longtime();
        System.out.println("main函数执行结束");
    }


    @Test
    public void test(){
        System.out.println("main函数开始执行");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("===task start===");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===task finish===");
                return 3;
            }
        }, executor);
        future.thenAccept(e -> System.out.println(e));
        System.out.println("main函数执行结束");
    }
}
