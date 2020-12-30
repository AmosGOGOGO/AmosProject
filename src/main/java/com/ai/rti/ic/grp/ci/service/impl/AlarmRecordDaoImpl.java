package com.ai.rti.ic.grp.ci.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ai.rti.ic.grp.ci.entity.AlarmRecord;
import com.ai.rti.ic.grp.ci.entity.ThresholdVarRef;
import com.ai.rti.ic.grp.ci.service.IAlarmRecordDao;
import com.ai.rti.ic.grp.ci.utils.AlarmDaoUtil;
import com.ai.rti.ic.grp.ci.utils.AlarmUtil;
import com.ai.rti.ic.grp.utils.StringUtil;

@Service
public class AlarmRecordDaoImpl implements IAlarmRecordDao {
	private Logger log = Logger.getLogger(AlarmRecordDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbTemplate;

	public List<AlarmRecord> getPageAlarmRecordByMore(String productId, String startTime, String endTime,
			List<String> thresholdIds, List<ThresholdVarRef> varRefs, String refType, String refId, String alarmType,
			String busiName, String busiId, String columnId, int checkFlag, int pageNum, int pageSize)
			throws Exception {
		String ids = AlarmUtil.List2String(thresholdIds, ",");

		String dialect = jdbTemplate.getDataSource().getConnection().getMetaData().getDatabaseProductName();
		String hql = getAlarmRecordQuerySql(productId, startTime, endTime, ids, varRefs, refType, refId, alarmType,
				busiName, busiId, columnId, (String) null, checkFlag, dialect);

		this.log.debug("getPageAlarmRecordByMore : hql = " + hql);
		List<AlarmRecord> result = jdbTemplate.query(hql.toString(), new BeanPropertyRowMapper<AlarmRecord>(AlarmRecord.class));

		return result;
	}

	private String getAlarmRecordQuerySql(String productId, String startTime, String endTime, String thresholdId,
			List<ThresholdVarRef> varRefs, String refType, String refId, String alarmType, String busiName,
			String busiId, String columnId, String levelId, int checkFlag, String dialect) {
		StringBuffer hql = new StringBuffer("SELECT DISTINCT R.RECORD_ID,");
		hql.append(" R.THRESHOLD_ID,");
		hql.append(" R.ALARM_CASE,");
		hql.append(" R.CALCULATE_DATE,");
		hql.append(" R.WEEK_ID,");
		hql.append(" R.BUSI_ID,");
		hql.append(" R.BUSI_NAME,");
		hql.append(" R.COLUMN_ID,");
		hql.append(" R.COLUMN_NAME,");
		hql.append(" R.RESULT_VALUE,");
		hql.append(" R.CHECK_FLAG");
		hql.append(" FROM COMP_ALARM_RECORD R,COMP_ALARM_THRESHOLD t");
		if (varRefs != null && varRefs.size() > 0) {

			int i = 0;
			hql.append(" ,( select s0.* from ");
			Map<Object, Object> varMap = new HashMap<>();
			for (ThresholdVarRef varRef : varRefs) {
				this.log.debug("getPageAlarmRecordByMore : varRef = " + varRef);
				String keyName = varRef.getAttColumn();
				String value = varRef.getMinValue();
				if (varMap.containsKey(keyName)) {
					String oldvalue = (String) varMap.get(keyName);
					value = oldvalue + "," + value;
					varMap.put(keyName, value);
					continue;
				}
				varMap.put(keyName, value);
			}

			if (varMap.size() > 0) {
				Set varSet = varMap.keySet();
				Iterator<String> varIt = varSet.iterator();
				while (varIt.hasNext()) {
					String name = varIt.next();
					String data = (String) varMap.get(name);
					data = data.replaceAll(",", "','");
					hql.append("( select * ");
					hql.append("from COMP_ALARM_THRESHOLD_VAR_REF ");
					hql.append("where attr_column = '" + name + "'");
					hql.append("and attr_min_value in ('" + data + "') ) s" + i + ", ");
					if (StringUtil.isNotEmpty(thresholdId) && !"null".equals(thresholdId)) {
						thresholdId = thresholdId.replaceAll(",", "','");
						hql.append(" and THRESHOLD_ID IN ('" + thresholdId + "')");
					}
					i++;
				}
			}
			if (hql.lastIndexOf(",") != -1) {
				hql = hql.deleteCharAt(hql.lastIndexOf(","));
			}
			hql.append(" where 1 = 1 ");
			for (int j = i - 1; j > 0; j--) {
				hql.append(" and s" + j + ".threshold_id = s" + (j - 1) + ".threshold_id");
			}
			hql.append(") s0");
		} else {
			hql.append(",COMP_ALARM_THRESHOLD_VAR_REF s0");
		}

		if (StringUtils.hasText(productId)) {
			hql.append(",COMP_ALARM_PRODUCT_RELATION s ").append(" where t.threshold_id = s.threshold_id ")
					.append(" and s.product_id = '").append(productId).append("' ");
		} else {

			hql.append(" where 1=1 ");
		}

		hql.append(" and T.THRESHOLD_ID = S0.THRESHOLD_ID");
		hql.append(" AND R.THRESHOLD_ID = T.THRESHOLD_ID");

		if (StringUtil.isNotEmpty(thresholdId) && !"null".equals(thresholdId)) {
			thresholdId = thresholdId.replaceAll(",", "','");
			hql.append(" and t.THRESHOLD_ID IN ('" + thresholdId + "')");
		}
		if (StringUtil.isNotEmpty(refType) && !"null".equals(refType)) {
			hql.append(" and t.ref_type = '" + refType + "'");
		}
		if (StringUtil.isNotEmpty(refId) && !"null".equals(refId)) {
			hql.append(" and t.ref_id = '" + refId + "'");
		}

		if (StringUtil.isNotEmpty(levelId) && !"null".equals(levelId)) {
			hql.append(" and t.level_id = '" + levelId + "'");
		}
		if (StringUtil.isNotEmpty(alarmType) && !"null".equals(alarmType)) {
			alarmType = alarmType.replaceAll(",", "','");
			hql.append(" and t.type_id in ('" + alarmType + "')");
		}
		if (StringUtil.isNotEmpty(busiName) && !"null".equals(busiName)) {
			hql.append(" and R.busi_name like '%" + busiName + "%'");
		}
		if (StringUtil.isNotEmpty(busiId) && !"null".equals(busiId)) {
			busiId = busiId.replaceAll(",", "','");
			hql.append(" and t.busi_id in ('" + busiId + "')");
		}
		if (StringUtil.isNotEmpty(columnId) && !"null".equals(columnId)) {
			hql.append(" and t.column_id = '" + columnId + "'");
		}
		if (checkFlag == 0 || checkFlag == 1) {
			hql.append(" and R.check_flag = " + checkFlag);
		}

		if (StringUtil.isNotEmpty(startTime) && !"null".equals(startTime)) {
			hql.append(" AND R.CALCULATE_DATE >= " + AlarmDaoUtil.translateStrToDate(dialect, startTime));
		}
		if (StringUtil.isNotEmpty(endTime) && !"null".equals(endTime)) {
			hql.append(" AND R.CALCULATE_DATE <= " + AlarmDaoUtil.translateStrToDate(dialect, endTime));
		}
		hql.append(" order by R.CALCULATE_DATE DESC");
		return hql.toString();
	}
}
