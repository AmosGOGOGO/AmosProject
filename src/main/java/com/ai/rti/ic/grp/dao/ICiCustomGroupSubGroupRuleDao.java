package com.ai.rti.ic.grp.dao;

import com.ai.rti.ic.grp.entity.CiCustomGroupSubGroupRule;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICiCustomGroupSubGroupRuleDao {
  List<CiCustomGroupSubGroupRule> querySubCustomGroupInfos(CiCustomGroupSubGroupRule paramCiCustomGroupSubGroupRule);
  
  void updateSubgroupRule(CiCustomGroupSubGroupRule paramCiCustomGroupSubGroupRule);
}


