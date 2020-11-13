package com.ai.rti.ic.grp.service;

import com.ai.rti.ic.grp.entity.CiCustomListInfo;
import java.util.List;

public interface ICiCustomListInfoService {
  List<CiCustomListInfo> queryCiCustomListInfo(CiCustomListInfo paramCiCustomListInfo);
  
  void insertSelective(CiCustomListInfo paramCiCustomListInfo);
  
  void updateSelective(CiCustomListInfo paramCiCustomListInfo);
}

