 package  com.ai.rti.ic.grp.quart;
 
import com.ai.rti.ic.grp.task.DayCycleTarGrpCreate;
import com.ai.rti.ic.grp.task.JobCreateTarGrp;
import com.ai.rti.ic.grp.task.MonthCycleTarGrpCreate;
import com.ai.rti.ic.grp.utils.SpringContextUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
 
 
 @Component
 public class Quartz
 {
   @Scheduled(cron = "0 0/1 * * * ?")
   public void jobCreateTarGrp() {
     JobCreateTarGrp jobCreateTarGrp = new JobCreateTarGrp();
     Thread thread = new Thread((Runnable)jobCreateTarGrp);
     thread.start();
   }
   
   @Scheduled(cron = "0 0/5 * * * ?")
   public void dayCycleTarGrpCreate() {
     ((DayCycleTarGrpCreate)SpringContextUtil.getBean(DayCycleTarGrpCreate.class)).execute();
   }
 
   
   @Scheduled(cron = "0 0/10 * * * ?")
   public void monthCycleTarGrpCreate() {
     ((MonthCycleTarGrpCreate)SpringContextUtil.getBean(MonthCycleTarGrpCreate.class)).execute();
   }
 }
