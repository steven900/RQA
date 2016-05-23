package com.jdog.frameworks.freemarker.directive;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

import com.jdog.frameworks.util.StringUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class FreeMarkerLiCheck implements TemplateDirectiveModel {

	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if(body != null){
			if(params.get("length") != null){
				StringWriter sw = new StringWriter();
				body.render(sw);
				String str = sw.toString();
				String liStr  = str.replaceAll("<span class=\"ml6 mr6 fb\">\\|</span>", ",");
				String[] liArray = liStr.split(",");
				String aStr = liStr.replaceAll("<li>", "").replaceAll("</li>", "");
				String nameStr = aStr.replaceAll("</a>", "");
				String[] nameStrArray = nameStr.split(",");
				
				int length = NumberUtils.toInt(params.get("length").toString(), 0);
				int rlength = 0;
				String html = "";
				
				for(int i=0;i<nameStrArray.length;i++) {
					if(nameStrArray[i].length() > 0) {
						String name = nameStrArray[i].substring(nameStrArray[i].indexOf(">") + 1, nameStrArray[i].length());
						int l = StringUtil.StringLength(name);
						if(i == nameStrArray.length) {
							rlength += l;
							html = str;
						} else {
							rlength += l + 3;
						}
					}
					if(rlength > length) {
						for(int j=0;j<i;j++) {
							if(j < i - 1) {
								html += liArray[j] + "<span class=\"ml6 mr6 fb\">|</span>";
							} else {
								if(liArray[j].endsWith("</li>")) {
									liArray[j].substring(0, liArray[j].length() - 4);
								}
								html += liArray[j] + "<span class=\"ml6 mr6 fb\">|</span></li>";
								html += params.get("append") == null ? "" : params.get("append");
							}
						}
						break;
					}
				}
				if(rlength <= length) {
					env.getOut().write(str);
				}
				env.getOut().write(html);
			}
		}
	}
}
