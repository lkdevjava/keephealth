package com.kh.common.model;

import java.io.Serializable;

public class ResultModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg;

	private boolean success;

	private Object result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
