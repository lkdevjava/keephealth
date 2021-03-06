package com.kh.common.shiro.login.service;

import org.apache.shiro.cache.Cache;

public interface ShiroCacheService {

    public <K, V> Cache<K, V> getCache(String cacheName);
    
    public void destroy();
    
}
