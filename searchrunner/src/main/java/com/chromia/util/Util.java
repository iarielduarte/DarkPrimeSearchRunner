package com.chromia.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Util {

	/**
	 * @author Ariel Duarte
	 * @since 30-Oct-2013
	 * @return url base
	 */
	public static String baseurl() {
		return "http://localhost:8080/chromia/";

	}

	public static String basepathlogin() {
		return "/chromia/faces/";

	}

	public static String basepath() {
		return "/faces/pages/";
	}

	public static void logger(String msg, String log) {
		Log logger = LogFactory.getLog("MENSAJE LOG: ");
		switch (log) {
		case "info":
			logger.info(msg);
			break;
		case "error":
			logger.error(msg);
			break;
		case "debug":
			logger.debug(msg);
			break;
		}

	}
}
