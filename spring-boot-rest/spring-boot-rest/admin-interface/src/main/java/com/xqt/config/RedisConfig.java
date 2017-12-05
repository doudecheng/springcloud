package com.xqt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis pool config
 *
 * @author andy
 * @create 2017-11-29 16:42
 **/
@Configuration
public class RedisConfig {

    @Autowired
    private Environment env;

    @Bean
    @RefreshScope
    public JedisPool getJedisPool(){
        Integer maxIdle = Integer.parseInt(env.getProperty("redis.MaxIdle"));
        Integer minIdle = Integer.parseInt(env.getProperty("redis.MinIdle"));
        Long maxWaitMillis = Long.parseLong(env.getProperty("redis.MaxWait"));
        Boolean testOnBorrow = Boolean.parseBoolean(env.getProperty("redis.TestOnBorrow"));
        String ip = env.getProperty("redis.ip");
        Integer port = Integer.parseInt(env.getProperty("redis.port"));
        String password = env.getProperty("redis.password");
        Integer database = Integer.parseInt(env.getProperty("redis.database"));
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大空闲数
        config.setMaxIdle(maxIdle);
        // 设置初始化数
        config.setMinIdle(minIdle);
        // 设置超时时间
        config.setMaxWaitMillis(maxWaitMillis);
        // 提前进行validate操作，确保获取的jedis实例均是可用
        config.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
        String host = ip;
        JedisPool jedisPool = new JedisPool(config, host, port, 120000, password, database);
        return jedisPool;
    }

}
