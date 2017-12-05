package com.xqt.controller;

import com.xqt.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * config client demo
 *
 * @author andy
 * @create 2017-11-29 16:48
 **/
@RestController
@RequestMapping("/config")
public class ConfigDemoController {

    @Value("${redis.ip}")
    String ip;

    @Autowired
    JedisPool jedisPool;


    @RequestMapping("/redis")
    public String redisConfig(){

        Object o = JedisUtil.get("andy");

        return jedisPool.getResource().get("hello")+o;
    }

}
