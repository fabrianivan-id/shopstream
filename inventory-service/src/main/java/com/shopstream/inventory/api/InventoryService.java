package com.shopstream.inventory.api;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class InventoryService {
  private final Map<String,Integer> store = new ConcurrentHashMap<>();
  @Cacheable(value="stock", key="#sku")
  public Integer getStock(String sku) { return store.getOrDefault(sku, 0); }
  @CacheEvict(value="stock", key="#sku")
  public void setStock(String sku, int value) { store.put(sku, value); }
}
