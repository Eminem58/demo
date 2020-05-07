package com.example.demo.enjoy.concurrent.safe.single;

/**
 * [懒汉式-延迟初始化占位类模式]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public class SingleInit {

    private SingleInit(){}

    private static class InstanceHolder{
        private static SingleInit singleInit = new SingleInit();
    }

    public  SingleInit getInstance(){
        //延迟加载，static只加载一次
        return InstanceHolder.singleInit;
    }
}
