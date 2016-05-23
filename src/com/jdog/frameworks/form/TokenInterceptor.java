package com.jdog.frameworks.form;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jdog.frameworks.encode.Escape;

public class TokenInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			
			SetToken setToken = method.getAnnotation(SetToken.class);
			if(setToken != null) {
				request.getSession(true).setAttribute("token", UUID.randomUUID().toString());
			}
			
			CheckToken checkToken = method.getAnnotation(CheckToken.class);
			if(checkToken != null) {
				if (isRepeatSubmit(request)) {
					String msg = checkToken.msg();
					msg = Escape.escape(msg);
					msg = Escape.escape(msg);
					String redirectUrl = checkToken.redirectUrl();
					response.sendRedirect("/test/msg.do?msg=" + msg + "&redirectUrl=" + redirectUrl);
					return false;
				}
				request.getSession(true).removeAttribute("token");
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(true).getAttribute("token");
		if (serverToken == null) {
			return true;
		}
		String clientToken = request.getParameter("token");
		if (clientToken == null) {
			return true;
		}
		if (!serverToken.equals(clientToken)) {
			return true;
		}
		return false;
	}
}
