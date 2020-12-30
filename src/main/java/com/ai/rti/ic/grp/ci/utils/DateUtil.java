package com.ai.rti.ic.grp.ci.utils;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ai.rti.ic.grp.utils.StringUtil;

public class DateUtil {

	public static String date2String(Date date, String formatPra) {
		String format = formatPra;
		if (date == null) {
			return "";
		}
		if (format == null || format.equals("")) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		String result = "";
		DateFormat dateFormat = new SimpleDateFormat(format);
		result = dateFormat.format(date);
		return result;
	}
	public static Timestamp string2Timestamp(String sdate) {
		if (sdate == null) {
			return null;
		}
		Timestamp ts = null;
		try {
			ts = Timestamp.valueOf(sdate);
		} catch (Exception exception) {
		}

		return ts;
	}
	public static String getFrontDay(int dayNum, String dataDate, String dateFormat) {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		try {
			Date date = format.parse(dataDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (!StringUtil.isEmpty(dataDate)) {
				calendar.add(5, -dayNum);
				dateStr = format.format(calendar.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	public static Date lastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int value = cal.getActualMaximum(5);
		cal.set(5, value);
		return cal.getTime();
	}
}
