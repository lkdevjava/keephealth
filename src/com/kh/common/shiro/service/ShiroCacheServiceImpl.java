package com.kh.common.shiro.service;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;

import com.kh.common.shiro.ehcache.ShiroEhcacheManager;

public class ShiroCacheServiceImpl implements ShiroCacheService {

    private EhCacheManager shiroCacheManager;

    public EhCacheManager getShiroCacheManager() {
        return shiroCacheManager;
    }

    public void setShiroCacheManager(EhCacheManager shiroCacheManager) {
        this.shiroCacheManager = shiroCacheManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) {
	return new ShiroEhcacheManager<K, V>(cacheName, shiroCacheManager);
    }

    @Override
    public void destroy() {
	//项目关闭ehcache会自动关闭
    }

}
