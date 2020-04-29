package com.example.demo.enjoy.concurrent.wn;

import lombok.SneakyThrows;

/**
 * [快递实体类]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/28
 */
public class Gun {

    private int zd;

    @SneakyThrows
    public synchronized void shootZd() {
        while (zd <= 0) {
            System.out.println(Thread.currentThread().getName() + "：子弹已经射完了，当前子弹剩余=" + zd);
            wait();
        }
        zd--;
        System.out.println(Thread.currentThread().getName() + "：发射一枚子弹，当前子弹剩余=" + zd);
        notifyAll();
    }

    @SneakyThrows
    public synchronized void putZd() {
        while (zd >= 20) {
            System.out.println(Thread.currentThread().getName() + "：子弹装满了，当前子弹剩余=" + zd);
            wait();
        }
        zd++;
        System.out.println(Thread.currentThread().getName() + "：装入一枚子弹，当前子弹剩余=" + zd);
        notifyAll();
    }

    private static class ConsumerTask implements Runnable {
        private Gun gun;

        public ConsumerTask(Gun gun) {
            this.gun = gun;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                gun.shootZd();
                Thread.sleep(8);
            }
        }
    }

    private static class ProductTask implements Runnable {
        private Gun gun;

        public ProductTask(Gun gun) {
            this.gun = gun;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                gun.putZd();
                Thread.sleep(3);
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        Gun gun = new Gun();
        for (int i = 0; i < 5; i++) {
            new Thread(new ConsumerTask(gun), "消费者" + i).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new ProductTask(gun), "生产者" + i).start();
        }
    }
}
