package com.example.demo.enjoy.concurrent.safe.single;

/**
 * [饿汉式]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public class SingleEHan {
    private SingleEHan(){}
    private static SingleEHan singleEHan = new SingleEHan();
}
