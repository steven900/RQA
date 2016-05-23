package com.jdog.frameworks.freemarker;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public interface BlockContext {

	public abstract HttpServletRequest getRequest();
	
	public abstract HttpServletResponse getResponse();
	
	public abstract Object getAttribute(String name);

	public abstract String getParameter(String name);

	public abstract Object getSessionAttribute(String name);
	
	public abstract void setSessionAttribute(String name,Object val);
	
	public abstract void removeSessionAttribute(String name);

	public abstract Cookie[] getCookies();

	public abstract <T> T getBean(String name);
	
	public abstract Map<String,Object> getRequestParameters();
	
	public abstract <T> void setLocalVariable(String name, T value);

	public abstract <T> void setVariable(String name, T value);
	
	public abstract <T> void setGlobalVariable(String name, T value);
	
	public abstract <T extends TemplateModel> T getGlobalVariable(String name) throws TemplateModelException;
	
	public abstract <T extends TemplateModel> T getLocalVariable(String name) throws TemplateModelException;
	
	public abstract <T extends TemplateModel> T getVariable(String name) throws TemplateModelException;
	
}