package com.example.demo.enjoy.concurrent.bq;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
public class ItemVo<T> implements Delayed {

    /**
     * 数据
     */
    private T data;
    /**
     * 到期时间
     */
    private long activeTime;

    /**
     * 过期时长单位s
     */
    public ItemVo(long expirationTime, T data) {
        this.data = data;
        this.activeTime = expirationTime * 1000 + System.currentTimeMillis();
    }

    public T getData() {
        return data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    /**
     * 这个方法返回到激活日期的剩余时间，时间单位由单位参数指定。
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(activeTime - System.currentTimeMillis(), unit);
    }
    /**
     * Delayed接口继承了Comparable接口，按剩余时间排序，实际计算考虑精度为纳秒数
     */
    @Override
    public int compareTo(Delayed o) {
        long compare = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        if (compare == 0) {
            return 0;
        } else {
            if (compare < 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
