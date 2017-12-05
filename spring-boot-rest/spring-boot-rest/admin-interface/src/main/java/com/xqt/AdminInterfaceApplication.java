package com.xqt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Administrator on 2017/3/13.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ImportResource({"classpath:applicationConsumer.xml"})
public class AdminInterfaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminInterfaceApplication.class, args);
    }
}