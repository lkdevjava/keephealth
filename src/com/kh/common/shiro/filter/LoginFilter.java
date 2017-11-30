package com.kh.common.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.kh.entity.model.KhUserInfo;

public class LoginFilter extends AccessControlFilter {

	private Log logger = LogFactory.getLog(LoginFilter.class);

	@SuppressWarnings("unused")
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		KhUserInfo token = (KhUserInfo) SecurityUtils.getSubject().getPrincipal();
		logger.debug("开始验证_" + token.getRealname() + "_用户是否登录");
		if (token != null) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		//跳转登陆提示界面
		WebUtils.issueRedirect(request, response, "");
		return false;
	}

}
