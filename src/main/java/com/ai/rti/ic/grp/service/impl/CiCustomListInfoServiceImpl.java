 package com.ai.rti.ic.grp.service.impl;
 
 import com.ai.rti.ic.grp.dao.ICiCustomListInfoDao;
 import com.ai.rti.ic.grp.entity.CiCustomListInfo;
 import com.ai.rti.ic.grp.service.ICiCustomListInfoService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 
 @Service("ciCustomListInfoService")
 public class CiCustomListInfoServiceImpl
   implements ICiCustomListInfoService
 {
   @Autowired
   private ICiCustomListInfoDao ciCustomListInfoDao;
   
   public List<CiCustomListInfo> queryCiCustomListInfo(CiCustomListInfo ciCustomListInfo) {
     List<CiCustomListInfo> list = this.ciCustomListInfoDao.selectSelective(ciCustomListInfo);
     return list;
   }
 
 
   
   public void insertSelective(CiCustomListInfo ciCustomListInfo) {
     this.ciCustomListInfoDao.insertSelective(ciCustomListInfo);
   }
 
 
   
   public void updateSelective(CiCustomListInfo ciCustomListInfo) {
     this.ciCustomListInfoDao.updateSelective(ciCustomListInfo);
   }

	@Override
	public void deleteCustomListInfo(String customGroupId, String dataDate) {
		this.ciCustomListInfoDao.deleteCustomListInfo(customGroupId, dataDate);
	}
 }
