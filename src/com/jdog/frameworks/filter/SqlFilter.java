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

public class SqlFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		boolean r1 = this.checkSQL(req, res);

		if (r1 == true) {
			chain.doFilter(request, response);
		} else {
			res.setStatus(403);
		}
	}

	private boolean checkSQL(HttpServletRequest req, HttpServletResponse res) {
		boolean flag = true;
		String query = "";
		if (req.getQueryString() != null) {
			query = req.getQueryString().toLowerCase();
		}
		String XH_In2 = "script|declare|nvarchar|exec|xp_cmdshell|delete%20from|truncate|drop%20table|net%20user|xp_cmdshell|net%20localgroup%20administrators|update%20|onerror|alert|%3c|%3e|'|%22|%27|<|>";
		String[] symbol2 = XH_In2.split("\\|");
		for (int i = 0; i < symbol2.length; i++) {
			if (query.indexOf(symbol2[i]) != -1) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}
}
