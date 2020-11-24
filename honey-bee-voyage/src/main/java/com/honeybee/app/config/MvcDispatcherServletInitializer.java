package com.honeybee.app.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { AppConfig.class }; //uzima konfiguraciju servleta iz AppCongig klase
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" }; //podesava se matching sa "/" url patternom
	}

}