package com.kh.common.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.Element;

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

    private net.sf.ehcache.Cache cache;

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

    public net.sf.ehcache.Cache getCache() throws CacheException {
	if (StringUtils.isBlank(cacheName)) {
	    logger.debug("shiro没有设置缓存对象名称");
	    throw new CacheException("shiro not set cache name");
	}
	if (null == cache) {
	    logger.debug("shiro缓存对象不存在,获取" + cacheName + "缓存对象");
	    cache = shiroCacheManager.getCacheManager().getCache(cacheName);
	}
	logger.debug("shiro缓存对象已存在");
	return cache;
    }

    @Override
    public void clear() throws CacheException {
	getCache().removeAll();
	getCache().flush();
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) throws CacheException {
	Element el = getCache().get(key);
	V v = null;
	if (el != null) {
	    v = (V) el.getObjectValue();
	}
	getCache().flush();
	return v;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
	Set<K> keys = new HashSet<K>();
	keys.addAll(getCache().getKeys());
	getCache().flush();
	return keys;
    }

    @Override
    public V put(K key, V value) throws CacheException {
	getCache().put(new Element(key, value));
	getCache().flush();
	return value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V remove(K key) throws CacheException {
	Element el = getCache().get(key);
	boolean success = getCache().remove(key);
	getCache().flush();
	if (success) {
	    return el != null ? (V) el.getObjectValue() : null;
	}
	return null;
    }

    @Override
    public int size() {
	return getCache().getSize();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<V> values() {
	List<V> values = new ArrayList<V>();
	List<K> keys = getCache().getKeys();
	if (keys != null && keys.size() > 0) {
	    for (K key : keys) {
		values.add((V) getCache().get(key).getObjectValue());
	    }
	}
	getCache().flush();
	return values;
    }

}
