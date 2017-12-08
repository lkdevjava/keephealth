package com.kh.controller.login.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.common.controller.BaseController;
import com.kh.common.ehcache.service.CacheManageService;
import com.kh.common.model.ResultModel;
import com.kh.common.shiro.model.ShiroToken;
import com.kh.controller.login.service.LoginMgrService;
import com.kh.entity.model.KhUserInfo;

@Controller
public class LoginMgrController extends BaseController {

	private Log logger = LogFactory.getLog(LoginMgrController.class);

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
		List<KhUserInfo> list = loginMgrService.queryUserIdList();
		getRequest().setAttribute("list", list);
		getRequest().setAttribute("size", list.size());
		ehCacheManageService.put("sampleCache3", "keephealth", "test keephealth");
		return "index";
	}

	@RequestMapping(value = "/find")
	public String find() {
		KhUserInfo user = loginMgrService.queryUserInfoById(1);
		System.out.println(user.toString());
		String value = ehCacheManageService.get("sampleCache3", "keephealth");
		System.out.println(value);
		return "index";
	}

	@RequestMapping(value = "/login")
	@ResponseBody
	public ResultModel login(KhUserInfo userInfo) {
		String password = new Md5Hash("123", "keephealth").toString();
		ShiroToken token = new ShiroToken(userInfo.getUsername(), password);
		token.setRememberMe(false);
		try {
			SecurityUtils.getSubject().login(token);
		} catch (DisabledAccountException e) {
			logger.error(e.getMessage(), e);
			return convertResult(e.getMessage(), false);
		} catch (AccountException e) {
			logger.error(e.getMessage(), e);
			return convertResult(e.getMessage(), false);
		}
		return convertResult("", true);
	}
	
	@RequestMapping(value = "/test")
	@ResponseBody
	public ResultModel test() {
		return convertResult("", true);
	}

	public static void main(String[] args) {
	}

}
