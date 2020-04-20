package com.example.demo.designpattern.decorator.impl;

import com.example.demo.designpattern.decorator.House;

/**
 * [抽象装饰者]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/3/30
 */
public class AbstractHouseDecorator implements House {

    /**
     * 拿到被装饰者对象，创建自己的对象
     */
    public House house;

    public AbstractHouseDecorator(House house) {
        this.house = house;
    }

    @Override
    public void addPeople(String peopleName) {
        house.addPeople(peopleName);
    }

    @Override
    public void addGoods(String goodsName) {
        house.addGoods(goodsName);
    }
}
