package com.kh.common.shiro.cookie;

import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;

public class ShiroCookie extends SimpleCookie {
	
	public ShiroCookie(){
		
	}
	
	public ShiroCookie(String cookieName){
		super(cookieName);
	}
	
	public ShiroCookie(Cookie cookie){
		super(cookie);
	}
	
}
