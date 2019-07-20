package com.example.demo;

import com.example.demo.db.Db;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *  [] 
 *  @author 金彪
 *  @date 2019年07月19日
 *  @version 1.0
 *  
 */
public class TestA<T> extends DemoApplicationTests {

    private List list = new ArrayList<>();
    List<T> list2;

    Db db=new Db();
    @Test
    public void syso() {
        System.out.println(list);
        System.out.println(list2);
        System.out.println(db);
        System.out.println(db.getUrl());
    }
}
