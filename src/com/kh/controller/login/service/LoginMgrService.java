package com.kh.controller.login.service;

import java.util.List;

import com.kh.entity.model.UserInfo;

public interface LoginMgrService {

	public List<UserInfo> queryUserIdList();
	
	public UserInfo queryUserInfoById(int id);

}
