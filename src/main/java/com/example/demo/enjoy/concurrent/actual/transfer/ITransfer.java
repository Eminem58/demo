package com.example.demo.enjoy.concurrent.actual.transfer;

import java.math.BigDecimal;

/**
 * [银行转账动作接口]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public interface ITransfer {
    /**
     * 转账
     */
    void transfer(UserAccount from, UserAccount to, BigDecimal amount);
}
