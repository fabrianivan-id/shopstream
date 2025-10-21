package com.shopstream.inventory.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private final RedisTemplate<String, Integer> redis;

    public StockService(RedisTemplate<String, Integer> redis) {
        this.redis = redis;
    }

    @Cacheable(cacheNames = "stock", key = "#sku")
    public Integer getStock(String sku) {
        Integer v = redis.opsForValue().get("stock:" + sku);
        return v == null ? 0 : v;
    }

    @CacheEvict(cacheNames = "stock", key = "#sku")
    public Integer setStock(String sku, int value) {
        redis.opsForValue().set("stock:" + sku, value);
        return value;
    }
}
