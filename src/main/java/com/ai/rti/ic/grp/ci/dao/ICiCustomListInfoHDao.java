package com.ai.rti.ic.grp.ci.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ai.rti.ic.grp.ci.entity.CiCustomListInfo;

@Mapper
public interface ICiCustomListInfoHDao {


	CiCustomListInfo selectById(String listTableName);

	List<CiCustomListInfo> selectByCustomGroupId(String customGroupId);

	List<CiCustomListInfo> selectPeriodCustomListInfo(String customGroupId);

}
