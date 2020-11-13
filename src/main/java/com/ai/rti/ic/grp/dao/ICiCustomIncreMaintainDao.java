package  com.ai.rti.ic.grp.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICiCustomIncreMaintainDao {
  Long queryForCountByGroupId(String paramString);
}

