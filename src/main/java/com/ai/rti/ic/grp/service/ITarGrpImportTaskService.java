package com.ai.rti.ic.grp.service;

import com.ai.rti.ic.grp.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.entity.CiCustomListInfo;
import com.ai.rti.ic.grp.entity.TarGrpImportTask;
import com.ai.rti.ic.grp.exception.ICException;
import java.io.IOException;
import java.sql.SQLException;

public interface ITarGrpImportTaskService {
  TarGrpImportTask getTarGrpTask();
  
  void updateTarGrpImportTask(TarGrpImportTask paramTarGrpImportTask);
  
  String createFile(TarGrpImportTask paramTarGrpImportTask) throws ICException, SQLException, IOException;
  
  Long createTarGrp(TarGrpImportTask paramTarGrpImportTask, CiCustomListInfo paramCiCustomListInfo) throws Exception;
  
  CiCustomListInfo createCustomList(CiCustomGroupInfo paramCiCustomGroupInfo);
}
