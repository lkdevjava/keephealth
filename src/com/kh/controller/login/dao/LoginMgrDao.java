package com.kh.controller.login.dao;

import java.util.List;

import com.kh.entity.model.KhUserInfo;

public interface LoginMgrDao {

	public List<KhUserInfo> queryUserIds();

	public KhUserInfo queryUserInfoById(int id);

	public KhUserInfo queryUserInfoByUsernameAndPwd(String username,
			String password);

}
