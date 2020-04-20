package com.example.demo.designpattern.decorator.impl;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/3/30
 */
public class Test {

    public static void main(String[] args) {
        MyHouse myHouse = new MyHouse();
        UpscaleHouseDecorator upscaleHouseDecorator = new UpscaleHouseDecorator(myHouse);
        upscaleHouseDecorator.addGoods("空调");
    }

}
