package com.example.demo.enjoy.concurrent.safe.single;

/**
 * [懒汉式，双重检查保证单例线程安全，必须加volatile，不然会出现NPE，已过时不用]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public class SingleDcl {

    private static volatile SingleDcl singleDcl;

    /**
     * 私有化
     */
    private SingleDcl() {
    }

    public static SingleDcl getInstance() {
        //第一次检查，不加锁
        if (singleDcl == null) {
            synchronized (SingleDcl.class) {
                //第二次检查，加锁情况下。可能被其他线程初始化，需要再检查下
                if (singleDcl == null) {
                    //内存中分配空间  1
                    //空间初始化 2
                    //把这个空间的地址给我们的引用  3
                    singleDcl = new SingleDcl();
                }
            }
        }
        return singleDcl;
    }
}
