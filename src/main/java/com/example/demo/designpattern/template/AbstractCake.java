package com.example.demo.designpattern.template;

/**
 * [模版方法：抽象蛋糕模型]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/4
 */
public abstract class AbstractCake {
    /**
     * 蛋糕造型
     */
    protected abstract void shape();
    /**
     * 蛋糕涂抹
     */
    protected abstract void apply();
    /**
     * 蛋糕烘焙
     */
    protected abstract void brake();

    /**
     * 模版方法
     */
    public final void run(){
        shape();
        if(shouldApply()){
            apply();
        }
        brake();
    }

    protected boolean shouldApply(){
        return true;
    }
}
