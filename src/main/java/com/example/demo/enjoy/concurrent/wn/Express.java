package com.example.demo.enjoy.concurrent.wn;


import lombok.SneakyThrows;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/4/28
 */
public class Express {
    private static final String DESTINATION = "上海";
    private String site;

    public Express(String site) {
        this.site = site;
    }

    @SneakyThrows
    public synchronized void waitSiteEnd() {
        while (!DESTINATION.equals(site)) {
            wait();
            System.out.println("Check Site thread[" + Thread.currentThread().getId() + "] is be notified。 site=" + site);
        }
        System.out.println("快递到了，通知客户。");
    }

    public synchronized void changeSiteAndNotifyCustomer() {
        site = "上海";
        notifyAll();
    }

}
