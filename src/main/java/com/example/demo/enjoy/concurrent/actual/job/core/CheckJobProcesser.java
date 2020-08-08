package com.example.demo.enjoy.concurrent.actual.job.core;

import com.example.demo.enjoy.concurrent.actual.job.vo.JobInfo;
import com.example.demo.enjoy.concurrent.bq.ItemVo;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.DelayQueue;

/**
 * [任务完成后,在一定的时间供查询结果，之后为释放资源节约内存，需要定期处理过期的任务]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public class CheckJobProcesser {
    private CheckJobProcesser() {
    }

    private static class CheckJobProcesserHolder {
        private static CheckJobProcesser checkJobProcesser = new CheckJobProcesser();
    }

    public static CheckJobProcesser getInstance() {
        return CheckJobProcesserHolder.checkJobProcesser;
    }

    /**
     * 存放任务的队列
     */
    private static DelayQueue<ItemVo<String>> queue = new DelayQueue<>();

    /**
     * 任务完成后，放入队列，经过expireTime时间后，会从整个框架中移除
     */
    public void putJob(String jobName, long expireTime) {
        queue.add(new ItemVo<>(expireTime, jobName));
        System.out.println(jobName + "已经放入过期检查缓存，时长：" + expireTime);
    }

    /**
     * 处理队列中到期任务
     */
    private static class FetchJob implements Runnable {
        private static DelayQueue<ItemVo<String>> queue = CheckJobProcesser.queue;
        //缓存的工作信息
        private Map<String, JobInfo<?>> jobInfoMap = PendingJobPool.getJobInfoMap();

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                ItemVo<String> itemVo = queue.take();
                String jobName = itemVo.getData();
                jobInfoMap.remove(jobName);
                System.out.println(jobName + " 过期了，从缓存中清除");
            }
        }
    }

    static {
        Thread thread = new Thread(new FetchJob());
        thread.setDaemon(true);
        thread.start();
        System.out.println("开启过期检查的守护线程");
    }
}
