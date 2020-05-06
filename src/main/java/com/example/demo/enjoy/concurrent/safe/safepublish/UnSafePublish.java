package com.example.demo.enjoy.concurrent.safe.safepublish;

import java.util.ArrayList;
import java.util.List;

/**
 * [不安全的发布]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class UnSafePublish {
    private List<Integer> list = new ArrayList<>();

    public UnSafePublish() {
        list.add(1);
        list.add(2);
    }

    public List<Integer> getList() {
        return list;
    }


    public static void main(String[] args) {
        UnSafePublish unSafePublish = new UnSafePublish();
        List<Integer> list = unSafePublish.getList();
        list.add(100);
        System.out.println(list);
        System.out.println(unSafePublish.getList());
    }
}
