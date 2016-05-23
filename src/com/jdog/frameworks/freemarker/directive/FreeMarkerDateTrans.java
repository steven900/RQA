package com.jdog.frameworks.freemarker.directive;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import com.jdog.frameworks.util.DateUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class FreeMarkerDateTrans implements TemplateDirectiveModel{

	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map map, TemplateModel[] arg2,
			TemplateDirectiveBody arg3) throws TemplateException, IOException {
		
		Object obj =  map.get("dt");
		String dt = obj+"";
		Date date = DateUtil.parse(dt);
		long t1 = date.getTime();
		long t2 = new Date().getTime();
		long tz = (t2 - t1)/(1000*60);
		if(tz == 0){
			env.getOut().write("1分钟前");
			return;
		}
		if(tz < 60){
			env.getOut().write(tz+"分钟前");
			return;
		}
		tz = tz /60;
		if(tz < 24){
			env.getOut().write(tz+"小时前");
			return ;
		}
		if(tz > 24){
			tz = tz / 24;
			env.getOut().write(tz+"天前");
			return ;
		}
		
	}

}
