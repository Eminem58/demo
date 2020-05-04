package com.example.demo.enjoy.concurrent.lock;

import lombok.SneakyThrows;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/3
 */
public class UseSyn implements IGoodsService {
    private GoodsInfo goodsInfo;

    public UseSyn(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @SneakyThrows
    @Override
    public synchronized Integer getNum() {
        Thread.sleep(1000);
        return goodsInfo.getNum();
    }

    @SneakyThrows
    @Override
    public synchronized void setNum(int num) {
        Thread.sleep(1000);
        goodsInfo.setNum(goodsInfo.getNum() + num);
    }
}
