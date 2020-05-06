package com.example.demo.enjoy.concurrent.bq;

import lombok.SneakyThrows;

import java.util.concurrent.DelayQueue;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class FetchOrder implements Runnable {

    private DelayQueue<ItemVo<Order>> delayQueue;

    public FetchOrder(DelayQueue<ItemVo<Order>> delayQueue) {
        this.delayQueue = delayQueue;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            ItemVo<Order> itemVo = delayQueue.take();
            System.out.println(itemVo.getData() + "-------超时订单出队-------到期时间=" + itemVo.getActiveTime());
        }
    }
}
