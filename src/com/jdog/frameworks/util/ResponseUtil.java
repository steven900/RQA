package com.jdog.frameworks.util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

	/**
	 * 设定HTTP缓存,会返回304
	 * @param req
	 * @param resp
	 * @param seconds
	 * @return
	 * @throws IOException
	 */
	public static boolean setCache(HttpServletRequest req,HttpServletResponse resp,int seconds) throws IOException{
		long millis = System.currentTimeMillis();
		
		long last_modified = millis - (millis % (seconds*1000L));
		long expires = last_modified + seconds * 1000L;
		
//		long if_last_modified = req.getDateHeader("If-Modified-Since");
//		
//		if(if_last_modified > 0 && if_last_modified == last_modified){
//			resp.setHeader("Expires", new Date(expires).toGMTString());
//			resp.setHeader("Cache-Control", "max-age="+seconds);
//			resp.setHeader("Last-Modified", new Date(last_modified).toGMTString());
//			resp.sendError(304);
//			return true;
//		}
		
		if(seconds > 0){
			resp.setHeader("Expires", new Date(expires).toString());
			resp.setHeader("Cache-Control", "max-age="+seconds);
			resp.setHeader("Last-Modified", new Date(last_modified).toString());
		}
		
		return false;
	}
}
