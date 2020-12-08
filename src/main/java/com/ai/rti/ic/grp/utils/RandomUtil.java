package com.ai.rti.ic.grp.utils;

public class RandomUtil {

	/**
	 * 
	 * @param len 1-16
	 * @return
	 * 
	 */
	public static String getRandom(int len) {
		if(len>0 && len < 17) {
			String str = "" + Math.random();
			return str.substring(2, 2+len);
		}
		
		return null;
	}
}
