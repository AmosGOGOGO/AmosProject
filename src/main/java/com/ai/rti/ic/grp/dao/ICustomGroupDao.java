package  com.ai.rti.ic.grp.dao;

import com.ai.rti.ic.grp.entity.CiCustomGroupInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICustomGroupDao {
  List<CiCustomGroupInfo> selectCiCustomGroupListInfo(CiCustomGroupInfo paramCiCustomGroupInfo);
  
  void insertSelective(CiCustomGroupInfo paramCiCustomGroupInfo);
  
  Map getNewDate(Integer paramInteger);
  
  String getBaseTable(Integer paramInteger);
  
  void updateSelective(CiCustomGroupInfo paramCiCustomGroupInfo);
}

