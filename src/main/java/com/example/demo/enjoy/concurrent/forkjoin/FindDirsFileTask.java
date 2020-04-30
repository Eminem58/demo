package com.example.demo.enjoy.concurrent.forkjoin;

import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * [遍历指定目录（含子目录）找寻指定类型文件]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/29
 */
public class FindDirsFileTask extends RecursiveAction {
    private File file;

    public FindDirsFileTask(File file) {
        this.file = file;
    }

    @Override
    protected void compute() {
        List<FindDirsFileTask> subTasks = new ArrayList<>();
        File[] files = file.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    subTasks.add(new FindDirsFileTask(file));
                } else {
                    if (file.getAbsolutePath().endsWith("txt")) {
                        System.out.println(file.getAbsolutePath());
                    }
                }
            }
            if (!subTasks.isEmpty()) {
                for (FindDirsFileTask subTask : invokeAll(subTasks)) {
                    subTask.join();
                }
            }
        }

    }

    @SneakyThrows
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FindDirsFileTask findDirsFileTask = new FindDirsFileTask(new File("E:/"));
        //异步提交
        forkJoinPool.execute(findDirsFileTask);
        Thread.sleep(1);
        findDirsFileTask.join();
    }

}
