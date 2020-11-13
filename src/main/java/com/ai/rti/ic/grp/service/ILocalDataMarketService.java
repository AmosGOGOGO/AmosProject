package com.ai.rti.ic.grp.service;

import com.ai.rti.ic.grp.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.entity.DataSource;
import com.ai.rti.ic.grp.exception.ICException;
import com.alibaba.fastjson.JSONArray;
import java.sql.Connection;
import java.util.List;

public interface ILocalDataMarketService {
  List<CiCustomGroupInfo> queryCiCustomGroupInfoList(CiCustomGroupInfo paramCiCustomGroupInfo) throws ICException;
  
  String createWhereSQL(JSONArray paramJSONArray, String paramString);
  
  String createFromSQL(JSONArray paramJSONArray1, JSONArray paramJSONArray2);
  
  Connection getConnection(DataSource paramDataSource);
  
  void updateSelective(CiCustomGroupInfo paramCiCustomGroupInfo);
}

