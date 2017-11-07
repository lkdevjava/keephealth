package com.kh.common.shiro.listener;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.kh.common.ehcache.service.CacheManageService;

public class ShiroSessionListenerImpl implements SessionListener {

    @Resource
    private CacheManageService ehCacheManageService;

    public CacheManageService getEhCacheManageService() {
	return ehCacheManageService;
    }

    public void setEhCacheManageService(CacheManageService ehCacheManageService) {
	this.ehCacheManageService = ehCacheManageService;
    }

    @Override
    public void onExpiration(Session session) {
	ehCacheManageService.remove("", (String) session.getId());

    }

    @Override
    public void onStart(Session session) {
	
    }

    @Override
    public void onStop(Session session) {
	
    }

}
