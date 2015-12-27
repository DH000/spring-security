package com.lin.security.common;

import org.slf4j.Logger;

/**
 * 
 * desc:   日志工具
 * @author xuelin
 * @date   Dec 17, 2015
 */
public final class LogUtils {
	
 	public static void info(Logger logger, String message){
 		if(logger.isInfoEnabled()){
 			logger.info(message);
 		}
 	}
 	
 	public static void debug(Logger logger, String message){
 		if(logger.isDebugEnabled()){
 			logger.debug(message);
 		}
 	}
 	
 	public static void error(Logger logger, String message){
 		if(logger.isErrorEnabled()){
 			logger.error(message);
 		}
 	}
 	
 	public static void warn(Logger logger, String message){
 		if(logger.isWarnEnabled()){
 			logger.warn(message);
 		}
 	}
 	
 	public static void out(Object message){
 		System.out.println(message);
 	}
	
}
