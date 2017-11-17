package com.kh.common.shiro.service;

import org.apache.shiro.cache.Cache;

public interface ShiroCacheService {

    public <K, V> Cache<K, V> getcCache(String cacheName);
    
    public void destroy();
    
}
