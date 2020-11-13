 package  com.ai.rti.ic.grp.service.impl;
 
 import com.ai.rti.ic.grp.dao.ICiCustomGroupSubGroupRuleDao;
 import com.ai.rti.ic.grp.dao.ICiCustomIncreMaintainDao;
 import com.ai.rti.ic.grp.dao.ICiCustomSubgroupListExeInfoDao;
 import com.ai.rti.ic.grp.dao.ICiCustomSubgroupListInfoDao;
 import com.ai.rti.ic.grp.dao.ICustomGroupDao;
 import com.ai.rti.ic.grp.dao.base.BackDataSourceFactory;
 import com.ai.rti.ic.grp.dao.base.DataSourceFactory;
 import com.ai.rti.ic.grp.entity.CiCustomGroupInfo;
 import com.ai.rti.ic.grp.entity.CiCustomGroupSubGroupRule;
 import com.ai.rti.ic.grp.entity.CiCustomSubgroupInfo;
 import com.ai.rti.ic.grp.entity.CiCustomSubgroupListExeInfo;
 import com.ai.rti.ic.grp.service.ICiCustomIncreMaintainService;
 import com.ai.rti.ic.grp.utils.Config;
 import com.ai.rti.ic.grp.utils.GenerateUUID;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service("CiCustomIncreMaintainServiceImpl")
 public class CiCustomIncreMaintainServiceImpl
   implements ICiCustomIncreMaintainService
 {
   @Autowired
   private ICiCustomGroupSubGroupRuleDao ciCustomGroupSubGroupRuleDao;
   @Autowired
   private ICiCustomIncreMaintainDao ciCustomIncreMaintainDao;
   private static final transient Logger log = LoggerFactory.getLogger(com.ai.rti.ic.grp.service.impl.CiCustomIncreMaintainServiceImpl.class);
 
   
   @Autowired
   private ICustomGroupDao customGroupDao;
 
   
   public boolean subCustomGroupMaintain(String groupId, String customTableName, String dataDate, Integer isIncrement, String activityId) {
     boolean flag = true;
     CiCustomGroupSubGroupRule ciCustomGroupSubGroupRule = new CiCustomGroupSubGroupRule();
     ciCustomGroupSubGroupRule.setGroupId(groupId);
     if (activityId != null && activityId.length() > 0) {
       ciCustomGroupSubGroupRule.setActivityId(activityId);
     }
     
     List<CiCustomGroupSubGroupRule> ciCustomGroupSubGroupRuleList = this.ciCustomGroupSubGroupRuleDao.querySubCustomGroupInfos(ciCustomGroupSubGroupRule);
     
     if (ciCustomGroupSubGroupRuleList.size() > 0) {
       log.info("客户群" + ciCustomGroupSubGroupRuleList.size() + "个分群!");
       for (CiCustomGroupSubGroupRule rule : ciCustomGroupSubGroupRuleList) {
         flag = singleSubCustomGroupMaintain(rule, customTableName, dataDate, isIncrement);
       }
     } else {
       log.info("客户群没有被分群!");
     } 
     return flag;
   }
 
   
   @Autowired
   private ICiCustomSubgroupListExeInfoDao ciCustomSubgroupListExeInfoDao;
   
   @Autowired
   private ICiCustomSubgroupListInfoDao ciCustomSubgroupListInfoDao;
   
   public boolean singleSubCustomGroupMaintain(CiCustomGroupSubGroupRule rule, String customTableName, String dataDate, Integer isIncrement) {
     boolean flag = true;
     String subGroupId = rule.getSubGroupId();
     
     String customIncrementAttr = Config.getObject("CUSTOM_INCREMENT_ATTR");
     try {
       String increament = "0";
       String decrement = "0";
       
       if (isIncrement.intValue() == 1) {
         List<Map<String, Object>> subCustomGroupCount = subCustomGroupCount(rule, customTableName);
         if (subCustomGroupCount.size() > 0) {
           for (Map<String, Object> subGroupCnt : subCustomGroupCount) {
             if (subGroupCnt.containsKey(customIncrementAttr.toUpperCase()) && subGroupCnt
               .get(customIncrementAttr.toUpperCase()) != null && subGroupCnt
               .get(customIncrementAttr.toUpperCase()).toString().equals("1"))
             {
               increament = subGroupCnt.get("CNT").toString();
             }
             if (subGroupCnt.containsKey(customIncrementAttr.toUpperCase()) && subGroupCnt
               .get(customIncrementAttr.toUpperCase()) != null && subGroupCnt
               .get(customIncrementAttr.toUpperCase()).toString().equals("-1"))
             {
               decrement = subGroupCnt.get("CNT").toString();
             }
           } 
         }
       } 
       
       Long customNum = queryCustomNum(rule, customTableName);
       CiCustomSubgroupInfo ciCustomSubgroupInfo = new CiCustomSubgroupInfo();
       ciCustomSubgroupInfo.setSubgroupListId(subGroupId + "-" + dataDate);
       ciCustomSubgroupInfo.setDataDate(dataDate);
       ciCustomSubgroupInfo.setSubGroupId(subGroupId);
       ciCustomSubgroupInfo.setCustomNum(customNum);
       ciCustomSubgroupInfo.setIncrementNum(Long.valueOf(increament));
       ciCustomSubgroupInfo.setDecrementNum(Long.valueOf(decrement));
       ciCustomSubgroupInfo.setDataStatus(Integer.valueOf(1));
       ciCustomSubgroupInfo.setDataTime(new Date());
       this.ciCustomSubgroupListInfoDao.insertSelective(ciCustomSubgroupInfo);
       
       CiCustomGroupSubGroupRule ciCustomGroupSubGroupRule = new CiCustomGroupSubGroupRule();
       ciCustomGroupSubGroupRule.setSubGroupId(subGroupId);
       ciCustomGroupSubGroupRule.setCustomNum(customNum);
       this.ciCustomGroupSubGroupRuleDao.updateSubgroupRule(ciCustomGroupSubGroupRule);
 
       
       CiCustomSubgroupListExeInfo ciexe = new CiCustomSubgroupListExeInfo();
       ciexe.setExeInfoId(GenerateUUID.getUUID());
       ciexe.setEndTime(new Date());
       ciexe.setStartTime(new Date());
       ciexe.setSubgroupId(subGroupId);
       this.ciCustomSubgroupListExeInfoDao.insertSelective(ciexe);
     } catch (Exception e) {
       log.error("客户群分群记录出错" + e);
       flag = false;
       log.error("客户群分群记录出错" + e.toString());
       CiCustomSubgroupListExeInfo ciexe = new CiCustomSubgroupListExeInfo();
       ciexe.setExeInfoId(GenerateUUID.getUUID());
       ciexe.setEndTime(new Date());
       ciexe.setStartTime(new Date());
       ciexe.setSubgroupId(subGroupId);
       ciexe.setExcpInfo(e.toString().substring(0, (e.toString().length() > 400) ? 400 : (e.toString().length() - 1)));
       this.ciCustomSubgroupListExeInfoDao.insertSelective(ciexe);
     } 
     return flag;
   }
 
 
 
   
   public List<Map<String, Object>> subCustomGroupCount(CiCustomGroupSubGroupRule rule, String customTableName) {
     String customIncrementAttr = Config.getObject("CUSTOM_INCREMENT_ATTR");
     String backSql = "";
     String condition = rule.getSqlCondition();
     if (rule.getSqlCondition().toUpperCase().trim().contains("*PERCENT")) {
       
       CiCustomGroupInfo ci = new CiCustomGroupInfo();
       ci.setCustomGroupId(rule.getGroupId());
       List<CiCustomGroupInfo> ciList = this.customGroupDao.selectCiCustomGroupListInfo(ci);
       Long countNum = ((CiCustomGroupInfo)ciList.get(0)).getCustomNum();
 
       
       String sql = rule.getSqlCondition();
       String tmp = rule.getSqlCondition();
       int index_1 = tmp.indexOf("*");
       
       tmp = tmp.substring(index_1, tmp.length());
       int index_2 = tmp.indexOf("=");
       
       tmp = tmp.substring(index_2 + 1, tmp.length());
       char[] backs = tmp.toCharArray();
       boolean isEmpty = true;
       String value = "";
       int i = 0;
       for (char c : backs) {
         i++;
         
         if (!"".equals(String.valueOf(c).trim())) {
           isEmpty = false;
         }
         
         if (!isEmpty) {
           
           if ("".equals(String.valueOf(c).trim())) {
             break;
           }
           
           value = value + String.valueOf(c);
         } 
       } 
 
       
       String[] values = value.split("-");
       double start = 0.0D;
       double end = 0.0D;
       for (int j = 0; j < values.length; j++) {
         
         if (values[j].contains("%")) {
           
           String val = values[j].trim().substring(0, values[j].trim().length() - 1);
           if (j == 0) {
             start = Double.valueOf(val).doubleValue() / 100.0D;
           } else {
             end = Double.valueOf(val).doubleValue() / 100.0D;
           }
         
         } else if (j == 0) {
           
           start = Double.valueOf(values[j]).doubleValue();
         } else {
           end = Double.valueOf(values[j]).doubleValue();
         } 
       } 
 
       
       start = countNum.longValue() * start;
       System.out.println("end=" + end);
       end = countNum.longValue() * end;
       
       String percentStrOld = sql.substring(index_1, index_1 + index_2 + i + 1);
       System.out.println(percentStrOld);
       String percentStrNew = "(t.row > " + start + "  and  t.row < " + end + ")";
       log.info("替换前的条件" + condition);
       
       condition = condition.replace(percentStrOld, percentStrNew);
       log.info("替换后的条件" + condition);
       backSql = "select count(1) cnt," + customIncrementAttr + "  from (select \trow_number() over() as row,* from " + customTableName + ") t where  " + condition + "  group by " + customIncrementAttr;
     } else {
       backSql = "select count(1) cnt," + customIncrementAttr + " from " + customTableName + " where  " + condition + "  group by " + customIncrementAttr;
     } 
     log.info("分群计数SQL" + backSql);
     Connection connection = BackDataSourceFactory.getConnection();
     List<Map<String, Object>> subGroupCntList = DataSourceFactory.getMapList(connection, backSql, new Object[0]);
     return subGroupCntList;
   }
 
 
   
   public Long queryCustomNum(CiCustomGroupSubGroupRule rule, String customTableName) {
     String backSql = "";
     String condition = rule.getSqlCondition();
     if (rule.getSqlCondition().toUpperCase().trim().contains("*PERCENT")) {
       
       CiCustomGroupInfo ci = new CiCustomGroupInfo();
       ci.setCustomGroupId(rule.getGroupId());
       List<CiCustomGroupInfo> ciList = this.customGroupDao.selectCiCustomGroupListInfo(ci);
       Long countNum = ((CiCustomGroupInfo)ciList.get(0)).getCustomNum();
       
       String sql = rule.getSqlCondition();
       String tmp = rule.getSqlCondition();
       int index_1 = tmp.indexOf("*");
       
       tmp = tmp.substring(index_1, tmp.length());
       int index_2 = tmp.indexOf("=");
       
       tmp = tmp.substring(index_2 + 1, tmp.length());
       char[] backs = tmp.toCharArray();
       boolean isEmpty = true;
       String value = "";
       int i = 0;
       for (char c : backs) {
         i++;
         
         if (!"".equals(String.valueOf(c).trim())) {
           isEmpty = false;
         }
         
         if (!isEmpty) {
           
           if ("".equals(String.valueOf(c).trim())) {
             break;
           }
           
           value = value + String.valueOf(c);
         } 
       } 
 
       
       String[] values = value.split("-");
       double start = 0.0D;
       double end = 0.0D;
       for (int j = 0; j < values.length; j++) {
         
         if (values[j].contains("%")) {
           
           String val = values[j].trim().substring(0, values[j].trim().length() - 1);
           if (j == 0) {
             start = Double.valueOf(val).doubleValue() / 100.0D;
           } else {
             end = Double.valueOf(val).doubleValue() / 100.0D;
           }
         
         } else if (j == 0) {
           
           start = Double.valueOf(values[j]).doubleValue();
         } else {
           end = Double.valueOf(values[j]).doubleValue();
         } 
       } 
       
       start = countNum.longValue() * start;
       System.out.println("end=" + end);
       end = countNum.longValue() * end;
       
       String percentStrOld = sql.substring(index_1, index_1 + index_2 + i + 1);
       String percentStrNew = "(t.row > " + start + "  and  t.row < " + end + ")";
       log.info("替换前的条件" + condition);
       
       condition = condition.replace(percentStrOld, percentStrNew);
       log.info("替换后的条件" + condition);
       backSql = "select count(1) cnt  from (select \trow_number() over() as row,* from " + customTableName + ") t where  " + condition;
     } else {
       backSql = "select count(1) cnt from " + customTableName + " where  " + condition;
     } 
     log.info("计数SQL" + backSql);
     Connection connection = BackDataSourceFactory.getConnection();
     ResultSet rs = null;
     Long count = Long.valueOf(0L);
     PreparedStatement statement = null;
     try {
       statement = connection.prepareStatement(backSql.toString());
       rs = statement.executeQuery();
       while (rs.next()) {
         count = Long.valueOf(rs.getLong(1));
       }
     } catch (SQLException e) {
       log.error("客户群数据查询", e);
     } finally {
       DataSourceFactory.release(rs, connection, statement);
     } 
 
     
     return count;
   }
 }

