package com.kh.common.shiro.ehcache;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;

public class ShiroEhcacheManager<K, V> implements Cache<K, V> {

    private static Log logger = LogFactory.getLog(ShiroEhcacheManager.class);

    private EhCacheManager shiroCacheManager;

    private String cacheName;

    private Cache<K, V> cache;

    public ShiroEhcacheManager(String cacheName,
	    EhCacheManager shiroCacheManager) {
	this.cacheName = cacheName;
	this.shiroCacheManager = shiroCacheManager;
    }

    public EhCacheManager getShiroCacheManager() {
	return shiroCacheManager;
    }

    public void setShiroCacheManager(EhCacheManager shiroCacheManager) {
	this.shiroCacheManager = shiroCacheManager;
    }

    public String getCacheName() {
	return cacheName;
    }

    public void setCacheName(String cacheName) {
	this.cacheName = cacheName;
    }

    public Cache<K, V> getCache() throws CacheException {
	if (StringUtils.isBlank(cacheName)) {
	    logger.info("shiro没有设置缓存对象名称");
	    throw new CacheException("shiro not set cache name");
	}
	if (null == cache) {
	    logger.info("shiro缓存对象不存在,获取" + cacheName + "缓存对象");
	    return shiroCacheManager.getCache(cacheName);
	}
	logger.info("shiro缓存对象已存在");
	return cache;
    }

    @Override
    public void clear() throws CacheException {
	getCache().clear();
    }

    @Override
    public V get(K key) throws CacheException {
	return getCache().get(key);
    }

    @Override
    public Set<K> keys() {
	return getCache().keys();
    }

    @Override
    public V put(K key, V value) throws CacheException {
	return getCache().put(key, value);
    }

    @Override
    public V remove(K key) throws CacheException {
	return getCache().remove(key);
    }

    @Override
    public int size() {
	return getCache().size();
    }

    @Override
    public Collection<V> values() {
	return getCache().values();
    }

}
