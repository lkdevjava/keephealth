package com.kh.controller.login.service;

import java.util.List;

import com.kh.entity.model.KhUserInfo;

public interface LoginMgrService {

	public List<KhUserInfo> queryUserIdList();

	public KhUserInfo queryUserInfoById(int id);

	public KhUserInfo queryUserInfoByUsernameAndPwd(String username,
			String password);

}
