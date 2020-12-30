package  com.ai.rti.ic.grp.dao;

import com.ai.rti.ic.grp.entity.CiCustomListInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ICiCustomListInfoDao {
  List selectSelective(CiCustomListInfo paramCiCustomListInfo);
  
  void insertSelective(CiCustomListInfo paramCiCustomListInfo);
  
  void updateSelective(CiCustomListInfo paramCiCustomListInfo);
  
  void updateSubgroupRule(String paramString, Long paramLong);

  void deleteCustomListInfo(@Param("customGroupId")String customGroupId, @Param("dataDate")String dataDate);
}

