package com.example.demo.enjoy.concurrent.actual.transfer;

import java.math.BigDecimal;

/**
 * [模拟支付公司转账的动作测试]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/6
 */
public class PayCompanyTest implements Runnable {
    private UserAccount from;
    private UserAccount to;
    private BigDecimal amount;
    private ITransfer transfer;

    public PayCompanyTest(UserAccount from, UserAccount to, BigDecimal amount, ITransfer transfer) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.transfer = transfer;
    }

    @Override
    public void run() {
        transfer.transfer(from, to, amount);
    }


    public static void main(String[] args) {
        SafeOperate safeOperate = new SafeOperate();
        SafeOperateToo safeOperateToo = new SafeOperateToo();
        NotSafeOperate notSafeOperate = new NotSafeOperate();
        UserAccount from = new UserAccount(1001L, "张三", new BigDecimal("508000.88"));
        UserAccount to = new UserAccount(1002L, "李四", new BigDecimal("0.00"));
        new Thread(new PayCompanyTest(from, to, new BigDecimal("5000"), notSafeOperate)).start();
        new Thread(new PayCompanyTest(to, from, new BigDecimal("3000"), notSafeOperate)).start();
    }
}
