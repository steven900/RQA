package com.jdog.frameworks.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ManageSessionInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		/**
		 * 后台操作
		 */
		String uri = request.getRequestURI();
		if(uri.startsWith("/manage/")){
			// 后台统一刷成中文
			if(!uri.startsWith("/manage/login.do") && !uri.startsWith("/manage/loginSubmit.do") && !uri.startsWith("/manage/logout.do")) {
				HttpSession session = request.getSession();
				if(session.getAttribute("manage") == null) {
					response.sendRedirect("/manage/login.do");
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
		return true;
	}

	
	
	
}
