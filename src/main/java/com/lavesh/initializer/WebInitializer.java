package com.lavesh.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		XmlWebApplicationContext cllContext = null;
		XmlWebApplicationContext dsContext = null;
		ContextLoaderListener cll = null;
		DispatcherServlet ds = null;

		cllContext = new XmlWebApplicationContext();
		cllContext.setConfigLocation("/WEB-INF/application-context.xml");
		cll = new ContextLoaderListener(cllContext);
		servletContext.addListener(cll);

		dsContext = new XmlWebApplicationContext();
		dsContext.setConfigLocation("/WEB-INF/dispatcher-servlet.xml");
		ds = new DispatcherServlet(dsContext);

		ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", ds);
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping("*.htm");
	}

}
