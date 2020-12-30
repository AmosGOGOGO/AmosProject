package com.ai.rti.ic.grp.ci.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ai.rti.ic.grp.ci.entity.MarketManageSubDomain;
@Mapper
public interface IMarketManageSubDomainJDao {

	List<Map<String, Object>> queryDimList(@Param("masterDataCode")String masterDataCode);

	List<MarketManageSubDomain> queryObjList(MarketManageSubDomain marketManageSubDomain);

}
