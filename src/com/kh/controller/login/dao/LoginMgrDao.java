package com.kh.controller.login.dao;

import java.util.List;

import com.kh.entity.model.UserInfo;

public interface LoginMgrDao {

	public List<UserInfo> queryUserIds();

}
