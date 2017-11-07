package com.kh.common.ehcache.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

@Service(value = "ehCacheManageService")
public class CacheManageServiceImpl implements CacheManageService {

    @Autowired
    private EhCacheCacheManager ehCacheManager;

    public EhCacheCacheManager getEhCacheManager() {
	return ehCacheManager;
    }

    public void setEhCacheManager(EhCacheCacheManager ehCacheManager) {
	this.ehCacheManager = ehCacheManager;
    }

    @Override
    public void put(String cacheName, String key, String value) {
	CacheManager mangager = ehCacheManager.getCacheManager();
	Cache cahce = mangager.getCache(cacheName);
	cahce.put(new Element(key, value));
	cahce.flush();
    }

    @Override
    public String get(String cacheName, String key) {
	CacheManager mangager = ehCacheManager.getCacheManager();
	Cache cahce = mangager.getCache(cacheName);
	Element el = cahce.get(key);
	String value = "";
	if (el != null) {
	    value = (String) el.getObjectValue();
	}
	cahce.flush();
	return value;
    }

    @Override
    public void remove(String cacheName, String key) {
	CacheManager mangager = ehCacheManager.getCacheManager();
	Cache cahce = mangager.getCache(cacheName);
	cahce.remove(key);
	cahce.flush();
    }

}
