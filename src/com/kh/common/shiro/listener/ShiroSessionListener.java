package com.kh.common.shiro.listener;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.kh.common.exception.CacheException;
import com.kh.common.shiro.session.service.ShiroSessionService;

public class ShiroSessionListener implements SessionListener {

    private Log logger = LogFactory.getLog(ShiroSessionListener.class);

    @Resource
    private ShiroSessionService shiroSessionService;

    public ShiroSessionService getShiroSessionService() {
	return shiroSessionService;
    }

    public void setShiroSessionService(ShiroSessionService shiroSessionService) {
	this.shiroSessionService = shiroSessionService;
    }

    @Override
    public void onStart(Session session) {
    }

    @Override
    public void onStop(Session session) {

    }

    @Override
    public void onExpiration(Session session) {
	try {
	    shiroSessionService.deleteSession(session);
	} catch (CacheException e) {
	    logger.error("删除无效session信息失败", e);
	}
    }

}
