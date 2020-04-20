package com.example.demo.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *  [] 
 *  @author 金彪
 *  @date 2019年11月19日
 *  @version 1.0
 *  
 */
@Service
public class AsyncMethond {
    @Async
    public void longtime() {
        System.out.println("我在执行一项耗时任务");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("完成");
    }
}
