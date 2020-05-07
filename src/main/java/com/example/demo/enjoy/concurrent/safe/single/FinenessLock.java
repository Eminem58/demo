package com.example.demo.enjoy.concurrent.safe.single;

import java.util.HashSet;

/**
 * [锁分离]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public class FinenessLock {
    private HashSet<String> userSet = new HashSet<>();
    private HashSet<String> productSet = new HashSet<>();

    public synchronized void addUser(String user) {
        userSet.add(user);
    }

    public synchronized void addProduct(String product) {
        productSet.add(product);
    }


    public void addUserDiv(String user) {
        synchronized (userSet) {
            userSet.add(user);
        }
    }

    public void addProductDiv(String product) {
        synchronized (productSet) {
            productSet.add(product);
        }
    }

}
