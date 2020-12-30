package com.ai.rti.ic.grp.ci.service;

import com.ai.rti.ic.grp.ci.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.ci.entity.CiCustomGroupPushCycle;
import com.ai.rti.ic.grp.ci.entity.CiCustomListInfo;
import com.ai.rti.ic.grp.ci.entity.CiCustomPushReq;
import java.util.List;

public interface ICustomersManagerService {

	void pushCustomerGroup(String customGroupId, String userId);

	String queryOtherSysCustomGroupId(String customGroupId, String sysId);

	CiCustomListInfo queryCiCustomListInfoById(String listTableName);

	CiCustomGroupInfo queryCiCustomGroupInfo(String customGroupId);

	void saveCiCustomGroupPushCycle(CiCustomGroupPushCycle pushCycleObj);

	void saveCiCustomPushReq(CiCustomPushReq ciCustomPushReq);

	void executeInBackDataBase(String createSql);

//	List<CiLabelRule> queryCiLabelRuleList(String customGroupId, Integer valueOf);

	List<CiCustomPushReq> queryCiCustomPushReq(CiCustomPushReq queryParam);

//	List<CiCustomGroupInfo> queryCustomersListTask(Pager pager, Object object, CiCustomGroupInfo customGroupInfo);

//	List<CiCustomGroupInfo> queryCustomersListTask(Pager pager, String paramString, CiCustomGroupInfo customGroupInfo);
}
