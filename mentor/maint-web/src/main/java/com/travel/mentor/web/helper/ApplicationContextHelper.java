package com.travel.mentor.web.helper;

import javax.servlet.ServletContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class ApplicationContextHelper implements ApplicationContextAware {

	private WebApplicationContext ctx;

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = (WebApplicationContext)ctx;
	}

	public ServletContext getServletContext() {
	    ServletContext servletContext = ctx.getServletContext();
	    return servletContext;
	  }

	public String getContext() {
		ServletContext servletContext = this.getServletContext();
		return servletContext.getContextPath();
	}
}
