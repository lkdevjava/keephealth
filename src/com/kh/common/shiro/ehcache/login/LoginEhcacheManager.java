package com.kh.common.shiro.ehcache.login;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

import com.kh.common.shiro.service.ShiroCacheService;

public class LoginEhcacheManager implements CacheManager, Destroyable {
    
    private ShiroCacheService shiroCacheService;

    public ShiroCacheService getShiroCacheService() {
        return shiroCacheService;
    }

    public void setShiroCacheService(ShiroCacheService shiroCacheService) {
        this.shiroCacheService = shiroCacheService;
    }
    
    @Override
    public void destroy() throws Exception {
	shiroCacheService.destroy();
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
	return shiroCacheService.getcCache(cacheName);
    }

}
