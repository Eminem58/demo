package com.example.demo.designpattern.decorator.impl;

import com.example.demo.designpattern.decorator.House;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * [被装饰对象]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/3/30
 */
@Data
public class MyHouse implements House {

    private List<String> goodsList = new ArrayList<>();
    private List<String> peopleList = new ArrayList<>();

    @Override
    public void addPeople(String peopleName) {

    }

    /**
     * 被装饰者对象的初始装饰
     */
    @Override
    public void addGoods(String goodsName) {
        goodsList.add("灯");
        goodsList.add("餐桌");
    }
}
