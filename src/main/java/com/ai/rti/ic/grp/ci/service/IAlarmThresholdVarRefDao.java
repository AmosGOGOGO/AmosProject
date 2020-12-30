package com.ai.rti.ic.grp.ci.service;

import java.util.List;
import java.util.Set;

import com.ai.rti.ic.grp.ci.entity.ThresholdVarRef;

public interface IAlarmThresholdVarRefDao {
	void saveOrUpdateThresholdVarRef(ThresholdVarRef paramThresholdVarRef) throws Exception;

	void mergeThresholdVarRef(ThresholdVarRef paramThresholdVarRef) throws Exception;

	void deleteAlarmThresholdVarRef(String paramString) throws Exception;

	void deleteAllAlarmThresholdVarRef(List<ThresholdVarRef> paramList) throws Exception;

	List<ThresholdVarRef> queryAlarmThresholdVarRefs(Set<String> paramSet) throws Exception;

	List<ThresholdVarRef> queryAlarmThresholdVarRefs(String paramString) throws Exception;

	String[] queryThresholdIdByVarRefs(List<ThresholdVarRef> paramList) throws Exception;
}
