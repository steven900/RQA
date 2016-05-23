package com.jdog.frameworks.freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jdog.frameworks.freemarker.FreemarkerServletWrapper;

import freemarker.core.Environment;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 对应FTL页面上的 [@checkcity /]宏命令，用于检查是否需要首先切换城市
 */
public class FreeMarkerCheckCity implements TemplateDirectiveModel {

	public void execute(Environment environment, @SuppressWarnings("rawtypes") Map map, TemplateModel[] model,
			TemplateDirectiveBody directiveBody) throws TemplateException, IOException {
		HttpRequestHashModel hashModel = (HttpRequestHashModel) environment.getDataModel().get(FreemarkerServletWrapper.KEY_REQUEST);
		HttpServletRequest request = hashModel.getRequest();
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		Writer out = environment.getOut();
		sb.append("<br/>");
		sb.append("<div style=\"margin-left:20px;\">");
		if(session.getAttribute("city") == null) {
			sb.append("请先切换城市，切换后刷新本页面即可");
			sb.append("</div>");
			out.append(sb.toString());
			out.flush();
			out.close();
		} else {
			environment.getOut().write("");
		}
	}
}
