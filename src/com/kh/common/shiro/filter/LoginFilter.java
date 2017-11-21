package com.kh.common.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.web.filter.AccessControlFilter;

public class LoginFilter extends AccessControlFilter {

    private Log logger = LogFactory.getLog(LoginFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
	    ServletResponse response, Object mappedValue) throws Exception {
	logger.info("---------------isAccessAllowed");
	return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
	    throws Exception {
	logger.info("---------------onAccessDenied");
	return false;
    }

}
