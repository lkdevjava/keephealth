package com.kh.common.exception;

public class KeepHealthException extends Exception {

	private static final long serialVersionUID = 1L;

	private String msg;

	private Throwable throwable;

	public KeepHealthException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public KeepHealthException(String msg, Throwable throwable) {
		super(msg, throwable);
		this.msg = msg;
		this.throwable = throwable;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}
