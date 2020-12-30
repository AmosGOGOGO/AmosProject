package com.ai.rti.ic.grp.ci.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.ai.rti.ic.grp.ci.entity.AlarmRecord;
import com.ai.rti.ic.grp.ci.entity.ThresholdVarRef;

@WebService
public interface IAlarmFacade {

	@WebMethod
	List<AlarmRecord> getPageAlarmRecordByMore(String paramString1, String paramString2, String paramString3,
			List<String> paramList, List<ThresholdVarRef> paramList1, String paramString4, String paramString5,
			String paramString6, String paramString7, String paramString8, String paramString9, int paramInt1,
			int paramInt2, int paramInt3) throws Exception;

}
