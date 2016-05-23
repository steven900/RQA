package com.jdog.project.bugmanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/wxcard")
public class BugController {

	@RequestMapping(value = "pics.do")
	@ResponseBody
	public String pics(HttpServletRequest request,Model model,HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<String> shuffers =new ArrayList<String>();
		shuffers.add("http://139.196.38.10:8080/uploadImages/img/2016-03/30/1459325113144.jpg");
		shuffers.add("http://139.196.38.10:8080/uploadImages/img/2016-05/09/1462794874598.jpg");
		
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		Map<String,Object> d1= new HashMap<String,Object>();
		d1.put("img", "http://139.196.38.10:8080/uploadImages/img/2016-03/30/1459325113144.jpg");
		data.add(d1);
		
		return JSONArray.toJSONString(data).toString();
	}
	
}
