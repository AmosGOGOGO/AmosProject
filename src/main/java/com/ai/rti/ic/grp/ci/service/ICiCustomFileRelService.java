package com.ai.rti.ic.grp.ci.service;

import com.ai.rti.ic.grp.ci.entity.CiGroupAttrRel;
import com.alibaba.fastjson.JSONArray;
import java.util.List;

public interface ICiCustomFileRelService {

	boolean refreshCustListToRedis(Integer customGroupDataType, String listTableName, String customGroupId, String colum,
			JSONArray activityInfoList, long longValue);

	boolean attrPushRedis(List<CiGroupAttrRel> ciGroupAttrRelList, String customGroupId);
}
