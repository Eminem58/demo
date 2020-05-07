package com.example.demo.enjoy.concurrent.actual.job;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public enum TaskResultType {
    //方法执行完成，业务结果也正确
    SUCCESS,
    //方法执行完成，业务结果错误
    FAILE,
    //方法执行抛出了异常
    EXCEPTION
}
