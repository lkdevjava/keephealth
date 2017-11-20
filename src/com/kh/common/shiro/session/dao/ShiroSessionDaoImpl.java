package com.kh.common.shiro.session.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import com.kh.common.exception.CacheException;
import com.kh.common.shiro.session.service.ShiroSessionService;

public class ShiroSessionDaoImpl extends AbstractSessionDAO {

	private Log logger = LogFactory.getLog(ShiroSessionDaoImpl.class);

	private ShiroSessionService shiroSessionService;

	public ShiroSessionService getShiroSessionService() {
		return shiroSessionService;
	}

	public void setShiroSessionService(ShiroSessionService shiroSessionService) {
		this.shiroSessionService = shiroSessionService;
	}

	@Override
	public void delete(Session session) {
		try {
			shiroSessionService.deleteSession(session);
		} catch (CacheException e) {
			logger.error("", e);
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Collection<Session> sessions = new HashSet<Session>();
		try {
			sessions = shiroSessionService.getSessions();
		} catch (CacheException e) {
			logger.error("", e);
		}
		return sessions;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		try {
			shiroSessionService.updateSession(session);
		} catch (CacheException e) {
			logger.error("", e);
		}
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable id = generateSessionId(session);
		assignSessionId(session, id);
		try {
			shiroSessionService.saveSession(session);
		} catch (CacheException e) {
			logger.error("", e);
		}
		return id;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = null;
		try {
			session = shiroSessionService.getSession(sessionId);
		} catch (CacheException e) {
			logger.error("", e);
		}
		return session;
	}

}
