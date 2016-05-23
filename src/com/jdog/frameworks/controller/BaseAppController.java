package com.jdog.frameworks.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class BaseAppController {

	/**
	 * 统一处理返回信息JSON格式
	 * @param ret 1 调用成功 0 调用失败
	 * @param data 具体数据
	 * @param msg 文字消息
	 * @return
	 */
	protected String msg(int ret, Object data, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ret", ret);
		map.put("data", data);
		map.put("msg", msg);
		return JSON.toJSONString(map);
	}
}
