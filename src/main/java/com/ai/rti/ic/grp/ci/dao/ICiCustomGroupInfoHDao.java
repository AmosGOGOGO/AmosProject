package com.ai.rti.ic.grp.ci.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ai.rti.ic.grp.ci.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.ci.entity.CiCustomGroupPushCycle;

@Mapper
public interface ICiCustomGroupInfoHDao {

	List<CiCustomGroupPushCycle> selectPushCycleByGroupId(String customGroupId);

	void insertCustomGroupPushCycle(CiCustomGroupPushCycle pushCycle);

	List<CiCustomGroupInfo> selectCiCustomGroupInfoListByName(@Param("customGroupName")String customGroupName, @Param("userId")String userId,
			String createCityId);

	CiCustomGroupInfo selectCustomGroupById(@Param("ciCustomGroupInfoId")String ciCustomGroupInfoId);


}
