package com.kh.common.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

public class PermissionFilter extends AccessControlFilter {
	
	private static Log logger = LogFactory.getLog(PermissionFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object objcet)
			throws Exception {
//		Subject subject = getSubject(request, response);
		Subject subject = SecurityUtils.getSubject();
		HttpServletRequest req = (HttpServletRequest) request;
		String contextPath = req.getContextPath();
		System.out.println(contextPath);
		String url = req.getRequestURI();
		System.out.println(subject.isPermitted(url));
		url = StringUtils.replace(url, contextPath, "");
		logger.debug("当前请求地址:"+url);
		subject.isPermitted(url);
		System.out.println(subject.isPermitted(url));
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		return false;
	}
	
}
