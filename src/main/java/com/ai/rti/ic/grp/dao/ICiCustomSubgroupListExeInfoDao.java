package  com.ai.rti.ic.grp.dao;

import com.ai.rti.ic.grp.entity.CiCustomSubgroupListExeInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICiCustomSubgroupListExeInfoDao {
  int deleteByPrimaryKey(String paramString);
  
  int insert(CiCustomSubgroupListExeInfo paramCiCustomSubgroupListExeInfo);
  
  int insertSelective(CiCustomSubgroupListExeInfo paramCiCustomSubgroupListExeInfo);
  
  CiCustomSubgroupListExeInfo selectByPrimaryKey(String paramString);
  
  int updateByPrimaryKeySelective(CiCustomSubgroupListExeInfo paramCiCustomSubgroupListExeInfo);
  
  int updateByPrimaryKey(CiCustomSubgroupListExeInfo paramCiCustomSubgroupListExeInfo);
}


