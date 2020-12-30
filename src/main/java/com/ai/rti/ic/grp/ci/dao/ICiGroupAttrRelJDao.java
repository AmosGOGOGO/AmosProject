package com.ai.rti.ic.grp.ci.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ai.rti.ic.grp.ci.entity.CiGroupAttrRel;

@Mapper
public interface ICiGroupAttrRelJDao {

	List<CiGroupAttrRel> selectCiGroupAttrRelList(String customGroupId, Date dataTime);

	List<CiGroupAttrRel> selectNewestCiGroupAttrRelList(String groupInfoId);
}
