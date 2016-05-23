package com.jdog.frameworks.db;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringBeanFactory implements ApplicationContextAware {
	
	private static ApplicationContext context;

	private static JdbcTemplate jdbcTemplate = null;
	public static Object getBean(String name) throws BeansException {
		return context.getBean(name);  
	}
	
	public static JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = (JdbcTemplate) SpringBeanFactory.getBean("jdbcTemplate");
		}
		return jdbcTemplate;
	}
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringBeanFactory.context = context; 
	}
	
	public static ApplicationContext getApplicationContext() {
		return context;
	}
}
