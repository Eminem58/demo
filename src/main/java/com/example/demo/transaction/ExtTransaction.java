package com.example.demo.transaction;

import java.lang.annotation.*;

/**
 * 〈手写事务〉
 *
 * @author jinbiao
 * @create 2019.06.12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExtTransaction {

}
