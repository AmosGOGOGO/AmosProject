package com.ai.rti.ic.grp.ci.utils;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.StringUtil;

public class CiUtil {
	private static Logger log = Logger.getLogger(CiUtil.class);

	public static synchronized String convertLongMillsToYYYYMMDDHHMMSS(long longMills) {
		Calendar caldTmp = Calendar.getInstance();
		if (longMills > 0L) {
			caldTmp.setTimeInMillis(longMills);
		} else {
			caldTmp.setTimeInMillis(System.currentTimeMillis());
			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
				log.error("convertLongMillsToYYYYMMDDHHMMSS sleep error");
			}
		}
		StringBuffer res = (new StringBuffer()).append(caldTmp.get(1));
		String tmpStr = String.valueOf(caldTmp.get(2) + 1);
		tmpStr = (tmpStr.length() < 2) ? ("0" + tmpStr) : tmpStr;
		res.append(tmpStr);
		tmpStr = String.valueOf(caldTmp.get(5));
		tmpStr = (tmpStr.length() < 2) ? ("0" + tmpStr) : tmpStr;
		res.append(tmpStr);
		res.append(caldTmp.get(11));
		tmpStr = String.valueOf(caldTmp.get(12));
		tmpStr = (tmpStr.length() < 2) ? ("0" + tmpStr) : tmpStr;
		res.append(tmpStr);
		tmpStr = String.valueOf(caldTmp.get(13));
		tmpStr = (tmpStr.length() < 2) ? ("0" + tmpStr) : tmpStr;
		res.append(tmpStr);
		tmpStr = String.valueOf(caldTmp.get(14));
		res.append(tmpStr);
		String serverId = Config.getObject("CURRENT_SERVER_ID");

		if (StringUtil.isNotEmpty(serverId) && serverId.startsWith("S")) {
			String ServerIdCharAfterS = serverId.substring(1);
			res.append(ServerIdCharAfterS);
		}
		return res.toString();
	}


}
