package com.kh.common.shiro.model;

import org.apache.shiro.session.mgt.SimpleSession;

public class ShiroSession extends SimpleSession {

    private static final long serialVersionUID = 1L;

    private String loginToken;

    private String host;

    private int onlineStatus;

    public String getLoginToken() {
	return loginToken;
    }

    public void setLoginToken(String loginToken) {
	this.loginToken = loginToken;
    }

    public String getHost() {
	return host;
    }

    public void setHost(String host) {
	this.host = host;
    }

    public int getOnlineStatus() {
	return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
	this.onlineStatus = onlineStatus;
    }

}
