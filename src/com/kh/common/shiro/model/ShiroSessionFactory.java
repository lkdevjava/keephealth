package com.kh.common.shiro.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

public class ShiroSessionFactory implements SessionFactory {

    private Log logger = LogFactory.getLog(ShiroSessionFactory.class);

    @Override
    public Session createSession(SessionContext context) {
	logger.debug("开始创建session");
	ShiroSession session = new ShiroSession();
	if (session != null && (session instanceof WebSessionContext)) {
	    logger.debug("session信息不存在,开始创建");
	    WebSessionContext webcontext = (WebSessionContext) session;
	    HttpServletRequest request = (HttpServletRequest) webcontext
		    .getServletRequest();
	    if (null != request) {
		String host = request.getLocalAddr() + ":"
			+ request.getLocalPort();
		logger.debug("当前访问ip: " + host);
		session.setHost(host);
	    }
	}
	logger.debug("创建session结束");
	return session;
    }

}
