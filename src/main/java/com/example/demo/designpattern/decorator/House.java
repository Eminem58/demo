package com.example.demo.designpattern.decorator;

/**
 * [装饰者模式，被装饰者类]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/3/26
 */
public interface House {
    /**
     * [增加人]
     *
     * @author 金彪
     * @date 2020.03.26
     * @version 1.0
     */
    void addPeople(String peopleName);
    /**
     * [增加物]
     *
     * @author 金彪
     * @date 2020.03.26
     * @version 1.0
     */
    void addGoods(String goodsName);
}
