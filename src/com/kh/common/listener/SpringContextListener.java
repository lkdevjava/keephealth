package com.kh.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kh.common.support.SpringContextUtil;

public class SpringContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
	WebApplicationContext context = WebApplicationContextUtils
		.getWebApplicationContext(event.getServletContext());
	SpringContextUtil.setContext(context);
    }

}
