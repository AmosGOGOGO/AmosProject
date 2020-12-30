package com.ai.rti.ic.grp.ci.utils;

import java.util.List;

public class AlarmUtil {
	public static String List2String(List<String> ids, String splitStr) {
		String result = "";
		if (ids != null && ids.size() > 0) {
			result = String.valueOf(ids).replaceAll(", ", splitStr).replace("[", "").replace("]", "");
		}
		return result;
	}

}
