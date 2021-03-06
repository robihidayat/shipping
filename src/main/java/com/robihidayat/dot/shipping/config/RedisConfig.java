package com.robihidayat.dot.shipping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

  @Bean
  public static JedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.afterPropertiesSet();
    return jedisConnectionFactory;
  }

  @Bean
  public static RedisTemplate<String, String> redisTemplate() {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<String ,String>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}
