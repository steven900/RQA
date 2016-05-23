package com.jdog.frameworks.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

public class Util {
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> describe(Object bean){
		try {
			return BeanUtils.describe(bean);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static<T> T populate(T bean,@SuppressWarnings("rawtypes") Map properties){
		try {
			BeanUtils.populate(bean, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public static void copyProperties(Object target,Object src){
		if(target != null && src != null){
		try {
			BeanUtils.copyProperties(target,src);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}}
	}
	
	/**
	 * 获取参数列表,返回Map(忽略大小写) , 若无重复项,则只取一项,否则返回String[]
	 * @param request
	 * @return
	 */
	public static Map<String,Object> getParameters(HttpServletRequest request){
		
		Map<String,Object> m = new HashMap<String,Object>();
		
		Map<String,String[]> s = request.getParameterMap();
		
		for(Entry<String,String[]> en : s.entrySet()){
			
			if(en.getValue().length == 1){
				//因为tomcat做了 URIEncoding="GB18030"的配置，所以这里不需要转了，如果需要再开起来
//				if(request.getMethod()=="GET"){
//					try {
//						en.getValue()[0] = new String(en.getValue()[0].getBytes("ISO-8859-1"),"GBK");
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
//				}
				m.put(en.getKey(), en.getValue()[0]);
			}else{
				m.put(en.getKey(), en.getValue());
			}
			
		}
		
		return m;
	}
	
	public static void binaryTransport(InputStream in,OutputStream out){
		if(in !=null && out != null){
			int len = 0;
			byte[] buffer =new byte[4096];
			try {
				while((len = in.read(buffer)) != -1){
					out.write(buffer,0,len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isImage(String mime){
		return mime == null ? false : mime.startsWith("image/");
	}
	
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
