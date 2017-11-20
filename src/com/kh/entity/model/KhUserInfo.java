package com.kh.entity.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class KhUserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String realname;

	private String username;

	private String password;

	private int status;

	private String appkey;

	private Timestamp createtime;

	private Timestamp updatetime;

	private Timestamp lastlogintime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public Timestamp getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Timestamp lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

}
