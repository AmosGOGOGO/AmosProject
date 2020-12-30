package com.ai.rti.ic.grp.ci.dao;


import org.apache.ibatis.annotations.Mapper;


import com.ai.rti.ic.grp.ci.entity.CiCustomGroupInfo;

@Mapper
public interface ICustomGroupInfoJDao {


	String selectOtherSysCustomGroupId(String customGroupId, String sysId);

	CiCustomGroupInfo queryCustomer(CiCustomGroupInfo demo);


}
