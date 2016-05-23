package com.jdog.frameworks.freemarker;


import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdog.frameworks.freemarker.directive.FreeMarkerBlockDirective;
import com.jdog.frameworks.freemarker.directive.FreeMarkerCheckCity;
import com.jdog.frameworks.freemarker.directive.FreeMarkerLeft;
import com.jdog.frameworks.freemarker.directive.FreeMarkerLiCheck;

import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.Environment;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerServletWrapper extends FreemarkerServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		super.init();
		
		this.getConfiguration().setTemplateExceptionHandler(new TemplateExceptionHandler() {
			public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
				System.out.println("### [FTL]: " +te.getMessage());
			}
		});
		
		this.getConfiguration().setCacheStorage(new freemarker.cache.MruCacheStorage(0, Integer.MAX_VALUE));
		this.getConfiguration().setSharedVariable("block", new FreeMarkerBlockDirective());
		this.getConfiguration().setSharedVariable("left", new FreeMarkerLeft());
		this.getConfiguration().setSharedVariable("liCheck", new FreeMarkerLiCheck());
		this.getConfiguration().setSharedVariable("checkCity", new FreeMarkerCheckCity());
	}
	
	/**
	 * 改进该方法以支持多重location
	 */
	protected TemplateLoader createTemplateLoader(String templatePath)
			throws IOException {
		String[] paths = templatePath.split(",");
		TemplateLoader[] loaders = new TemplateLoader[paths.length];
		int i = 0;
		for(String path : paths){
			loaders[i++] = super.createTemplateLoader(path.trim());
		}
		MultiTemplateLoader loader = new MultiTemplateLoader(loaders);
		return loader;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
	}
}
