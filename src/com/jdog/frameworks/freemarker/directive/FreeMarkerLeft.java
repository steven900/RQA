package com.jdog.frameworks.freemarker.directive;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class FreeMarkerLeft  implements TemplateDirectiveModel{

	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if(body != null){
			if(params.get("length") != null){
				StringWriter sw = new StringWriter();
				body.render(sw);
				String str = sw.toString();
				int length = NumberUtils.toInt(params.get("length").toString(), 0);
				env.getOut().write(StringUtils.left(str,length));
				if(params.get("append") != null){
					if(str.length() > length){
						env.getOut().write(params.get("append").toString());
					}
				}
			}
		}
	}

}
