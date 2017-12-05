package com.xqt.springboot.dubbo.commands;

import org.springframework.boot.CommandLineRunner;


public class DubboServiceLatchCommandLineRunner implements CommandLineRunner {

    private String domain = "com.xqt.lifecycles";

    @Override
    public void run(String... args) throws Exception {
        ShutdownLatch latch = new ShutdownLatch(getDomain());
        latch.await();
    }


    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
