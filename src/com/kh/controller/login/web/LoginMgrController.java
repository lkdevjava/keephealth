package com.kh.controller.login.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.common.controller.BaseController;
import com.kh.common.ehcache.service.CacheManageService;
import com.kh.controller.login.service.LoginMgrService;
import com.kh.entity.model.UserInfo;

@Controller
public class LoginMgrController extends BaseController {

    @Resource
    private LoginMgrService loginMgrService;

    @Resource
    private CacheManageService ehCacheManageService;

    public LoginMgrService getLoginMgrService() {
	return loginMgrService;
    }

    public void setLoginMgrService(LoginMgrService loginMgrService) {
	this.loginMgrService = loginMgrService;
    }

    public CacheManageService getEhCacheManageService() {
	return ehCacheManageService;
    }

    public void setEhCacheManageService(CacheManageService ehCacheManageService) {
	this.ehCacheManageService = ehCacheManageService;
    }

    @RequestMapping(value = "/index")
    public String index() {
	List<UserInfo> list = loginMgrService.queryUserIdList();
	getRequest().setAttribute("list", list);
	getRequest().setAttribute("size", list.size());
	ehCacheManageService.put("sampleCache3", "keephealth",
		"test keephealth");
	return "index";
    }

    @RequestMapping(value = "/find")
    public String find() {
	UserInfo user = loginMgrService.queryUserInfoById(1);
	System.out.println(user.toString());
	String value = ehCacheManageService.get("sampleCache3", "keephealth");
	System.out.println(value);
	return "index";
    }

}
