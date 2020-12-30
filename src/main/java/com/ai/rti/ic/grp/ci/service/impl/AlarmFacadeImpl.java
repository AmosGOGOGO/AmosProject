package com.ai.rti.ic.grp.ci.service.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.rti.ic.grp.ci.entity.AlarmRecord;
import com.ai.rti.ic.grp.ci.entity.ThresholdVarRef;
import com.ai.rti.ic.grp.ci.service.IAlarmFacade;
import com.ai.rti.ic.grp.ci.service.IAlarmRecordService;

@Service
public class AlarmFacadeImpl implements IAlarmFacade {
	private Logger log = Logger.getLogger(AlarmFacadeImpl.class);
	
	@Autowired
	private IAlarmRecordService recordService;


	public List<AlarmRecord> getPageAlarmRecordByMore(String startTime, String endTime, List<ThresholdVarRef> varRefs,
			String refType, String refId, String alarmType, String busiId, String columnId, int pageNum, int pageSize)
			throws Exception {
		List<AlarmRecord> result = this.recordService.getPageAlarmRecordByMore(null, startTime, endTime, null, varRefs,
				refType, refId, alarmType, null, busiId, columnId, -1, pageNum, pageSize);

		return result;
	}

	public List<AlarmRecord> getPageAlarmRecordByMore(String productId, String startTime, String endTime,
			List<String> thresholdIds, List<ThresholdVarRef> varRefs, String refType, String refId, String alarmType,
			String busiName, String busiId, String columnId, int checkFlag, int pageNum, int pageSize)
			throws Exception {
		List<AlarmRecord> result = this.recordService.getPageAlarmRecordByMore(productId, startTime, endTime,
				thresholdIds, varRefs, refType, refId, alarmType, busiName, busiId, columnId, checkFlag, pageNum,
				pageSize);

		return result;
	}

}
