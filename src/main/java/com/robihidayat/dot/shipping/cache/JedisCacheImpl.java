package com.robihidayat.dot.shipping.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robihidayat.dot.shipping.exception.CacheException;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class JedisCacheImpl implements Cache {

  private static final String REDIS_ENTITY = "cache";

  private final RedisTemplate<String, Object> redisTemplate;
  private HashOperations<String, String, String> hashOperations;
  private final ObjectMapper objectMapper;

  public JedisCacheImpl(
      RedisTemplate<String, Object> redisTemplate,
      HashOperations<String, String, String> hashOperations,
      ObjectMapper objectMapper) {
    this.redisTemplate = redisTemplate;
    this.hashOperations = hashOperations;
    this.objectMapper = objectMapper;
  }


  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
  }

  @Override
  public <T> T get(String key, Class<T> type) {
    T result = null;
    String cached = hashOperations.get(REDIS_ENTITY, key);
    if (cached != null) {
      log.trace("cache hit {}", key);
      if (type.isAssignableFrom(String.class)) {
        result = (T) cached;
      } else {
        try {
          result = objectMapper.readValue(cached, type);
        } catch (IOException e) {
          throw new CacheException("error parsing", e, key);
        }
      }
    }
    return result;
  }

  @Override
  public <T> void put(String key, T value) {
    try {
      String cached =
          value instanceof String ? (String) value : objectMapper.writeValueAsString(value);
      hashOperations.put(REDIS_ENTITY, key, cached);
    } catch (Exception e) {
      throw new CacheException("error parsing", e, key);
    }
  }
}
