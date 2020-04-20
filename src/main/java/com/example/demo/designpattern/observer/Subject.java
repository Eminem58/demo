package com.example.demo.designpattern.observer;

/**
 *  [被观察目标接口] 
 *  @author 金彪
 *  @date 2019年09月15日
 *  @version 1.0
 *  
 */
public interface Subject {
    /**
     * [添加观察者]
     * @author 金彪
     * @date 2019年09月15日
     * @param
     * @return
     * @version 1.0
     */
    void addObserver(Observer observer);
    /**
     * [删除观察者]
     * @author 金彪
     * @date 2019年09月15日
     * @param
     * @return
     * @version 1.0
     */
    void deleteObserver(Observer observer);
    /**
     * [通知观察者]
     * @author 金彪
     * @date 2019年09月15日
     * @param
     * @return
     * @version 1.0
     */
    void notice(String msg);
}
