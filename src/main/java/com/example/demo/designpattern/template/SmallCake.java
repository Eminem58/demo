package com.example.demo.designpattern.template;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/4
 */
public class SmallCake extends AbstractCake {
    private boolean shouldApplyFlag;

    public void setShouldApplyFlag(boolean shouldApplyFlag) {
        this.shouldApplyFlag = shouldApplyFlag;
    }

    @Override
    protected boolean shouldApply() {
        return shouldApplyFlag;
    }

    @Override
    protected void shape() {
        System.out.println("小蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("小蛋糕涂抹");
    }

    @Override
    protected void brake() {
        System.out.println("小蛋糕烘焙");
    }
}
