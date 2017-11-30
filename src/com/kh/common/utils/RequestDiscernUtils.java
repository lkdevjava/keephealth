package com.kh.common.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class RequestDiscernUtils {

	public static boolean isAjax(ServletRequest request) {
		return StringUtils.equalsIgnoreCase("XMLHttpRequest",
				((HttpServletRequest) request).getHeader("X-Requested-With"));
	}

}
