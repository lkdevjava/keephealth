package com.kh.common.shiro.realm;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.kh.common.shiro.model.ShiroToken;
import com.kh.controller.login.service.LoginMgrService;
import com.kh.entity.model.KhUserInfo;

public class LoginRealmImpl extends AuthorizingRealm {

    private Log logger = LogFactory.getLog(LoginRealmImpl.class);

    @Resource
    private LoginMgrService loginMgrService;

    public LoginMgrService getLoginMgrService() {
	return loginMgrService;
    }

    public void setLoginMgrService(LoginMgrService loginMgrService) {
	this.loginMgrService = loginMgrService;
    }
    
    @Override
    public String getName() {
    	return "loginRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
	    PrincipalCollection principal) {
    	System.out.println("-------------------xxxxxxxxxxxxxxxxxxxx----------------------");
	KhUserInfo userInfo = (KhUserInfo) SecurityUtils.getSubject()
		.getPrincipal();
	SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
	    AuthenticationToken authtoken) throws AuthenticationException {
	logger.info("开始登陆");
	ShiroToken token = (ShiroToken) authtoken;
	KhUserInfo userInfo = loginMgrService.queryUserInfoByUsernameAndPwd(
		token.getUsername(), token.getPwd());
	if (null == userInfo) {
	    logger.info("帐号或密码不正确");
	    throw new AccountException("帐号或密码不正确！");
	}
	if (0 != userInfo.getStatus()) {
	    logger.info("帐号已经禁止登录");
	    throw new DisabledAccountException("帐号已经禁止登录！");
	}
	return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(),
		getName());
    }

    @Override
    protected void clearCache(PrincipalCollection principals) {
	super.clearCache(principals);
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
	SimplePrincipalCollection simples = new SimplePrincipalCollection(
		principals, getName());
	super.clearCachedAuthenticationInfo(simples);
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
	SimplePrincipalCollection simples = new SimplePrincipalCollection(
		principals, getName());
	super.clearCachedAuthorizationInfo(simples);
    }

}
