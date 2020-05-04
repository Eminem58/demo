package com.example.demo.designpattern.template;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/4
 */
public class CheeseCake extends AbstractCake {

    @Override
    protected void shape() {
        System.out.println("芝士蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("芝士蛋糕涂抹");
    }

    @Override
    protected void brake() {
        System.out.println("芝士蛋糕烘焙");
    }
}
