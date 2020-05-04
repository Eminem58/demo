package com.example.demo.designpattern.template;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/4
 */
public class MakeCake {
    public static void main(String[] args) {
        CheeseCake cheeseCake = new CheeseCake();
        cheeseCake.run();

        SmallCake smallCake = new SmallCake();
        smallCake.setShouldApplyFlag(false);
        smallCake.run();
    }
}
