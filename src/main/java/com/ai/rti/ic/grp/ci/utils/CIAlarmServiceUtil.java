package com.ai.rti.ic.grp.ci.utils;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.ai.rti.ic.grp.ci.entity.AlarmRecord;
import com.ai.rti.ic.grp.ci.service.IAlarmFacade;
import com.ai.rti.ic.grp.ci.service.impl.AlarmFacadeImpl;
import com.ai.rti.ic.grp.utils.SpringContextUtil;

public class CIAlarmServiceUtil {
	private static Logger log = Logger.getLogger(CIAlarmServiceUtil.class);


	public static List<AlarmRecord> getPageAlarmRecords(String startTime, String endTime, String userId,
			String alarmType, String busiName, String busiId, String columnId, int checkFlag, int pageNum, int pageSize)
			throws Exception {
		IAlarmFacade alarmFacade = (IAlarmFacade) SpringContextUtil.getBean(AlarmFacadeImpl.class);
		return alarmFacade.getPageAlarmRecordByMore("COC", startTime, endTime, null, null, "user", userId, alarmType,
				busiName, busiId, columnId, checkFlag, pageNum, pageSize);
	}
	public static boolean haveAlarmRecords(String date, String userId, String alarmType, String busiId)
			throws Exception {
		boolean result = false;
		String startTime = date;
		String endTime = date;
		if (null != date) {
			if (date.length() == 6) {
				startTime = date.substring(0, 4) + "-" + date.substring(4, 6) + "-01 00:00:00";
				endTime = DateUtil.date2String(DateUtil.lastDayOfMonth(DateUtil.string2Timestamp(startTime)),
						"yyyy-MM-dd") + " 23:59:59";
			}
			if (date.length() == 8) {
				startTime = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8)
						+ " 00:00:00";
				endTime = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " 23:59:59";
			}
		}
		List<AlarmRecord> alarmRecordList = getPageAlarmRecords(startTime, endTime, userId, alarmType, null, busiId,
				null, -1, 0, 2147483647);
		if (CollectionUtils.isNotEmpty(alarmRecordList)) {
			result = true;
		}
		return result;
	}

}
