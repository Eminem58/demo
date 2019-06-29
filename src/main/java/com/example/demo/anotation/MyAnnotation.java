package com.example.demo.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 〈自定义注解〉
 *
 * @author jinbiao
 * @create 2019/6/10
 * @since 1.0.0
 */
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface MyAnnotation {

    int userId() default 0;
    String userName() default "默认名称";
    String[] arrays() default {};
}
