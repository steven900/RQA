package com.jdog.frameworks.freemarker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import com.jdog.frameworks.util.Pair;
import com.jdog.frameworks.util.RequestUtil;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class BlockContextImpl implements BlockContext {
	
	protected Environment env;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private WebApplicationContext springContext;
	protected BeansWrapper wrapper = DefaultObjectWrapper.getDefaultInstance();
	protected List<Pair<String, Object>> vars = new ArrayList<Pair<String,Object>>();
	
	public BlockContextImpl(Environment env){
		
		this.env  = env;
		
		try {
			
			HttpRequestHashModel model = (HttpRequestHashModel)env.getDataModel().get(FreemarkerServletWrapper.KEY_REQUEST);
			
			if(model != null){
				request = model.getRequest();
				response = model.getResponse();
			}
		
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
	}
	
	public Object getAttribute(String name){
		return request.getAttribute(name);
	}
	
	public String getParameter(String name){
		return request.getParameter(name);
	}
	
	public Object getSessionAttribute(String name){
		return request.getSession().getAttribute(name);
	}
	
	public Cookie[] getCookies(){
		return request.getCookies();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(String name){
		return (T)springContext.getBean(name);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setSessionAttribute(String name, Object val) {
		request.getSession().setAttribute(name, val);
	}
	
	public void removeSessionAttribute(String name) {
		request.getSession().removeAttribute(name);
	}

	public Map<String, Object> getRequestParameters() {
		return RequestUtil.getParameters(request);
	}
	
	/* (non-Javadoc)
	 * @see com.fccs.v8.ftl.bbps.BlockContext#setLocalVariable(java.lang.String, T)
	 */
	public <T> void setLocalVariable(String name,T value){
		try {
			storeVariable(name);
			env.setVariable(name, wrapper.wrap(value));
		} catch (TemplateModelException e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.fccs.v8.ftl.bbps.BlockContext#setVariable(java.lang.String, T)
	 */
	public <T> void setVariable(String name,T value){
		
		try {
			env.setVariable(name, wrapper.wrap(value));
		} catch (TemplateModelException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends TemplateModel> T getLocalVariable(String name) throws TemplateModelException {
		return (T) env.getVariable(name);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends TemplateModel> T getVariable(String name) throws TemplateModelException {
		return (T)env.getVariable(name);
	}
	
	protected void storeVariable(String name) throws TemplateModelException{
		TemplateModel variable = env.getVariable(name);
		vars.add(new Pair<String, Object>(name,variable));
	}
	
	@SuppressWarnings("unchecked")
	public <T extends TemplateModel> T getGlobalVariable(String name) throws TemplateModelException {
		return (T)env.getGlobalVariable(name);
	}
	
	public <T> void setGlobalVariable(String name,T value){
		try {
			env.setGlobalVariable(name, wrapper.wrap(value));
		} catch (TemplateModelException e) {
			throw new RuntimeException(e);
		}
	}

}
