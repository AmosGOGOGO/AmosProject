package  com.ai.rti.ic.grp.dao;

import com.ai.rti.ic.grp.entity.CiGroupAttrRelNew;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICiGroupAttrRelNewDao {
  void insertSelective(CiGroupAttrRelNew paramCiGroupAttrRelNew);
}

