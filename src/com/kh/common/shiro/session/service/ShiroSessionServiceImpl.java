package com.kh.common.shiro.session.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.InitializingBean;

import com.kh.common.exception.CacheException;

public class ShiroSessionServiceImpl implements ShiroSessionService,
	InitializingBean {

    private Log logger = LogFactory.getLog(ShiroSessionServiceImpl.class);

    private String cacheName;

    private EhCacheManager shiroCacheManager;

    private Cache cache;

    public String getCacheName() {
	return cacheName;
    }

    public void setCacheName(String cacheName) {
	this.cacheName = cacheName;
    }

    public EhCacheManager getShiroCacheManager() {
	return shiroCacheManager;
    }

    public void setShiroCacheManager(EhCacheManager shiroCacheManager) {
	this.shiroCacheManager = shiroCacheManager;
    }

    @Override
    public boolean saveSession(Session session) throws CacheException {
	Element el = cache.get(session.getId());
	if (null == el) {
	    cache.put(new Element(session.getId(), session));
	    return true;
	} else {
	    logger.debug("缓存中已存在," + session.getId() + "**session");
	    return false;
	}
    }

    @Override
    public boolean deleteSession(Session session) throws CacheException {
	return cache.remove(session.getId());
    }

    @Override
    public void updateSession(Session session) throws CacheException {
	Element el = cache.get(session.getId());
	if (null == el) {
	    logger.debug("缓存中不存在," + session.getId() + "**session,进行添加");
	    cache.put(new Element(session.getId(), session));
	} else {
	    boolean flag = cache.remove(session.getId());
	    if (flag) {
		logger.debug("成功删除" + session.getId() + "**sesssion信息");
		cache.put(new Element(session.getId(), session));
	    } else {
		logger.debug("删除" + session.getId() + "**sesssion信息失败");
		throw new CacheException("修改时,删除缓存信息失败");
	    }
	}
    }

    @Override
    public Session getSession(Serializable id) throws CacheException {
	return (Session) cache.get(id).getObjectValue();
    }

    @Override
    public Collection<Session> getSessions() throws CacheException {
	HashSet<Session> sessions = new HashSet<Session>();
	@SuppressWarnings("unchecked")
	List<Serializable> keys = cache.getKeys();
	if (keys != null && keys.size() > 0) {
	    for (Serializable key : keys) {
		sessions.add((Session) cache.get(key));
	    }
	}
	return sessions;
    }

    @Override
    public int size() throws CacheException {
	return cache.getSize();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
	cache = shiroCacheManager.getCacheManager().getCache(cacheName);
    }

}
