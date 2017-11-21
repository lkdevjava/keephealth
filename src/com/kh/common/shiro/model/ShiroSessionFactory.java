package com.kh.common.shiro.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

public class ShiroSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext context) {
	ShiroSession session = new ShiroSession();
	if (session != null && (session instanceof WebSessionContext)) {
	    WebSessionContext webcontext = (WebSessionContext) session;
	    HttpServletRequest request = (HttpServletRequest) webcontext
		    .getServletRequest();
	    if(null != request){
		String ip = request.getLocalAddr() + ":" + request.getLocalPort();
		session.setHost(ip);
	    }
	}
	return session;
    }

}
