package  com.ai.rti.ic.grp.dao;

import com.ai.rti.ic.grp.entity.TarGrpImportTask;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITarGrpImportTaskDao {
  int updateByPrimaryKeySelective(TarGrpImportTask paramTarGrpImportTask);
  
  TarGrpImportTask getTarGrpTask();
  
  List<TarGrpImportTask> selectSelective(TarGrpImportTask paramTarGrpImportTask);
  
  List<TarGrpImportTask> selectSelectiveLimit(TarGrpImportTask paramTarGrpImportTask);
}

 