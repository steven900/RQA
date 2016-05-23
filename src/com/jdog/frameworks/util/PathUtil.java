package com.jdog.frameworks.util;

/**
 * 
 * java类获取web应用的根目录
 * 
 */
public class PathUtil {

	/**
	 * 当前WEB应用类目录
	 * 
	 * @return
	 */
	public String getWebClassesPath() {
		String path = this.getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		return path;

	}

	/**
	 * 当前WEB应用WEB-INF目录
	 * 
	 * @return
	 * @throws IllegalAccessException
	 */
	public String getWebInfPath() {
		try {
			String path = getWebClassesPath();
			if (path.indexOf("WEB-INF") > 0) {
				path = path.substring(0, path.indexOf("WEB-INF") + 8);
			} else {
				throw new IllegalAccessException("路径获取错误");
			}
			return path;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 当前WEB应用根目录
	 * 
	 * @return
	 * @throws IllegalAccessException
	 */
	public String getWebRoot() {
		try {
			String path = getWebClassesPath();
			if (path.indexOf("WEB-INF") > 0) {
				path = path.substring(0, path.indexOf("WEB-INF/classes"));
			} else {
				throw new IllegalAccessException("路径获取错误");
			}
			return path;
		} catch (Exception e) {
			return "";
		}
	}

}