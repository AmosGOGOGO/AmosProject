 package com.ai.rti.ic.grp.service.impl;
 
 import com.ai.rti.ic.grp.constant.RespValueCode;
 import com.ai.rti.ic.grp.dao.ICustomGroupDao;
 import com.ai.rti.ic.grp.dao.ICustomGroupJDBCDao;
 import com.ai.rti.ic.grp.dao.IDataSourceDao;
 import com.ai.rti.ic.grp.dao.IMarketManageSubDomainDao;
 import com.ai.rti.ic.grp.dao.base.DataSourceFactory;
 import com.ai.rti.ic.grp.entity.CiCustomGroupInfo;
 import com.ai.rti.ic.grp.entity.DataSource;
 import com.ai.rti.ic.grp.entity.DataSourceAuthConf;
 import com.ai.rti.ic.grp.entity.MarketManageSubDomain;
 import com.ai.rti.ic.grp.exception.ICException;
 import com.ai.rti.ic.grp.service.ILocalDataMarketService;
 import com.ai.rti.ic.grp.utils.KerberosAuthenUtil;
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.asiainfo.util.Encoder;
 import java.sql.Connection;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service("customGroupService")
 public class LocalDataMarketServiceImpl
   implements ILocalDataMarketService
 {
   @Autowired
   private IMarketManageSubDomainDao marketManageSubDomainDao;
   @Autowired
   private ICustomGroupDao customGroupDao;
   @Autowired
   private IDataSourceDao dataSourceDao;
   private static final transient Logger logger = LoggerFactory.getLogger(com.ai.rti.ic.grp.service.impl.LocalDataMarketServiceImpl.class);
 
 
 
   
   public Connection getConnection(DataSource job) {
     MarketManageSubDomain marketManageSubDomain = new MarketManageSubDomain();
     marketManageSubDomain.setMasterDataCode("CRM-A-0012");
     marketManageSubDomain.setMasterDataName(job.getDataSourceType().toUpperCase());
     List<MarketManageSubDomain> types = this.marketManageSubDomainDao.getMarketManageSubDomain(marketManageSubDomain);
     MarketManageSubDomain type = types.get(0);
     Connection connection = null;
 
     
     try {
       DataSourceAuthConf authConf = new DataSourceAuthConf();
       authConf.setDataSourceId(job.getDataSourceId());
       List<DataSourceAuthConf> authConfs = this.dataSourceDao.getDataSourceAuthConf(authConf);
       if (authConfs != null && authConfs.size() > 0) {
         DataSourceAuthConf confObj = authConfs.get(0);
         KerberosAuthenUtil.getKerberosAuth(confObj.getKeytabFile(), confObj.getHdpSecAuth(), confObj.getKerberosPrincipal());
         
         String schemaValue = String.valueOf(job.getSchemaValue()) + ";principal=" + confObj.getKerberosPrincipal();
         job.setSchemaValue(schemaValue);
       } 
       connection = DataSourceFactory.getConnection(job.getDataSourceIp(), 
           job.getDataSourcePort(), 
           job.getUserName(), 
           Encoder.decrypt(job.getPassword()), 
           job.getSchemaValue(), 
           type.getIllustrate(), 
           type.getValue());
     }
     catch (Exception e1) {
       logger.error("获取连接出错" + e1);
     } 
     logger.info("获取连接");
     return connection;
   }
 
 
 
   
   public String createFromSQL(JSONArray jsonArray, JSONArray relations) {
     StringBuilder fromAndOnSql = new StringBuilder();
     
     Set<String> tables = new HashSet<>();
     for (int i = 0; i < jsonArray.size(); i++) {
       JSONObject job = jsonArray.getJSONObject(i);
       
       if ("1".equals(job.getString("type"))) {
         String str = job.getString("id").split("@")[0];
         tables.add(str);
       } 
     } 
     Iterator<String> it = tables.iterator();
     boolean flag = true;
     String tableName = "";
     while (it.hasNext()) {
       if (flag) {
         tableName = it.next();
         fromAndOnSql.append(" from ").append(tableName);
         flag = false; continue;
       } 
       String tmp = it.next();
       String first = "";
       String other = "";
       for (int j = 0; j < relations.size(); j++) {
         if (tableName.equals(relations.getJSONObject(j).getString("tableName"))) {
           first = relations.getJSONObject(j).getString("col");
         }
         if (tmp.equals(relations.getJSONObject(j).getString("tableName"))) {
           other = relations.getJSONObject(j).getString("col");
         }
       } 
       fromAndOnSql.append("  inner join ").append(tmp).append("  on  ").append(tableName).append(".").append(first).append(" = ").append(tmp).append(".").append(other).append(" ");
     } 
     
     logger.info("表查询:" + fromAndOnSql);
     
     return fromAndOnSql.toString();
   }
 
 
   
   public String createWhereSQL(JSONArray jsonArray, String dataSourceType) {
     StringBuilder whereSql = new StringBuilder(" where 1=1 and ( ");
     for (int m = 0; m < jsonArray.size(); m++) {
       
       JSONObject job = jsonArray.getJSONObject(m);
 
 
 
       
       if ("3".equals(job.getString("type"))) {
         String val = "left".equalsIgnoreCase(job.getString("value")) ? "(" : ")";
         whereSql.append(" ").append(val).append(" ");
       } 
 
 
       
       if ("2".equals(job.getString("type"))) {
         whereSql.append(" ").append(job.getString("value")).append(" ");
       }
 
 
       
       if ("1".equals(job.getString("type"))) {
         
         JSONArray rules = job.getJSONArray("rule");
         
         boolean isFirst = true;
         if (rules.size() > 1) {
           whereSql.append(" ( ");
         }
         for (int i = 0; i < rules.size(); i++) {
           
           if (!isFirst) {
             whereSql.append(" and ");
           }
           isFirst = false;
           
           JSONObject jsob = rules.getJSONObject(i);
           String rel = jsob.getString("rel");
           String val = jsob.getString("val");
           String colName = job.getString("id").replace("@", ".");
           
           if ("1".equals(rel) && "0".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" < ").append(val).append(" ");
           } else if ("1".equals(rel) && "1".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" >= ").append(val).append(" ");
           } 
           
           if ("2".equals(rel) && "0".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" > ").append(val).append(" ");
           } else if ("2".equals(rel) && "1".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" <= ").append(val).append(" ");
           } 
           
           if ("3".equals(rel) && "0".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" <= ").append(val).append(" ");
           } else if ("3".equals(rel) && "1".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" > ").append(val).append(" ");
           } 
           
           if ("4".equals(rel) && "0".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" >= ").append(val).append(" ");
           } else if ("4".equals(rel) && "1".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" < ").append(val).append(" ");
           } 
 
 
           
           if ("5".equals(rel) && "0".equals(job.getString("reverse"))) {
             
             if ("1".equals(job.get("classType"))) {
               whereSql.append(colName).append(" = ").append(val).append(" ");
             } else {
               whereSql.append(colName).append(" = '").append(val).append("' ");
             } 
           } else if ("5".equals(rel) && "1".equals(job.getString("reverse"))) {
             if ("1".equals(job.get("classType"))) {
               whereSql.append(colName).append(" != ").append(val).append(" ");
             } else {
               whereSql.append(colName).append(" != '").append(val).append("' ");
             } 
           } 
           
           if ("6".equals(rel) && "0".equals(job.getString("reverse"))) {
             
             if ("1".equals(job.get("classType"))) {
               whereSql.append(colName).append(" like %").append(val).append("% ");
             } else {
               whereSql.append(colName).append(" like '%").append(val).append("%' ");
             } 
           } else if ("6".equals(rel) && "1".equals(job.getString("reverse"))) {
             if ("1".equals(job.get("classType"))) {
               whereSql.append(colName).append(" not like %").append(val).append("% ");
             } else {
               whereSql.append(colName).append(" not like '%").append(val).append("%' ");
             } 
           } 
 
           
           if ("7".equals(rel) || "8".equals(rel) || "9".equals(rel) || "10".equals(rel)) {
             ICustomGroupJDBCDao customGroupDao = DataSourceFactory.dbAdapterInit(dataSourceType);
             assert customGroupDao != null;
             colName = customGroupDao.getDateStr(colName);
             val = customGroupDao.getDateValStr(val);
           } 
 
           
           if ("7".equals(rel) && "0".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" < ").append(val).append(" ");
           } else if ("7".equals(rel) && "1".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" >= ").append(val).append(" ");
           } 
           
           if ("8".equals(rel) && "0".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" > ").append(val).append(" ");
           } else if ("8".equals(rel) && "1".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" <= ").append(val).append(" ");
           } 
           
           if ("9".equals(rel) && "0".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" <= ").append(val).append(" ");
           } else if ("9".equals(rel) && "1".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" > ").append(val).append(" ");
           } 
           
           if ("10".equals(rel) && "0".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" >= ").append(val).append(" ");
           } else if ("10".equals(rel) && "1".equals(job.getString("reverse"))) {
             whereSql.append(colName).append(" < ").append(val).append(" ");
           } 
 
 
           
           if ("11".equals(rel) && "0".equals(job.getString("reverse"))) {
             
             if ("1".equals(job.get("classType"))) {
               whereSql.append(colName).append(" in (").append(val).append(" ) ");
             } else {
               String[] vals = val.split(",");
               StringBuilder newVal = new StringBuilder(); byte b; int j; String[] arrayOfString1;
               for (j = (arrayOfString1 = vals).length, b = 0; b < j; ) { String value = arrayOfString1[b];
                 newVal.append(" '").append(value).append("', "); b++; }
               
               newVal = new StringBuilder(newVal.toString().trim().substring(0, newVal.toString().trim().length() - 1));
               whereSql.append(colName).append(" in ( ").append(newVal).append(" ) ");
             } 
           } else if ("11".equals(rel) && "1".equals(job.getString("reverse"))) {
             if ("1".equals(job.get("classType"))) {
               whereSql.append(colName).append(" not in (").append(val).append(") ");
             } else {
               String[] vals = val.split(",");
               StringBuilder newVal = new StringBuilder(); byte b; int j; String[] arrayOfString1;
               for (j = (arrayOfString1 = vals).length, b = 0; b < j; ) { String value = arrayOfString1[b];
                 newVal.append(" '").append(value).append("', "); b++; }
               
               newVal = new StringBuilder(newVal.toString().trim().substring(0, newVal.toString().trim().length() - 1));
               whereSql.append(colName).append(" not in ( ").append(newVal).append(" ) ");
             } 
           } 
         } 
         if (rules.size() > 1) {
           whereSql.append(" ) ");
         }
       } 
     } 
     
     whereSql.append(" ) ");
     logger.info("where条件:" + whereSql);
 
     
     return whereSql.toString();
   }
 
 
   
   public List<CiCustomGroupInfo> queryCiCustomGroupInfoList(CiCustomGroupInfo customGroupInfo) throws ICException {
     List<CiCustomGroupInfo> list;
     try {
       list = this.customGroupDao.selectCiCustomGroupListInfo(customGroupInfo);
     } catch (Exception e) {
       logger.error("查询客户群信息出错", e);
       throw new ICException(RespValueCode.ERROR_DATA_SOURCE_OPERATE.getCode(), RespValueCode.ERROR_DATA_SOURCE_OPERATE.getDesc());
     } 
     return list;
   }
 
 
   
   public void updateSelective(CiCustomGroupInfo ciCustomGroupInfo) {
     this.customGroupDao.updateSelective(ciCustomGroupInfo);
   }
 }

