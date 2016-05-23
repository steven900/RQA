package com.jdog.frameworks.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HtmlGenerator {
	
	private Configuration config;
	
	public Configuration getConfig() {  
		return config;  
	}
	public void setConfig(Configuration config) {
		this.config = config;
	}

	/**
	 * 初始化
	 */
	private void init(String filePath) throws Exception {
		config = new Configuration();
		config.setDirectoryForTemplateLoading(new File(filePath));
		config.setEncoding(Locale.CHINA, "utf-8");
	}
	
	/**
	 * 生成静态文件
	 * @param filePath 模板路径
	 * @param templateFile 模板文件名
	 * @param htmlFile 目标html文件路径和文件名
	 * @param params 所有模板中需要用到的变量 Map形式
	 */
	public boolean generate(String webroot, String templateFile, String htmlPath, String htmlFile, Map<String, Object> params) {
		try {
			init(webroot);
			Map<String, Object> root = new HashMap<String, Object>();
			Template template = getConfig().getTemplate(templateFile);
			if(params.keySet() != null) {
				for(String key : params.keySet()) {
					Object value = params.get(key);
					root.put(key, value);
				}
			}
			File file = new File(webroot + htmlPath);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			try {
				Writer out = new OutputStreamWriter(new FileOutputStream(webroot + htmlPath + htmlFile), "utf-8");
				template.process(root, out);
				return true;
			} catch(Exception e) {
				File delFile = new File(webroot + htmlPath + htmlFile);
				delFile.delete();
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
