package com.example.demo;

import com.example.demo.db.Db;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
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
    @Autowired
    Db db;

    private List list = new ArrayList<>();
    private List<T> list2;


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
    }
}
