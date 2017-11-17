package com.kh.common.exception;

public class CacheException extends Exception {

    private static final long serialVersionUID = 1L;

    private String msg;

    public CacheException(String msg) {
	super(msg);
	this.msg = msg;
    }

    public CacheException(String msg, Throwable throwable) {
	super(msg, throwable);
	this.msg = msg;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }

}
