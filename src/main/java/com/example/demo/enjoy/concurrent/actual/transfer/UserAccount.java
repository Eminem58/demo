package com.example.demo.enjoy.concurrent.actual.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * [账户实体]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
@Data
@AllArgsConstructor
public class UserAccount {
    private Long accountId;
    private String accountName;
    private BigDecimal money;
    private final Lock lock = new ReentrantLock();

    public void addMoney(BigDecimal amount) {
        money = money.add(amount);
    }

    public void subtractMoney(BigDecimal amount) {
        money = money.subtract(amount);
    }
}
