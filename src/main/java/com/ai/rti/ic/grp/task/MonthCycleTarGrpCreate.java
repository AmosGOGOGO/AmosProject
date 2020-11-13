 package  com.ai.rti.ic.grp.task;
 
 import com.ai.rti.ic.grp.dao.ITarGrpImportTaskDao;
 import com.ai.rti.ic.grp.entity.TarGrpImportTask;
 import com.ai.rti.ic.grp.utils.SpringContextUtil;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Component;
 
 

 @Component
 public class MonthCycleTarGrpCreate
 {
   private static final transient Logger logger = LoggerFactory.getLogger(com.ai.rti.ic.grp.task.MonthCycleTarGrpCreate.class);
 
   
   public void execute() {
     ChangeState();
   }
   
   private void ChangeState() {
     synchronized (this) {
       ITarGrpImportTaskDao tarGrpImportTaskDao = (ITarGrpImportTaskDao)SpringContextUtil.getBean(ITarGrpImportTaskDao.class);
       
       TarGrpImportTask tarGrpImportTask = new TarGrpImportTask();
       tarGrpImportTask.setUpdateCycle(Integer.valueOf(2));
       List<TarGrpImportTask> list = tarGrpImportTaskDao.selectSelective(tarGrpImportTask);
       for (TarGrpImportTask task : list) {
         String dataDate = task.getDataDate();
         int dataDateInt = Integer.parseInt(dataDate);
         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
         String date = sdf.format(new Date());
         int dateInt = Integer.parseInt(date);
         
         SimpleDateFormat sd = new SimpleDateFormat("dd");
         String dd = sd.format(new Date());
         int dayInt = Integer.parseInt(dd);
         
         String createTime = task.getCreateTime();
         int createTimeInt = Integer.valueOf(createTime).intValue();
 
         
         if (dateInt > dataDateInt && dayInt >= createTimeInt) {
           logger.info("月客户群：" + task.toString());
           
           task.setStatus(Integer.valueOf(1));
           task.setDataDate(date);
           tarGrpImportTaskDao.updateByPrimaryKeySelective(task);
         } 
       } 
     } 
   }
 }

