package com.atguigu.letaotao.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class LeTaoTaoListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		String contextPath = servletContext.getContextPath();
		servletContext.setAttribute("APP_PATH", contextPath);
		
		super.contextInitialized(event);
	}

}
