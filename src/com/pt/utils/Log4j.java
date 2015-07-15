package com.pt.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4j {

	public static void sample(Exception m){
		Logger logger = Logger.getLogger(Log4j.class);
		PropertyConfigurator.configure("config/log4j.properties");
		logger.error(m.getMessage()+"错误",m);
	}
}
