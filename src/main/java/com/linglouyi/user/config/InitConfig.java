package com.linglouyi.user.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class InitConfig {

    @Value("${system.token}")
    private String token = "x-token";

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public String tokenName(){
        return token;
    }

    @Bean
    public RedisTemplate getRedisTemplate(){
        return redisTemplate;
    }

}
