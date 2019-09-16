package com.example.demo.mode.observer.impl;

import com.example.demo.mode.observer.Observer;
import com.example.demo.mode.observer.Subject;

/**
 *  [] 
 *  @author 金彪
 *  @date 2019年09月15日
 *  @version 1.0
 *  
 */
public class Test {

    public static void main(String[] args) {
        Observer observerA = new ConcreteObserver("警察张三");
        Observer observerB = new ConcreteObserver("警察李四");

        Subject subjectA = new ConcreteSubject("小偷王某");
        Subject subjectB = new ConcreteSubject("嫌犯小明");

        subjectA.addObserver(observerA);
        subjectB.addObserver(observerB);

        subjectA.notice("要逃跑了");
        subjectB.notice("在吃饭");
    }
}
