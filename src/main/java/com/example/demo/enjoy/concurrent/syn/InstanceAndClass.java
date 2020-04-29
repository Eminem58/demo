package com.example.demo.enjoy.concurrent.syn;

import com.example.demo.utils.SleepTools;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/29
 */
public class InstanceAndClass {
    private static class SynClass extends Thread{
        @Override
        public void run() {
            System.out.println("TestClass is running...");
            synClass();
        }
    }

    private static class InstanceSyn implements Runnable{
        private InstanceAndClass SynClassAndInstance;

        public InstanceSyn(InstanceAndClass SynClassAndInstance) {
            this.SynClassAndInstance = SynClassAndInstance;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..."+SynClassAndInstance);
            SynClassAndInstance.instance();
        }
    }

    private synchronized void instance(){
        SleepTools.second(1);
        System.out.println("synInstance is going..."+this.toString());
        SleepTools.second(1);
        System.out.println("synInstance ended "+this.toString());
    }

    private static synchronized void synClass(){
        SleepTools.second(1);
        System.out.println("synClass going...");
        SleepTools.second(1);
        System.out.println("synClass end");
    }

    public static void main(String[] args) {
        InstanceAndClass synClassAndInstance = new InstanceAndClass();
        Thread t1 = new SynClass();
        Thread t2 = new Thread(new InstanceSyn(synClassAndInstance));
        t2.start();
        SleepTools.second(1);
        t1.start();
    }
}
