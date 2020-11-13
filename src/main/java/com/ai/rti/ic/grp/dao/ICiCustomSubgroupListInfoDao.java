package  com.ai.rti.ic.grp.dao;

import com.ai.rti.ic.grp.entity.CiCustomSubgroupInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICiCustomSubgroupListInfoDao {
  int insert(CiCustomSubgroupInfo paramCiCustomSubgroupInfo);
  
  int insertSelective(CiCustomSubgroupInfo paramCiCustomSubgroupInfo);
}

