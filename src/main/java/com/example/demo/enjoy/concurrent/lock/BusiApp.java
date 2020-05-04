package com.example.demo.enjoy.concurrent.lock;

import lombok.SneakyThrows;

import java.util.Random;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/3
 */
public class BusiApp {
    private static class ReadWork implements Runnable{
        private GoodsInfo goodsInfo;

        public ReadWork(GoodsInfo goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        @SneakyThrows
        @Override
        public void run() {
            long start = System.currentTimeMillis();
            goodsInfo.getNum();
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName()+"：num="+goodsInfo.getNum()+"    读商品耗时="+(System.currentTimeMillis()-start)+"ms");
        }
    }
    private static class WriteWork implements Runnable{
        private GoodsInfo goodsInfo;

        public WriteWork(GoodsInfo goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        @SneakyThrows
        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Thread.sleep(100);
            goodsInfo.setNum(new Random().nextInt(100));
            System.out.println(Thread.currentThread().getName()+"：num="+goodsInfo.getNum()+"    写商品耗时="+(System.currentTimeMillis()-start)+"ms");
        }
    }


    public static void main(String[] args) {
        GoodsInfo goodsInfo=new GoodsInfo("iphone",1);
        for (int i = 0; i < 30; i++) {
            new Thread(new ReadWork(goodsInfo)).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new WriteWork(goodsInfo)).start();
        }
    }
}
