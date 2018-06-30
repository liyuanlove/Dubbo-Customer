package com.coder.dubbo.customer.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching //启用缓存，这个注解很重要；
//maxInactiveIntervalInSeconds 默认是1800秒过期，这里测试修改为60秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class RedisConfig {

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

//    @Bean
//    public JedisPool redisPoolFactory() {
//        System.out.println("JedisPool注入成功！！");
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        //本地redis未设置密码，所以第五个参数password不传
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
//        return jedisPool;
//    }
//
//    /**
//     * 缓存管理器.
//     * @param redisTemplate
//     * @return
//     */
//    /*/-->Bean
//    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
//        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        return cacheManager;
//    }*/
//
//    /**
//     * redis模板操作类,类似于jdbcTemplate的一个类;
//     * 虽然CacheManager也能获取到Cache对象，但是操作起来没有那么灵活；
//     * 这里在扩展下：RedisTemplate这个类不见得很好操作，我们可以在进行扩展一个我们
//     * 自己的缓存类，比如：RedisStorage类;
//     * @param redisConnectionFactory : 通过Spring进行注入，参数在application.properties进行配置；
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
//                new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }
//
//    /**
//     * 自定义key生成策略
//     * 类名+方法名+参数(适用于分布式缓存)，默认key生成策略分布式下有可能重复被覆盖
//     * @return
//     */
//    @Bean
//    public KeyGenerator keyGenerator() {
//        return (o, method, objects) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(o.getClass().getName());
//            sb.append("." + method.getName() + "(");
//            for (Object obj : objects) {
//                sb.append(obj.toString());
//            }
//            sb.append(")");
//            return sb.toString();
//        };
//    }
}
