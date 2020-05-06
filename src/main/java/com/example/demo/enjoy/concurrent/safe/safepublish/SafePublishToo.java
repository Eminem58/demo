package com.example.demo.enjoy.concurrent.safe.safepublish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class SafePublishToo {
    private List<Integer> list= Collections.synchronizedList(new ArrayList<>());

    public SafePublishToo() {
        list.add(1);
        list.add(2);
    }

    public List<Integer> getList() {
        return list;
    }

    public static void main(String[] args) {
        SafePublishToo safePublishToo = new SafePublishToo();
        List<Integer> list = safePublishToo.getList();
        list.add(400);
        System.out.println(list);
        System.out.println(safePublishToo.getList());
    }
}
