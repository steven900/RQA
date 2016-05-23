package wx.weixin.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import wx.weixin.data.ParametersAPI;

public class WxLog {

	public static final boolean log = ParametersAPI.log;
	
	public static void p(Object obj){
		if(log){
			System.out.println("----------"+new Date()+"----------");
			System.out.println(obj);
		}
	}
	
	public static void  url(HttpServletRequest request){
		if(log){
			String uri = request.getRequestURI();
			String params = request.getQueryString();
			p(uri+"?"+params);
		}
	}
}
