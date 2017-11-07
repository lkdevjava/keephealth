package com.kh.common.ehcache.service;

public interface CacheManageService {

    public void put(String cacheName, String key, String value);

    public String get(String cacheName, String key);

    public void remove(String cacheName, String key);

}
