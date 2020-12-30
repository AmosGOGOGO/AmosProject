package com.ai.rti.ic.grp.ci.utils;

import org.apache.log4j.Logger;

public class AlarmDaoUtil {
	private static Logger log = Logger.getLogger(AlarmDaoUtil.class);

	public static String translateStrToDate(String dialect, String colValue) {
		String result = "";
		String[] temp = colValue.split(":");
		int size = temp.length;
		if (size == 1) {
			if (colValue.indexOf(" ") == -1) {

				colValue = colValue + " 00:00:00";
			} else {

				colValue = colValue + ":00:00";
			}

		} else if (size == 2) {

			colValue = colValue + ":00";
		}

		if (dialect.contains("DB2")) {
			result = "TIMESTAMP('" + colValue + "')";
		} else if (dialect.contains("Oracle")) {
			result = "TO_TIMESTAMP('" + colValue + "', 'YYYY-MM-DD HH24:MI:SS')";
		} else if (dialect.contains("MySQL")) {
			result = "STR_TO_DATE('" + colValue + "', '%Y-%m-%d %H:%i:%s')";
		}
		log.debug("translateStrToDate dialect===" + dialect + "***");

		return result;
	}
}
