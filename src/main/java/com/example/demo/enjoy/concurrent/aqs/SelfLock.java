package com.example.demo.enjoy.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * [实现我们自己独占锁,不可重入]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/4
 */
public class SelfLock implements Lock {

    /**
     * 静态内部类，自定义同步器，交给同步器，用同步器实现显示锁Lock
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 获得锁
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁
         */
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setState(0);
            setExclusiveOwnerThread(null);
            return true;
        }

        /**
         * 判断处于占用状态
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 返回一个Condition，每个condition都包含了一个condition队列
         */
        public Condition newConditon() {
            return new ConditionObject();
        }
    }


    /**
     * 仅需要将操作代理到sync上即可
     */
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        System.out.println(Thread.currentThread().getName() + "：开始拿锁");
        sync.acquire(1);
        System.out.println(Thread.currentThread().getName() + "：拿锁完成");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName() + "：开始释放锁");
        sync.release(1);
        System.out.println(Thread.currentThread().getName() + "：释放锁完成");
    }

    @Override
    public Condition newCondition() {
        return sync.newConditon();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }
}
