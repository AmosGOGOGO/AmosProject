package com.ai.rti.ic.grp.ci.service.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.rti.ic.grp.ci.entity.AlarmRecord;
import com.ai.rti.ic.grp.ci.entity.ThresholdVarRef;
import com.ai.rti.ic.grp.ci.service.IAlarmRecordDao;
import com.ai.rti.ic.grp.ci.service.IAlarmRecordService;

@Service
public class AlarmRecordServiceImpl implements IAlarmRecordService {
	
	@Autowired
	private IAlarmRecordDao recordDao;
	private Logger log = Logger.getLogger(AlarmRecordServiceImpl.class);

	public IAlarmRecordDao getRecordDao() {
		return this.recordDao;
	}

	public void setRecordDao(IAlarmRecordDao recordDao) {
		this.recordDao = recordDao;
	}


	public List<AlarmRecord> getPageAlarmRecordByMore(String productId, String startTime, String endTime,
			List<String> thresholdIds, List<ThresholdVarRef> varRefs, String refType, String refId, String alarmType,
			String busiName, String busiId, String columnId, int checkFlag, int pageNum, int pageSize)
			throws Exception {
		List<AlarmRecord> result = this.recordDao.getPageAlarmRecordByMore(productId, startTime, endTime, thresholdIds,
				varRefs, refType, refId, alarmType, busiName, busiId, columnId, checkFlag, pageNum, pageSize);

		return result;
	}

}
