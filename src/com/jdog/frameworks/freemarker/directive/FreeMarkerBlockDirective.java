package com.jdog.frameworks.freemarker.directive;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jdog.frameworks.freemarker.BlockContext;
import com.jdog.frameworks.freemarker.BlockContextImpl;
import com.jdog.frameworks.freemarker.FreemarkerServletWrapper;

import freemarker.core.Environment;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.ServletContextHashModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class FreeMarkerBlockDirective implements TemplateDirectiveModel{
	
	private static final int DEFAULT_AGE = 1*60*60;
	
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String key = params.get("key") + "";
		Object expire = params.get("exp");
		if(expire == null) expire = params.get("expire");
		
		boolean useCache = key != null && expire != null && NumberUtils.isDigits(expire.toString());
		

		HttpRequestHashModel model = (HttpRequestHashModel)env.getDataModel().get(FreemarkerServletWrapper.KEY_REQUEST);
		
		if(model != null && useCache)
			useCache =(model.getRequest().getParameter("clear") == null);
		
//		if(useCache){
//			Object data;
//			if((data = CacheUtil.getInstance().get(key)) != null){
//				env.getOut().write(data.toString());
//				return;
//			}
//		}
//		
		
		Object mode = params.get("mode");
		
		mode = mode != null ? mode : "spring";
		
		BlockContextImpl context = new BlockContextImpl(env);
		
//		for(Object p : params.entrySet()){
//			Entry o = (Entry)p;
//			context.setLocalVariable(o.getKey().toString(), o.getValue());
//		}
		
		if("spring".equals(mode)){
			
			Object bean = params.get("bean");
			Object method = params.get("method");
			
			if(bean != null && method != null){
				
				ServletContextHashModel servletModel = (ServletContextHashModel)env.getDataModel().get(FreemarkerServletWrapper.KEY_APPLICATION);
				WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletModel.getServlet().getServletContext());
				Object actBean = webApplicationContext.getBean(bean.toString());
				
//				context.setSpringContext(webApplicationContext);
				
				
				try {
					actBean.getClass().getMethod(method.toString(), BlockContext.class).invoke(actBean, context);
				} catch(Exception e){
					e.printStackTrace();
					throw new TemplateException(e, env);
				}
				
			}
			
		}else{
			throw new TemplateException("no such mode in [@block]: " + mode,env);
		}
		
		if(useCache){
//			String dom = gatherRender(body);
//			CacheUtil.getInstance().put(key, dom, NumberUtils.toInt(expire.toString(), DEFAULT_AGE));
//			env.getOut().write(dom);
		}else{
			if(body != null)
				body.render(env.getOut());
		}
		
//		还原本地变量
//		if(context != null){
//			context.restoreVariables();
//		}
		
	}
	
	private String gatherRender(TemplateDirectiveBody body) throws TemplateException, IOException{
		if(body != null){
			StringWriter sw = new StringWriter();
			body.render(sw);
			return sw.toString();
		}
		return "";
	}

}
