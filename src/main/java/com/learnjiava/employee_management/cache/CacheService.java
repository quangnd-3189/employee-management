package com.learnjiava.employee_management.cache;

import org.springframework.cache.*;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CacheService {
  private final CacheManager cacheManager;

  public <T> T getKeyFromCache(String name, String key, Class<T> type) {
    if (name == null) return null;
    Cache cache = cacheManager.getCache(name);
    if (cache != null && key != null) return cache.get(key, type);
    return null;
  }

  public void setKeyToCache(String name, String key, Object value) {
    if (name == null) return;
    Cache cache = cacheManager.getCache(name);
    if (cache != null && key != null) cache.put(key, value);
  }

}
