package com.jdog.frameworks.log;

import org.apache.log4j.Logger;

// TODO 整体改造
public class Log {

	private static Logger logger = null;
	
	static {
		logger = Logger.getLogger("EntityValidate");
	}
	
	private Log() {}
	
	public static void info(String message) {
		logger.info(message);
	}
	
	public static void debug(String message) {
		logger.debug(message);
	}
}
