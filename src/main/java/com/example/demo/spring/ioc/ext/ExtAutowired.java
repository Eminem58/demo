package com.example.demo.spring.ioc.ext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  [] 
 *  @author 金彪
 *  @date 2019年07月30日
 *  @version 1.0
 *  
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtAutowired {
    boolean require() default true;
}
