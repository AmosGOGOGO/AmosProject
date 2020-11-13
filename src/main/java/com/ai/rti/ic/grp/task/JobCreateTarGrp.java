 package  com.ai.rti.ic.grp.task;
 
 import com.ai.rti.ic.grp.entity.CiCustomGroupInfo;
 import com.ai.rti.ic.grp.entity.CiCustomListInfo;
 import com.ai.rti.ic.grp.entity.TarGrpImportTask;
 import com.ai.rti.ic.grp.service.ICiCustomListInfoService;
 import com.ai.rti.ic.grp.service.ILocalDataMarketService;
 import com.ai.rti.ic.grp.service.ITarGrpImportTaskService;
 import com.ai.rti.ic.grp.utils.Config;
 import com.ai.rti.ic.grp.utils.HttpClientUtil;
 import com.ai.rti.ic.grp.utils.SpringContextUtil;
 import com.alibaba.fastjson.JSONObject;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 public class JobCreateTarGrp  implements Runnable
 {
   private static final transient Logger logger = LoggerFactory.getLogger(com.ai.rti.ic.grp.task.JobCreateTarGrp.class);
 

   public static final String CUST_LIST_TMP_TABLE_NEW = Config.getObject("CUST_LIST_TMP_TABLE_NEW");
   public static final String PD_LIST_TMP_TABLE_NEW = Config.getObject("PD_LIST_TMP_TABLE_NEW");
 
   
   private TarGrpImportTask getTask() {
     synchronized (this) {
       ITarGrpImportTaskService tarGrpImportTaskServiceImpl = (ITarGrpImportTaskService)SpringContextUtil.getBean(ITarGrpImportTaskService.class);
       TarGrpImportTask tarGrpImportTask = tarGrpImportTaskServiceImpl.getTarGrpTask();
       if (tarGrpImportTask != null) {
    	   
    	  //2表示创建中
         tarGrpImportTask.setStatus(Integer.valueOf(2));
         tarGrpImportTaskServiceImpl.updateTarGrpImportTask(tarGrpImportTask);
       } 
       return tarGrpImportTask;
     } 
   }
 
 
   
   public void run() {
     CiCustomGroupInfo ciCustomGroupInfo;
     ITarGrpImportTaskService tarGrpImportTaskServiceImpl = (ITarGrpImportTaskService)SpringContextUtil.getBean(ITarGrpImportTaskService.class);
     ILocalDataMarketService localDataMarketService = (ILocalDataMarketService)SpringContextUtil.getBean(ILocalDataMarketService.class);
     ICiCustomListInfoService ciCustomListInfoService = (ICiCustomListInfoService)SpringContextUtil.getBean(ICiCustomListInfoService.class);
     TarGrpImportTask tarGrpImportTask = getTask();
 
     
     if (tarGrpImportTask == null || tarGrpImportTask.getTarGrpId() == null) {
       return;
     }
     CiCustomGroupInfo ci = new CiCustomGroupInfo();
     ci.setCustomGroupId(tarGrpImportTask.getTarGrpId());
     
     CiCustomListInfo ciCustomListInfo = new CiCustomListInfo();
     
     try {
       List<CiCustomGroupInfo> list = localDataMarketService.queryCiCustomGroupInfoList(ci);
       if (list == null || list.size() == 0) {
         throw new IllegalArgumentException("客户群不存在");
       }
       ciCustomGroupInfo = list.get(0);
     } catch (Exception e) {
       tarGrpImportTask.setStatus(Integer.valueOf(-1));
       tarGrpImportTaskServiceImpl.updateTarGrpImportTask(tarGrpImportTask);
       logger.error("获取客户群信息出错 生成客户群清单列表", e);
       
       return;
     } 
     try {
       if (tarGrpImportTask.getUpdateCycle().intValue() == 2) {
         SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyyMM");
         String date = simpleFormatter.format(new Date());
         tarGrpImportTask.setDataDate(date);
         ciCustomGroupInfo.setDataDate(date);
       } else {         
         SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyyMMdd");
         String date = simpleFormatter.format(new Date());
         tarGrpImportTask.setDataDate(date);
         ciCustomGroupInfo.setDataDate(date);
       } 
       ciCustomListInfo = tarGrpImportTaskServiceImpl.createCustomList(ciCustomGroupInfo);
       tarGrpImportTaskServiceImpl.updateTarGrpImportTask(tarGrpImportTask);
       logger.info("调度任务tarGrpImportTask:" + tarGrpImportTask);
       
       try {
         String sql = tarGrpImportTaskServiceImpl.createFile(tarGrpImportTask);
         ciCustomGroupInfo.setLabelOptRuleShow(sql);
       } catch (Exception e) {
         logger.error("生成客户群文件出错", e);
         throw e;
       } 
       ciCustomGroupInfo.setDataDate(tarGrpImportTask.getDataDate());
       ciCustomGroupInfo.setDataStatus(Integer.valueOf(2));
       localDataMarketService.updateSelective(ciCustomGroupInfo);
       Long count = Long.valueOf(0L);
       try {
         count = tarGrpImportTaskServiceImpl.createTarGrp(tarGrpImportTask, ciCustomListInfo);
       } catch (Exception e) {
         logger.error("创建客户群出错", e);
         throw e;
       } 
 
 
 
       
       tarGrpImportTask.setStatus(Integer.valueOf(0));
       tarGrpImportTaskServiceImpl.updateTarGrpImportTask(tarGrpImportTask);
       ciCustomGroupInfo.setCustomNum(count);
       ciCustomGroupInfo.setDataStatus(Integer.valueOf(3));
       localDataMarketService.updateSelective(ciCustomGroupInfo);
 
       
       ciCustomListInfo.setCreateTime(new Date());
       ciCustomListInfo.setCustomNum(count);
       ciCustomListInfo.setDataStatus(Integer.valueOf(3));
       ciCustomListInfoService.updateSelective(ciCustomListInfo);
       
       logger.info("生成客户群成功");
       
       Map<String, String> param = new HashMap<>(8);
       param.put("customGroupId", tarGrpImportTask.getTarGrpId());
       param.put("userId", tarGrpImportTask.getCreateStaff());
       String resp = HttpClientUtil.postMethod(Config.getObject("CUSTOM_GROUP_PUST"), param);
       logger.info("推送客户群返回信息：" + resp);
       JSONObject object = JSONObject.parseObject(resp);
       
       if (object.getInteger("code").intValue() != 1000) {
         throw new RuntimeException(object.getString("msg"));
       
       }
     }
     catch (Exception e) {
       logger.error("创建客户群出错", e);
       tarGrpImportTask.setStatus(Integer.valueOf(-1));
       tarGrpImportTaskServiceImpl.updateTarGrpImportTask(tarGrpImportTask);
       ciCustomGroupInfo.setDataStatus(Integer.valueOf(0));
       localDataMarketService.updateSelective(ciCustomGroupInfo);
       String msg = e.getMessage();
       if ((msg.getBytes()).length > 128) {
         msg = msg.substring(0, 128);
       }
       ciCustomListInfo.setExcpInfo(msg);
       ciCustomListInfo.setDataStatus(Integer.valueOf(0));
       ciCustomListInfoService.updateSelective(ciCustomListInfo);
     } 
   }
 }

