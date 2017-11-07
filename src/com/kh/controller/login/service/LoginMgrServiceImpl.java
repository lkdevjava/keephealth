package com.kh.controller.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.controller.login.dao.LoginMgrDao;
import com.kh.entity.model.UserInfo;

@Service(value = "loginMgrService")
public class LoginMgrServiceImpl implements LoginMgrService {

	@Autowired
	private LoginMgrDao loginMgrDao;

	public LoginMgrDao getLoginMgrDao() {
		return loginMgrDao;
	}

	public void setLoginMgrDao(LoginMgrDao loginMgrDao) {
		this.loginMgrDao = loginMgrDao;
	}

	@Override
	public List<UserInfo> queryUserIdList() {
		return loginMgrDao.queryUserIds();
	}

	@Override
	public UserInfo queryUserInfoById(int id) {
	    return loginMgrDao.queryUserInfoById(id);
	}

}
