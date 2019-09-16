package com.example.demo.mode.singleton;


/**
 * 〈单例模式〉
 *
 * @author jinbiao
 * @create 2019/6/2
 * @since 1.0.0
 */
public class Singleton {

    //饿汉式
    /*private static final Singleton singleton=new Singleton();
    private Singleton(){

    }
    public static Singleton getInstance() {
        return singleton;
    }*/

    //懒汉式
    /*private static Singleton singleton;
    private Singleton(){

    }
    public static synchronized Singleton getInstance(){
        if(singleton==null){
            singleton=new Singleton();
        }
        return singleton;
    }*/

    //枚举创建单例，天生具备jvm保障单例，不能被反射
    public enum SingletonEnum{
        INSTANCE;
        private Singleton singleton;
        SingletonEnum(){
            singleton=new Singleton();
        }
        public Singleton getInstance(){
            return singleton;
        }
    }
    public static Singleton getInstance(){
        return SingletonEnum.INSTANCE.getInstance();
    }


    public static void main(String[] args) {
        Singleton singleton=Singleton.getInstance();
        Singleton singleton2=Singleton.getInstance();
        System.out.println(singleton==singleton2);
    }

}
