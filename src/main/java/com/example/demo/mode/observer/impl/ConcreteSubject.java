package com.example.demo.mode.observer.impl;

import com.example.demo.mode.observer.Observer;
import com.example.demo.mode.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 *  [具体的目标对象] 
 *  @author 金彪
 *  @date 2019年09月15日
 *  @version 1.0
 *  
 */
public class ConcreteSubject implements Subject {

    /**
     * 目标的名字
     */
    private String subjectName;
    /**
     * 观察者集合
     */
    private List<Observer> observerList = new ArrayList<>();

    public ConcreteSubject(String subjectName){
        this.subjectName = subjectName;
    }

    @Override
    public void addObserver(Observer observer) {
        if (!observerList.contains(observer)) {
            observerList.add(observer);
        }
    }

    @Override
    public void deleteObserver(Observer observer) {
        if (observerList.contains(observer)) {
            observerList.remove(observer);
        }
    }

    @Override
    public void notice(String msg) {
        for (Observer observer : observerList) {
            observer.update("目标【" + subjectName + "】" + msg);
        }
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Observer> getObserverList() {
        return observerList;
    }

    public void setObserverList(List<Observer> observerList) {
        this.observerList = observerList;
    }
}
