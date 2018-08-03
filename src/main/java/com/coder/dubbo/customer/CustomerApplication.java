package com.coder.dubbo.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@ImportResource(value = {"classpath:consumers.xml"}) // 使用 consumers.xml 配置；
@EnableCaching // 开启缓存功能
@EnableAutoConfiguration
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
