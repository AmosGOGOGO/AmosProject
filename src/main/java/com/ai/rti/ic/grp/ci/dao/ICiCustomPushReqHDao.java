package com.ai.rti.ic.grp.ci.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ai.rti.ic.grp.ci.entity.CiCustomPushReq;

@Mapper
public interface ICiCustomPushReqHDao {

	void insertCiCustomPushReq(CiCustomPushReq req);

	void updateCiCustomPushReqById(CiCustomPushReq req);
	
	List<CiCustomPushReq> select(CiCustomPushReq ciCustomPushReq);

}
