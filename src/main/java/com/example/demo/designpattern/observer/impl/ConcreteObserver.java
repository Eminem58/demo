package com.example.demo.designpattern.observer.impl;

import com.example.demo.designpattern.observer.Observer;

/**
 *  [具体的观察者对象] 
 *  @author 金彪
 *  @date 2019年09月15日
 *  @version 1.0
 *  
 */
public class ConcreteObserver implements Observer {

    /**
     * 观察者名字
     */
    private String observerName;

    public ConcreteObserver(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(String msg) {
        System.out.println(observerName + "观察到：" + msg);
    }

    public String getObserverName() {
        return observerName;
    }

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }
}
