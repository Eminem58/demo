package com.example.demo.enjoy.concurrent.bq;

import java.math.BigDecimal;
import java.util.concurrent.DelayQueue;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class PutOrder implements Runnable {
    private DelayQueue<ItemVo<Order>> queue;

    public PutOrder(DelayQueue<ItemVo<Order>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Order order = new Order("1001", new BigDecimal("4999.99"));
        ItemVo<Order> itemVo = new ItemVo<>(1, order);
        queue.offer(itemVo);
        System.out.println("1s超时订单入队");
        Order order2 = new Order("1002", new BigDecimal("1999.99"));
        ItemVo<Order> itemVo2 = new ItemVo<>(2, order2);
        queue.offer(itemVo2);
        System.out.println("2s超时订单入队");
    }
}
