package  com.ai.rti.ic.grp.service.impl;

import com.ai.rti.ic.grp.dao.base.BackDataSourceFactory;
import com.ai.rti.ic.grp.dao.ICiGroupAttrRelNewDao;
import com.ai.rti.ic.grp.dao.ICustomGroupDao;
import com.ai.rti.ic.grp.dao.ICustomGroupJDBCDao;
import com.ai.rti.ic.grp.dao.IDataSourceDao;
import com.ai.rti.ic.grp.dao.IMarketManageSubDomainDao;
import com.ai.rti.ic.grp.dao.ITarGrpImportTaskDao;
import com.ai.rti.ic.grp.dao.base.BackDataSourceFactory;
import com.ai.rti.ic.grp.dao.base.DataSourceFactory;
import com.ai.rti.ic.grp.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.entity.CiCustomListInfo;
import com.ai.rti.ic.grp.entity.CiGroupAttrRel;
import com.ai.rti.ic.grp.entity.CiGroupAttrRelId;
import com.ai.rti.ic.grp.entity.CiGroupAttrRelNew;
import com.ai.rti.ic.grp.entity.CiLabelRule;
import com.ai.rti.ic.grp.entity.DataSource;
import com.ai.rti.ic.grp.entity.MarketManageSubDomain;
import com.ai.rti.ic.grp.entity.TarGrpImportTask;
import com.ai.rti.ic.grp.service.ICiCustomFileRelService;
import com.ai.rti.ic.grp.service.ICiCustomIncreMaintainService;
import com.ai.rti.ic.grp.service.ICiCustomListInfoService;
import com.ai.rti.ic.grp.service.ILocalDataMarketService;
import com.ai.rti.ic.grp.service.ITarGrpImportTaskService;
import com.ai.rti.ic.grp.task.JobCreateTarGrp;
import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.GenerateUUID;
import com.ai.rti.ic.grp.utils.HDFSUtil;
import com.ai.rti.ic.grp.utils.HttpClientUtil;
import com.ai.rti.ic.grp.utils.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
 
 @Service("tarGrpImportTaskServiceImpl")
 public class TarGrpImportTaskServiceImpl implements ITarGrpImportTaskService {
   private static final transient Logger logger = LoggerFactory.getLogger(com.ai.rti.ic.grp.service.impl.TarGrpImportTaskServiceImpl.class);
   
   @Autowired
   private ITarGrpImportTaskDao tarGrpImportTaskDao;
   
   @Autowired
   private ILocalDataMarketService localDataMarketService;
   
   @Autowired
   private IDataSourceDao dataSourceDao;
   @Autowired
   private ICustomGroupDao customGroupDao;
   @Autowired
   private ICiCustomListInfoService ciCustomListInfoService;

   @Autowired
   private ICiGroupAttrRelNewDao ciGroupAttrRelNewDao;
   @Autowired
   private IMarketManageSubDomainDao marketManageSubDomainDao;
   
   public TarGrpImportTask getTarGrpTask() {
     return this.tarGrpImportTaskDao.getTarGrpTask();
   }
 
 
   
   private CiCustomListInfo newCiCustomListInfo(CiCustomGroupInfo ciCustomGroupInfo, CiLabelRule ciLabelRule, List<CiGroupAttrRel> ciGroupAttrRelList, String dataDate, String monthLabelDate, String dayLabelDate) {
     CiCustomListInfo ciCustomListInfo = new CiCustomListInfo();
     String tabName = (ciCustomGroupInfo.getCustomGroupBelong().intValue() == 0) ? "CI_PUSER_YYMMDDHHMISSTTTTTT" : "CI_CUSER_YYMMDDHHMISSTTTTTT";
     try {
       Thread.sleep(1000L);
     } catch (InterruptedException e) {
       logger.error("生成CiCustomListInfo对象出错");
       e.printStackTrace();
     } 
     tabName = String.valueOf(tabName.replace("YYMMDDHHMISSTTTTTT",  "")) + convertLongMillsToYYYYMMDDHHMMSS(-1L);
     ciCustomListInfo.setListTableName(tabName);
     ciCustomListInfo.setDataDate(dataDate);
     ciCustomListInfo.setDataTime(new Date());
     ciCustomListInfo.setDataStatus(Integer.valueOf(1));
     ciCustomListInfo.setDayLabelDate(dayLabelDate);
     ciCustomListInfo.setListMaxNum(ciCustomGroupInfo.getListMaxNum());
     
     return ciCustomListInfo;
   }
 
   
   public CiCustomListInfo createCustomList(CiCustomGroupInfo ciCustomGroupInfo) {
     List<CiCustomListInfo> ciCustomListInfoList = new ArrayList<>();
     CiCustomListInfo ciCustomListInfo = newCiCustomListInfo(
         ciCustomGroupInfo, new CiLabelRule(), new ArrayList<>(), 
         ciCustomGroupInfo.getDataDate(), 
         ciCustomGroupInfo.getMonthLabelDate(), 
         ciCustomGroupInfo.getDayLabelDate());
     ciCustomListInfoList.add(ciCustomListInfo);
     
     if (ciCustomListInfoList.size() > 0) {
       for (CiCustomListInfo ciCustomList : ciCustomListInfoList) {
         ciCustomList.setCustomGroupId(ciCustomGroupInfo.getCustomGroupId());
         this.ciCustomListInfoService.insertSelective(ciCustomList);
       } 
     }
     
     return ciCustomListInfo;
   }
   
   private static synchronized String convertLongMillsToYYYYMMDDHHMMSS(long longMills) {
     Calendar caldTmp = Calendar.getInstance();
     if (longMills > 0L) {
       caldTmp.setTimeInMillis(longMills);
     } else {
       caldTmp.setTimeInMillis(System.currentTimeMillis());
       try {
         Thread.sleep(1L);
       } catch (InterruptedException e) {
         logger.error("convertLongMillsToYYYYMMDDHHMMSS sleep error");
       } 
     } 
     StringBuilder res = (new StringBuilder()).append(caldTmp.get(1));
     String tmpStr = String.valueOf(caldTmp.get(2) + 1);
     tmpStr = (tmpStr.length() < 2) ? ("0" + tmpStr) : tmpStr;
     res.append(tmpStr);
     tmpStr = String.valueOf(caldTmp.get(5));
     tmpStr = (tmpStr.length() < 2) ? ("0" + tmpStr) : tmpStr;
     res.append(tmpStr);
     res.append(caldTmp.get(11));
     tmpStr = String.valueOf(caldTmp.get(12));
     tmpStr = (tmpStr.length() < 2) ? ("0" + tmpStr) : tmpStr;
     res.append(tmpStr);
     tmpStr = String.valueOf(caldTmp.get(13));
     tmpStr = (tmpStr.length() < 2) ? ("0" + tmpStr) : tmpStr;
     res.append(tmpStr);
     tmpStr = String.valueOf(caldTmp.get(14));
     res.append(tmpStr);
     return res.toString();
   }
 
   
   private List<CiGroupAttrRel> createCiGroupAttrRelList(TarGrpImportTask tarGrpImportTask) {
     List<CiGroupAttrRel> ciGroupAttrRelList = new ArrayList<>();
     
     String extendCol = tarGrpImportTask.getTarGrpEntendCol();
     JSONArray ja = JSONArray.parseArray(extendCol);
     
     Date date = new Date();
 
     
     String dispatchTypeCode = tarGrpImportTask.getDispatchTypeCode();
     int tarGrpDataType = tarGrpImportTask.getTarGrpDataType().intValue();
     
     int colIndex = 0;
     
     for (int i = 0; i < ja.size(); i++) {
       CiGroupAttrRel ciGroupAttrRel = new CiGroupAttrRel();
       JSONObject job = ja.getJSONObject(i);
       CiGroupAttrRelId ciGroupAttrRelId = new CiGroupAttrRelId();
       
       ciGroupAttrRelId.setModifyTime(date);
       ciGroupAttrRelId.setCustomGroupId(tarGrpImportTask.getTarGrpId());
       ciGroupAttrRel.setId(ciGroupAttrRelId);
 
       String[] argsNames = Config.getObject("CPT_ATTRS_NAME").split(",");
       if (tarGrpImportTask.getIsTactic().intValue() == 1 && i == tarGrpDataType + 1) {
         byte b; int j; String[] arrayOfString; for (j = (arrayOfString = argsNames).length, b = 0; b < j; ) { String argsName = arrayOfString[b];
           CiGroupAttrRel ciGroupAttrRel1 = new CiGroupAttrRel();
           CiGroupAttrRelId ciGroupAttrRelId1 = new CiGroupAttrRelId();
           ciGroupAttrRelId1.setAttrCol(genCustomAttrColumnName(colIndex));
           colIndex++;
           ciGroupAttrRelId1.setModifyTime(date);
           ciGroupAttrRelId1.setCustomGroupId(tarGrpImportTask.getTarGrpId());
           ciGroupAttrRel1.setId(ciGroupAttrRelId1);
           ciGroupAttrRel1.setAttrColName(argsName);
           ciGroupAttrRel1.setAttrColType("string");
           ciGroupAttrRel1.setAttrSource(1);
           ciGroupAttrRel1.setIsVerticalAttr(0);
           ciGroupAttrRel1.setStatus(Integer.valueOf(0));
           ciGroupAttrRelList.add(ciGroupAttrRel1);
           b++; }
       
       } 
       ciGroupAttrRelId.setAttrCol(genCustomAttrColumnName(colIndex));
       colIndex++;
 
 
 
       
       if (StringUtil.isNotEmpty(dispatchTypeCode)) {
         MarketManageSubDomain demo = new MarketManageSubDomain();
         demo.setMasterDataCode("CAM-C-0017");
         demo.setCodeValue(dispatchTypeCode);
         List<MarketManageSubDomain> domainList = this.marketManageSubDomainDao.getMarketManageSubDomain(demo);
         if (domainList.size() != 1) {
           logger.error("配置的派单类型信息与前台内容不相符!");
           throw new RuntimeException("配置的派单类型信息与前台内容不相符!");
         } 
 
         
         if (tarGrpImportTask.getIsTactic().intValue() == 0) {
           if (i == tarGrpDataType) {
             ciGroupAttrRel.setAttrColName(((MarketManageSubDomain)domainList.get(0)).getValue());
           } else {
             ciGroupAttrRel.setAttrColName(job.getString("comment"));
           }
         
         } else if (i == tarGrpDataType + 4) {
           ciGroupAttrRel.setAttrColName(((MarketManageSubDomain)domainList.get(0)).getValue());
         } else {
           ciGroupAttrRel.setAttrColName(job.getString("comment"));
         } 
       } 

       
       if (StringUtil.isEmpty(dispatchTypeCode)) {
         ciGroupAttrRel.setAttrColName(job.getString("comment"));
       }
 
 
       
       if (tarGrpDataType == 1 && 
         i == 0) {
         ciGroupAttrRel.setAttrColName("增减量标识");
       }
       
       ciGroupAttrRel.setAttrColType("string");
       ciGroupAttrRel.setColTypeCode(job.getString("type"));
       
       if (job.getString("type").equals("2000") || job.getString("type").equals("3000")) {
         Map<String, Object> map = new HashMap<>();
         map.put("col", ciGroupAttrRelId.getAttrCol());
         String[] attrs = job.getString("attr").split(",");
         JSONArray attrArray = new JSONArray();
         Collections.addAll((Collection<? super String>)attrArray, attrs);
         map.put("val", attrArray);
         ciGroupAttrRel.setAttrRange(map);
       } 
       ciGroupAttrRel.setAttrSource(1);
       ciGroupAttrRel.setIsVerticalAttr(0);
       ciGroupAttrRel.setStatus(Integer.valueOf(0));
       ciGroupAttrRelList.add(ciGroupAttrRel);
     } 
     return ciGroupAttrRelList;
   }
 
 
   
   private static String genCustomAttrColumnName(int index) {
     StringBuilder prefix = new StringBuilder("ATTR_COL_");
     
     int prefixLength = prefix.length();    
     int length = 13;     
     int indexLength = String.valueOf(index).length();     
     int zeroNum = length - prefixLength - indexLength;
     
     for (int i = 0; i < zeroNum; i++) {
       prefix.append("0");
     }
     
     prefix.append(String.valueOf(index));     
     return prefix.toString();
   }
 
   
   public void updateTarGrpImportTask(TarGrpImportTask tarGrpImportTask) {
     this.tarGrpImportTaskDao.updateByPrimaryKeySelective(tarGrpImportTask);
   }
 
   
   private void dealCol(StringBuilder selectSql, JSONObject colObj, int i) {
     String col = colObj.getString("col");
     String table = col.split("@")[0];
     String colName = col.split("@")[1];
     selectSql.append(",").append(table).append(".").append(colName).append("  as attr").append(i);
   }
 
 
 
   
   public String createFile(TarGrpImportTask tarGrpImportTask) throws SQLException, IOException {
     DataSource dataSource = new DataSource();
     dataSource.setDataSourceId(tarGrpImportTask.getDataSourceId());
     List<DataSource> dataSources = this.dataSourceDao.getDataSourceList(dataSource);
     dataSource = dataSources.get(0);
     logger.info("获取当前数据源配置:" + dataSource.toString());
     String dataSourceType = dataSource.getDataSourceType();
     
     StringBuilder sql = new StringBuilder();
     int t = 0;
     
     StringBuilder selectSql = new StringBuilder();
     String extendCol = tarGrpImportTask.getTarGrpEntendCol();
     JSONArray cols = JSONArray.parseArray(extendCol);
     selectSql.append("select ").append(tarGrpImportTask.getTarGrpKey()).append(" as attr0 ");
     ICustomGroupJDBCDao customGroupDao = DataSourceFactory.dbAdapterInit(dataSourceType);
     for (int i = 0; i < cols.size(); i++) {
       JSONObject colObj = cols.getJSONObject(i);
       t++;
       dealCol(selectSql, colObj, t);
       if (tarGrpImportTask.getIsTactic().intValue() == 1 && ((
         tarGrpImportTask.getTarGrpDataType().intValue() == 0 && i == 0) || (tarGrpImportTask.getTarGrpDataType().intValue() == 1 && i == 1))) {
         
         int a = 0;
         while (a < 3) {
           a++;
           assert customGroupDao != null;
           selectSql.append(",").append(customGroupDao.getEmpty());
         } 
       } 
     } 
 

     String rulesJson = tarGrpImportTask.getRulesJson();
     String relation = tarGrpImportTask.getTableRelation();
     JSONArray rules = JSONArray.parseArray(rulesJson);
     JSONArray relations = JSONArray.parseArray(relation);
     String whereSql = this.localDataMarketService.createWhereSQL(rules, dataSource.getDataSourceType().toUpperCase());
     String fromSql = this.localDataMarketService.createFromSQL(rules, relations);
     sql.append(selectSql).append(fromSql).append(whereSql);
     
     logger.info("获取数据SQL信息：" + sql);
 
     
     List<List<String>> bigList = new ArrayList<>();
     ResultSet rs = null;
     PreparedStatement statement = null;
     Connection connection = null;
     
     String fileName = String.valueOf(GenerateUUID.getUUID()) + ".txt";
     String fileUrl = String.valueOf(Config.getObject("CUSTOM_FILE_DOWN_PATH")) + "/" + fileName;
     File f = new File(fileUrl);
     OutputStream out = new FileOutputStream(f);
     try {
       connection = this.localDataMarketService.getConnection(dataSource);
       statement = connection.prepareStatement(sql.toString());
       rs = statement.executeQuery();
       
       tarGrpImportTask.setFileUrl(fileUrl);
       tarGrpImportTask.setFileName(fileName);
       StringBuilder buf = new StringBuilder();
       boolean big = false;
       long count = 0L;
       while (rs.next()) {
         List<String> smallList = new ArrayList<>();
         int a = 1;
         if (tarGrpImportTask.getIsTactic().intValue() == 1) {
           a += 3;
         }
         if (big) {
           buf.append("\n");
         }
         big = true;
         boolean small = false;
         for (int j = 0; j < cols.size() + a; j++) {
           String col = rs.getString(j + 1);
           if (small) {
             buf.append("\t");
           }
           if (col == null || col.equalsIgnoreCase("NULL")) {
             buf.append(" ");
           } else {
             buf.append(col);
           } 
           small = true;
         } 
         count++;
         
         if (count > 10000L) {          
           byte[] arrayOfByte = buf.toString().getBytes();
           if (out != null) {
             out.write(arrayOfByte);
             out.flush();
             buf = new StringBuilder();
           } 
           count = 0L;
         } 
       } 
 
       
       byte[] info = buf.toString().getBytes();
       if (out != null) {
         out.write(info);
       }
       logger.info("生成文件成功!" + tarGrpImportTask.getFileUrl());
     } catch (Exception e) {
       logger.error("客户群数据查询异常!", e);
     } finally {
       DataSourceFactory.release(rs, connection, statement);
       if (out != null) {
         out.close();
       }
     } 
     return "select " + tarGrpImportTask.getTarGrpKey() + "   " + fromSql + whereSql;
   }
 
   
   private void insertAttr(String tarGrpId, Date date) {
     String[] argsCodes = Config.getObject("CPT_ATTRS").split(",");
     String[] argsNames = Config.getObject("CPT_ATTRS_NAME").split(",");
 
     
     for (int i = 0; i < argsCodes.length; i++) {
       String arg = argsCodes[i];
       String argName = argsNames[i];
       CiGroupAttrRelNew ciGroupAttrRelNew = new CiGroupAttrRelNew();
       ciGroupAttrRelNew.setAttrColName(argName);
       ciGroupAttrRelNew.setAttrColType("String");
       ciGroupAttrRelNew.setColTypeCode("1000");
       ciGroupAttrRelNew.setAttrRange(null);
       ciGroupAttrRelNew.setAttrSource(1);
       ciGroupAttrRelNew.setIsVerticalAttr(0);
       ciGroupAttrRelNew.setStatus(Integer.valueOf(0));
       ciGroupAttrRelNew.setCustomGroupId(tarGrpId);
       ciGroupAttrRelNew.setModifyTime(date);
       ciGroupAttrRelNew.setAttrCol(arg);
       this.ciGroupAttrRelNewDao.insertSelective(ciGroupAttrRelNew);
     } 
   }
 

   
   public Long createTarGrp(TarGrpImportTask tarGrpImportTask, CiCustomListInfo customListInfoList) throws Exception {
     CiCustomListInfo param = new CiCustomListInfo();
     param.setCustomGroupId(tarGrpImportTask.getTarGrpId());
     String templetListTableName = (tarGrpImportTask.getTarGrpType().intValue() == 0) ? JobCreateTarGrp.PD_LIST_TMP_TABLE_NEW : JobCreateTarGrp.CUST_LIST_TMP_TABLE_NEW;
 
     
     String listTableNameTmp = String.valueOf(customListInfoList.getListTableName()) + "_new";     
     String column = tarGrpImportTask.getTarGrpKey();     
     List<CiGroupAttrRel> ciGroupAttrRelList = createCiGroupAttrRelList(tarGrpImportTask);

     
     if (ciGroupAttrRelList != null && ciGroupAttrRelList.size() > 0) {
       Date date = new Date();       
       for (CiGroupAttrRel ciGroupAttrRel : ciGroupAttrRelList) {        
         try {
          
           CiGroupAttrRelNew ciGroupAttrRelNew = new CiGroupAttrRelNew();
           ciGroupAttrRelNew.setAttrColName(ciGroupAttrRel.getAttrColName());
           ciGroupAttrRelNew.setAttrColType(ciGroupAttrRel.getAttrColType());
           ciGroupAttrRelNew.setColTypeCode(ciGroupAttrRel.getColTypeCode());
           ciGroupAttrRelNew.setAttrRange(ciGroupAttrRel.getAttrRange());
           ciGroupAttrRelNew.setAttrSource(ciGroupAttrRel.getAttrSource());
           ciGroupAttrRelNew.setIsVerticalAttr(ciGroupAttrRel.getIsVerticalAttr());
           ciGroupAttrRelNew.setStatus(ciGroupAttrRel.getStatus());
           ciGroupAttrRelNew.setCustomGroupId(ciGroupAttrRel.getId().getCustomGroupId());
           ciGroupAttrRelNew.setModifyTime(date);
           ciGroupAttrRelNew.setAttrCol(ciGroupAttrRel.getId().getAttrCol());
           this.ciGroupAttrRelNewDao.insertSelective(ciGroupAttrRelNew);
         } catch (Exception e) {
           logger.error("保存客户群属性错误", e);
         } 
       } 
     } 
 
 
     
     StringBuilder strRet = new StringBuilder();
     strRet.append("create table ").append(listTableNameTmp).append(" like ")
       .append(templetListTableName);
     
     Connection connection = null;
     Statement stmt = null;
     
     try {
       connection = BackDataSourceFactory.getConnection();
       stmt = connection.createStatement();
       logger.info("建_new表语句：" + strRet.toString());
       stmt.executeUpdate(strRet.toString());
       assert ciGroupAttrRelList != null;
       
       for (CiGroupAttrRel aCiGroupAttrRelList : ciGroupAttrRelList){
 
         StringBuilder addColumnSql = new StringBuilder();
         addColumnSql.append("  ALTER TABLE  ").append(listTableNameTmp).append("  ADD COLUMNS  (");
         addColumnSql.append(aCiGroupAttrRelList.getId().getAttrCol());
         addColumnSql.append("      ");
         addColumnSql.append(getColumnType(aCiGroupAttrRelList.getAttrColType()));
         addColumnSql.append("   )   ");
         logger.info("添加字段" + addColumnSql.toString());
         stmt.executeUpdate(addColumnSql.toString());
       }
     
     } catch (SQLException e) {
       logger.error("建_new表失败!", e);
       throw e;
     } finally {
       DataSourceFactory.release(null, connection, stmt);
     } 
     
     StringBuffer columns = new StringBuffer(column);
     if (ciGroupAttrRelList.size() > 0) {
       columns.append(",");
       for (int i = 0; i < ciGroupAttrRelList.size(); i++) {
         columns.append(((CiGroupAttrRel)ciGroupAttrRelList.get(i)).getId().getAttrCol());
         if (i < ciGroupAttrRelList.size() - 1) {
           columns.append(",");
         }
       } 
     } 
     
     try {
       HDFSUtil.uploadFileToHDFS(tarGrpImportTask.getFileUrl(), Config.getObject("CUSTOM_FILE_HDFS_PATH"));
     } catch (Exception e) {
       logger.error("上传文件到HDFS 出错!", e);
       throw e;
     } 
     
     String sql = " LOAD DATA INPATH '" + Config.getObject("CUSTOM_FILE_HDFS_PATH") + tarGrpImportTask.getFileName() + "' INTO TABLE " + listTableNameTmp;
     try {
       connection = BackDataSourceFactory.getConnection();
       logger.info("load文件SQL:" + sql);
       stmt = connection.createStatement();
       stmt.executeUpdate(sql);
     } catch (Exception e) {
       logger.info("load文件到spark出错!");
       throw e;
     } finally {
       DataSourceFactory.release(null, connection, stmt);
     } 
     logger.info("load table:" + listTableNameTmp + " successful");
     
     String oldName = listTableNameTmp;
     listTableNameTmp = listTableNameTmp.replace("_new", "_tmp");
     Map<String, Object> baseInfo = getBaseTable(tarGrpImportTask.getTarGrpType(), tarGrpImportTask.getUpdateCycle());
     String baseTable = (baseInfo.get("baseTable") == null) ? "" : baseInfo.get("baseTable").toString();
     Map<String, Object> map = new HashMap<>();
     map.put("listTableNameTmp", listTableNameTmp);
     map.put("oldName", oldName);
     map.put("customGroupBelong", tarGrpImportTask.getTarGrpType());
     map.put("baseTable", baseTable);
     map.put("customGroupNetType", tarGrpImportTask.getTarGrpNetType());
     
     creataTmpNew(map, ciGroupAttrRelList);
     deleteAllData(oldName);
     Long resultNum = batchInsert2CustListTabNew(listTableNameTmp, columns, tarGrpImportTask.getTarGrpType());
     logger.info("客户群'" + tarGrpImportTask.getTarGrpName() + "'规模：" + resultNum);
     deleteFile(tarGrpImportTask.getFileUrl());
     
     return resultNum;
   }
 
 
 
   
   private JSONArray getCustomGroupActivity(String customGroupId) {
     String url = String.valueOf(Config.getObject("MCD_CUSTOMGROUP_OPTIONS_URL")) + "&custGroupId=" + 
       customGroupId;
     String responseBody = HttpClientUtil.getMethod(url);
     return JSONArray.parseArray(responseBody);
   }
 
 
   
   private Long batchInsert2CustListTabNew(String listTableNameTmp, StringBuffer columns, Integer tarGrpType) throws SQLException {
     String sql, listTableName = listTableNameTmp.replaceAll("_tmp", "");
     String hasRowNum = "true";
 
     
     /**
      * CUSTOM_GROUP_BELONG=0  用户群，用pd_inst_id
	  *	CUSTOM_GROUP_BELONG=1  客户群，用cust_id
      */
     String overOrderBy="order by pd_inst_id";
     if( 1==tarGrpType ) {
    	 overOrderBy="order by cust_id";
     }else if(0 ==tarGrpType){
    	 overOrderBy="order by pd_inst_id";
     }
     
     if ("true".equalsIgnoreCase(hasRowNum)) {   	 
       sql = "create table " + listTableName + "  as select a.*,ROW_NUMBER() OVER("+overOrderBy+") as rownum from ( select distinct * from " + Config.getObject("CI_SCHEMA") + "." + listTableNameTmp + ") a";
     } else {
       sql = "create table " + listTableName + "  as  select distinct * from " + Config.getObject("CI_SCHEMA") + "." + listTableNameTmp;
     } 
     
     long count = 0L;
     Connection connection = null;
     Statement stmt = null;
     ResultSet resultSet = null;
     PreparedStatement pstate = null;
     try {
       connection = BackDataSourceFactory.getConnection();
       logger.info("建正式表语句：" + sql);
       stmt = connection.createStatement();
       stmt.executeUpdate(sql);
     } catch (Exception e) {
       logger.error("建正式表失败!", e);
     } finally {
       deleteAllData(listTableNameTmp);
       DataSourceFactory.release(null, connection, stmt);
     } 
     try {
       connection = BackDataSourceFactory.getConnection();
       String countSql = "select count(1) from " + listTableName;
       logger.info("获取客户群规模：" + countSql);
       pstate = connection.prepareStatement(countSql);
       resultSet = pstate.executeQuery();
       while (resultSet.next()) {
         count = resultSet.getLong(1);
       }
     } catch (SQLException e) {
       logger.error("获取客户群规模失败!", e);
       throw e;
     } finally {
       DataSourceFactory.release(resultSet, connection, pstate);
     } 
     
     return Long.valueOf(count);
   }
 
 
   
   private void deleteFile(String fileName) {
     try {
       File delFile = new File(fileName);
       if (delFile.exists()) {
         delFile.delete();
       }
     } catch (Exception e) {
       logger.error("file delete fail！", e);
     } 
   }
 
   
   private void deleteAllData(String oldName) {
     String sql = "DROP TABLE " + oldName;
     Connection connection = null;
     Statement stmt = null;
     ResultSet resultSet = null;
     logger.info("删除旧表数据：" + sql);
     try {
       connection = BackDataSourceFactory.getConnection();
       stmt = connection.createStatement();
       stmt.executeUpdate(sql);
     } catch (Exception e) {
       logger.info("删除数据出错!");
     } finally {
       DataSourceFactory.release(null, connection, stmt);
     } 
   }
 

   private Map<String, Object> getBaseTable(Integer tarGrpType, Integer updateCycle) {
     String date;
     logger.info("更新周期：" + updateCycle);
     
     Map map = this.customGroupDao.getNewDate(tarGrpType);
     
     if (2 == updateCycle.intValue()) {
       date = (String)map.get("MONTH_NEWEST_DATE");
     } else {
       date = (String)map.get("DAY_NEWEST_DATE");
     } 
     logger.info("获取基表时间：" + date);
     String base = this.customGroupDao.getBaseTable(tarGrpType);
     String baseTable = String.valueOf(base) + date;
     Map<String, Object> reu = new HashMap<>(1);
     reu.put("baseTable", baseTable);
     return reu;
   }
 

   
   private void creataTmpNew(Map<String, Object> map, List<CiGroupAttrRel> ciGroupAttrRelList) {
     String[] needcolumn;
     StringBuilder columns = new StringBuilder();
     
     String column = (((Integer)map.get("customGroupBelong")).intValue() == 0) ? "PD_INST_ID" : "CUST_ID";
     String other = "SERVICE_IC";
     
     if (((Integer)map.get("customGroupBelong")).intValue() == 0) {
       needcolumn = new String[] { "PD_INST_ID", "NUMBER_TYPE", "CUST_ID", "SERVICE_ID", "LATN_ID", "AREA_ID" };
     } else {
       needcolumn = new String[] { "CUST_ID", "LATN_ID", "AREA_ID" };
     } 
 
     
     boolean flag = false; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = needcolumn).length, b = 0; b < i; ) { String col = arrayOfString1[b];
       
       if (!column.trim().equalsIgnoreCase(col.trim())) {
         if (flag) {
           columns.append(",");
         }
         if (col.trim().equalsIgnoreCase(other.trim()) && map.get("customGroupNetType").equals(Integer.valueOf(0))) {
           columns.append("t1.").append(col.trim());
           columns = new StringBuilder(columns.toString().replace(other, String.valueOf(column) + " as " + other));
         } else {
           columns.append("t2.").append(col.trim());
         } 
         flag = true;
       } 
       
       b++; }
     
     Connection connection = null;
     Statement stmt = null;
     PreparedStatement preparedStatement = null;
     ResultSet resultSet = null;
     List<Map<String, String>> mapList = new ArrayList<>();
     try {
       connection = BackDataSourceFactory.getConnection();
       String sql = "select * from " + map.get("oldName") + " limit 1";
       preparedStatement = connection.prepareStatement(sql);
       resultSet = preparedStatement.executeQuery();
       ResultSetMetaData metaData = resultSet.getMetaData();
       for (int j = 0; j < metaData.getColumnCount(); j++) {
         String columnName = metaData.getColumnName(j + 1);
         Map<String, String> mappp = new HashMap<>(16);
         mappp.put("code", columnName);
         mapList.add(mappp);
       } 
 
       
       logger.info("_new 表字段" + mapList);
       
       StringBuilder temp = new StringBuilder();
       for (Map<String, String> val : mapList) {
         if (!column.trim().equalsIgnoreCase(val.get("code"))) {
           temp.append("t1.").append(val.get("code")).append(",");
         }
       } 
       
       if (StringUtil.isNotEmpty(temp.toString())) {
         temp = new StringBuilder(temp.substring(0, temp.length() - 1));
       }
 
       
       String need = "t1." + column;
       if (StringUtil.isNotEmpty(columns.toString())) {
         need = String.valueOf(need) + "," + columns;
       }
       if (StringUtil.isNotEmpty(temp.toString())) {
         need = String.valueOf(need) + "," + temp;
       }
       StringBuilder sql2 = new StringBuilder();
       sql2.append("  create  table   ").append(map.get("listTableNameTmp")).append("    as    ");
       sql2.append("  select  ").append(need).append("   from ").append(map.get("oldName")).append("   t1   ");
       sql2.append("   left join   ").append(map.get("baseTable")).append("    t2    ");
       sql2.append("  on  t1.").append(column).append(" = t2.").append(column).append("   ");
       logger.info("创建tmp临时表语句:" + sql2);
       stmt = connection.createStatement();
       stmt.executeUpdate(sql2.toString());
     } catch (Exception e) {
       logger.info("创建新表失败!", e);
     } finally {
       DataSourceFactory.release(null, connection, stmt);
     } 
   }
 
   
   private String getColumnType(String columnType) {
     if (StringUtil.isNotEmpty(columnType)) {
       Map<String, String> map = new HashMap<>();
       map.put("NUMBER(\\(\\d+\\))?", "INT");
       map.put("NUMBER\\(\\d+,\\d+\\)", "DECIMAL");
       map.put("VARCHAR(\\(\\d+\\))?", "STRING");
       map.put("VARCHAR2(\\(\\d+\\))?", "STRING");
       map.put("CHAR(\\(\\d+\\))?", "STRING");
       map.put("INTEGER", "INT");
       map.put("FLOAT\\(\\d+\\)", "FLOAT");
       map.put("DOUBLE", "DOUBLE");
       
       map.put("DECIMAL(\\(\\d+,\\d+\\))?", "DOUBLE");
       
       map.put("DECIMAL(\\(\\d+\\))?", "DOUBLE");
       for (Map.Entry<String, String> entry : map.entrySet()) {
         String key = entry.getKey();
         if (columnType.toUpperCase().matches(key)) {
           columnType = entry.getValue();
           break;
         } 
       } 
     } else if (StringUtil.isEmpty(columnType)) {
       columnType = "STRING";
     } 
     
     return columnType;
   }
 }