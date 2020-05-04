package com.example.demo.enjoy.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * [共享同步工具类，允许多个线程同时获得锁]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/4
 */
public class TrinityLock implements Lock {


    private static class Sync extends AbstractQueuedSynchronizer {
        //表示允许count个线程同时获得锁
        private int count;

        public Sync(int count) {
            if (count < 0) {
                throw new IllegalArgumentException("不能小于0");
            }
            setState(count);
            this.count = count;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int state = getState();
                int stateUpdate = state + arg;

                if(stateUpdate>count){
                    throw new IllegalArgumentException("超出归还连接");
                }
                if (compareAndSetState(state, stateUpdate)) {
                    return true;
                }
            }
        }

        /**
         * @param arg 扣减个数
         * @return 小于0表示没拿到锁，否则成功拿锁
         */
        @Override
        protected int tryAcquireShared(int arg) {
            for (; ; ) {
                int state = getState();
                int stateUpdate = state - arg;
                if (stateUpdate<0 || compareAndSetState(state,stateUpdate)) {
                    return stateUpdate;
                }
            }
        }

        final ConditionObject newCondition(){
            return new ConditionObject();
        }
    }


    /**
     * 仅需要将操作代理到sync上即可
     */
    private final Sync sync = new Sync(4);

    @Override
    public void lock() {
        sync.tryAcquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1)>=0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
