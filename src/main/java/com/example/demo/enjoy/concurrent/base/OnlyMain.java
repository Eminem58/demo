package com.example.demo.enjoy.concurrent.base;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/19
 */
public class OnlyMain {
    public static void main(String[] args) {
        //虚拟机线程系统的管理接口
        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMxBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + "-----" + threadInfo.getThreadName());
        }
    }
}
