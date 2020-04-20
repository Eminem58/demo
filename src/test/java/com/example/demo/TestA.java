/*
package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.db.Db;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

*/
/**
 *  [] 
 *  @author 金彪
 *  @date 2019年07月19日
 *  @version 1.0
 *  
 *//*

public class TestA<T> extends DemoApplicationTests {
    @Autowired
    Db db;

    private List list = new ArrayList<>();
    private List<T> list2;

    @Test
    public void test(){
        //System.out.println(Long.parseLong(""));

        List<Person> lisiList = new ArrayList<>();
        Consumer <Person> consumer  =  x -> {
            if (x.name.equals("lisi")){
                lisiList.add(x);
            }
        };
        Stream.of(
                new Person(21,"zhangsan"),
                new Person(22,"lisi"),
                new Person(23,"wangwu"),
                new Person(24,"wangwu"),
                new Person(23,"lisi"),
                new Person(26,"lisi"),
                new Person(26,"zhangsan")
        ).forEach(consumer);

        System.out.println(JSON.toJSONString(lisiList));
    }

    @Test
    public void syso() {
        System.out.println(list);
        System.out.println(list2);
        System.out.println(db);
        System.out.println(db.getUrl());
        System.out.println(StringUtils.uncapitalize("Ab2dDfg"));
        System.out.println("************");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            //System.out.println(applicationContext.getBean(field.getName()));
            System.out.println(field.getName());
        }
        System.out.println("************");

        String url = "www";
        url = new Db().getUrl();
        System.out.println(url);

        System.out.println("************");
        Set<Integer> set = new TreeSet<>();
        System.out.println(set.toString());
        System.out.println(set.size());
        set.add(0);
        set.add(99);
        set.add(1);
        set.add(4);
        set.add(2);
        System.out.println(set.toString());
        System.out.println(set.size());

        Set<Integer> set2 = new TreeSet<>();
        set2.add(1);
        set2.add(1);
        set2.add(0);
        set2.add(99);
        System.out.println(set.addAll(set2));


        Set<Integer> set3 = new TreeSet<>();
        set3.add(0);
        set3.add(99);
        set3.add(1);
        Set<Integer> set4 = new TreeSet<>();
        set4.add(4);
        set4.add(2);
        set4.add(0);
        set4.add(99);
        set4.add(1);
        System.out.println(set3.addAll(set4));


        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        set.forEach((s) -> sb.append(s));
        System.out.println("a:" + sb);
        set.forEach(sb2::append);
        System.out.println("b:" + sb2);

        System.out.println("************");
        HashMap map = new HashMap();
        map.put("a","1");
        map.put("b","2");
        System.out.println(map.toString());
    }
}
*/
