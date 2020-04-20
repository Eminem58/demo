package com.example.demo.designpattern.decorator.impl;

import com.example.demo.designpattern.decorator.House;

/**
 * [高端的装饰者]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/3/30
 */
public class UpscaleHouseDecorator extends AbstractHouseDecorator {

    public UpscaleHouseDecorator(House house) {
        super(house);
    }

    @Override
    public void addGoods(String goodsName) {
        super.addGoods(goodsName);
        MyHouse myHouse = (MyHouse) house;
        myHouse.getGoodsList().add(goodsName);
        System.out.println("买了一个" + goodsName);
        give();
    }

    /**
     * 赠送
     */
    public void give() {
        MyHouse myHouse = (MyHouse)house;
        myHouse.getGoodsList().add("水墨画");
        System.out.println("赠送一幅水墨画");
    }
}
