package  com.ai.rti.ic.grp.dao;

import com.ai.rti.ic.grp.entity.TarGrpImportTask;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ITarGrpImportTaskDao {
  int updateByPrimaryKeySelective(TarGrpImportTask paramTarGrpImportTask);
  
  TarGrpImportTask getTarGrpTask(@Param("cityIdList")List<String> cityIdList);
  
  List<TarGrpImportTask> selectSelective(TarGrpImportTask paramTarGrpImportTask);
  
  List<TarGrpImportTask> selectSelectiveLimit(TarGrpImportTask paramTarGrpImportTask);
}

 