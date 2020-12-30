package com.ai.rti.ic.grp.ci.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ai.rti.ic.grp.ci.entity.CiSysInfo;
@Mapper
public interface ICiSysInfoHDao {

	CiSysInfo selectById(String id);

}
