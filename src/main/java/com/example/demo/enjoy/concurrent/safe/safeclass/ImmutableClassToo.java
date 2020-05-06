package com.example.demo.enjoy.concurrent.safe.safeclass;

import java.util.ArrayList;
import java.util.List;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class ImmutableClassToo {
    private final List<Integer> list = new ArrayList<>(3);

    public ImmutableClassToo() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public boolean isContain(int i){
        return list.contains(i);
    }
}
