package com.kh.common.support;

import org.springframework.web.context.WebApplicationContext;

public class SpringContextUtil {

    public static WebApplicationContext context;

    public static WebApplicationContext getContext() {
        return context;
    }

    public static void setContext(WebApplicationContext context) {
        SpringContextUtil.context = context;
    }
    
}
