package com.jdog.frameworks.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreloadingFilter implements Filter {
	
//	private static CacheUtil cacheUtil = CacheUtil.getInstance();
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			preloadSettings((HttpServletRequest) request, (HttpServletResponse) response);
		}
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {}
	
	public void destroy() {}
	
	protected void preloadSettings(HttpServletRequest request,  HttpServletResponse response) {
	}
}
