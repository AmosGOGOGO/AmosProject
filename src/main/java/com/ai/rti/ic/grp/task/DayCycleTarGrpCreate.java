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
 public class DayCycleTarGrpCreate
 {
   private static final transient Logger logger = LoggerFactory.getLogger(com.ai.rti.ic.grp.task.DayCycleTarGrpCreate.class);
 
  
   public void execute() {
     changeState();
   }
 
   
   private void changeState() {
     synchronized (this) {
       ITarGrpImportTaskDao tarGrpImportTaskDao = (ITarGrpImportTaskDao)SpringContextUtil.getBean(ITarGrpImportTaskDao.class);
       TarGrpImportTask tarGrpImportTask = new TarGrpImportTask();
       
       
       tarGrpImportTask.setUpdateCycle(3);
       tarGrpImportTask.setStatus(1);
       final List<TarGrpImportTask> liststatus1 = tarGrpImportTaskDao.selectSelective(tarGrpImportTask);
       final int cnt_waitcreat = liststatus1.size();
       tarGrpImportTask.setUpdateCycle(3);
       tarGrpImportTask.setStatus(2);
       final List<TarGrpImportTask> liststatus2 = tarGrpImportTaskDao.selectSelective(tarGrpImportTask);
       final int cnt_creating = liststatus2.size();
       final int task_cnt = cnt_waitcreat + cnt_creating;
       if (task_cnt > 10) {
           return;
       }
       tarGrpImportTask = new TarGrpImportTask();

       
       
       
       tarGrpImportTask.setUpdateCycle(Integer.valueOf(3));
       List<TarGrpImportTask> list = tarGrpImportTaskDao.selectSelective(tarGrpImportTask);
       for (TarGrpImportTask task : list) {
         try {
           String dataDate = task.getDataDate();
           int dataDateInt = Integer.parseInt(dataDate);
           SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
           String date = sdf.format(new Date());
           int dateInt = Integer.parseInt(date);
           SimpleDateFormat sd = new SimpleDateFormat("HHmmss");
           String nowTime = sd.format(new Date());
           int dayInt = Integer.parseInt(nowTime);
           String createTime = task.getCreateTime();
           int createTimeInt = Integer.valueOf(createTime).intValue();
           
           if (dateInt > dataDateInt && dayInt >= createTimeInt) {
             logger.info("日客户群：" + task.toString());
             
             task.setStatus(Integer.valueOf(1));
             task.setDataDate(date);
             tarGrpImportTaskDao.updateByPrimaryKeySelective(task);
           } 
         } catch (Exception e) {
           logger.error("修改账期出错!", e);
         } 
       } 
     } 
   }
 }

