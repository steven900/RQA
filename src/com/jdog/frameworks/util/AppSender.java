package com.jdog.frameworks.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class AppSender {

	// {retcode:"1" retmsg:"" object:返回对象}

	public static final int relsuccess = 1;
	public static final int relfail = 0;

	// 换回信息统一接口
	public static String msg(int rel, Object data, String msg) {
		return msg(rel + "", data, msg);
	}

	// 直接成功发送
	public static String msgSuccess(Object data, String msg) {
		return msg(relsuccess + "", data, msg);
	}

	
	// 消息成功无数据提示
	public static String msgSuccess(String msg) {
		return msg(relsuccess + "", null, msg);
	}

	
	public static String msgSuccess(Object data) {
		return msg(relsuccess + "", data, null);
	}

	// 直接失败发送
	public static String msgFail(Object data, String msg) {
		return msg(relfail + "", data, msg);
	}

	
	// 直接失败发送，直接提示
	public static String msgFail( String msg) {
		return msg(relfail + "", null, msg);
	}
	
	public static String msg(String rel, Object data, String msg) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("retmsg", msg);
		map.put("retcode", rel);
		map.put("data", data);
		return JSONObject.toJSONString(map).toString();
	}

	
}
