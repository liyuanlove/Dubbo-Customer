package com.coder.dubbo.customer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@Configuration
@EnableCaching //启用缓存，这个注解很重要；
/**
 * (maxInactiveIntervalInSeconds = 60)
 * maxInactiveIntervalInSeconds 默认是1800秒过期，这里测试修改为60秒
 */
@EnableRedisHttpSession
public class RedisConfig {

    public RedisConfig(){
        System.out.println("===============================================================");
        System.out.println(this.host);
    }

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;



}
