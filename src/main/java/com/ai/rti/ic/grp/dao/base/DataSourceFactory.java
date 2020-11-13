package com.ai.rti.ic.grp.dao.base;
import com.ai.rti.ic.grp.dao.ICustomGroupJDBCDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 public class DataSourceFactory {
   private static final transient Logger logger = LoggerFactory.getLogger(com.ai.rti.ic.grp.dao.base.DataSourceFactory.class);
   private static final String DBMS_ORACLE = "ORACLE";
   private static final String DBMS_MYSQL = "MYSQL";
   private static final String DBMS_SPARKSQL = "SPARKSQL";
   private static final Map<String, String> DB_TYPE;
   static {
     Map<String, String> map = new HashMap<>();
     map.put("MYSQL", "com.ai.rti.ic.grp.dao.impl.mysql.CustomGroupJDBCDao");
     map.put("ORACLE", "com.ai.rti.ic.grp.dao.impl.oracle.CustomGroupJDBCDao");
     map.put("SPARKSQL", "com.ai.rti.ic.grp.dao.impl.spark.CustomGroupJDBCDao");
     DB_TYPE = Collections.unmodifiableMap(map);
   }
 
   
   public static ICustomGroupJDBCDao dbAdapterInit(String dbName) {
     try {
       String clsName = DB_TYPE.get(dbName.toUpperCase());
       
       Class<?> cls = Class.forName(clsName);
       return (ICustomGroupJDBCDao)cls.newInstance();
     } catch (ClassNotFoundException e) {
       logger.error("get database adapter init ClassNotFoundException：", e);
     } catch (InstantiationException e) {
       logger.error("get database adapter init InstantiationException：", e);
     } catch (IllegalAccessException e) {
       logger.error("get database adapter init IllegalAccessException：", e);
     } 
     logger.debug("get database adapter init fail,return null.");
     return null;
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static Connection getConnection(String dataSourceIp, Integer dataSourcePort, String userName, String password, String schema, String urlTmp, String driverClass) {
     Connection conn = null;
     
     try {
       Class.forName(driverClass);
       String url = urlTmp.replace("ip", dataSourceIp).replace("port", String.valueOf(dataSourcePort)).replace("schema", 
           schema);
       logger.info("本地取数数据库连接配置信息-->URL:{},userName:{},password:{}", new Object[] { url, userName, password });
       conn = DriverManager.getConnection(url, userName, password);
     } catch (Exception e) {
       logger.error("获取连接失败:");
       logger.error(e.getMessage());
     } 
     
     return conn;
   }
 
 
 
 
 
 
 
 
 
 
 
   
   public static void release(ResultSet resultSet, Connection connection, Statement stmt) {
     if (resultSet != null) {
       try {
         resultSet.close();
       } catch (SQLException e) {
         logger.error("关闭资源失败：", e);
       } 
     }
     if (stmt != null) {
       try {
         stmt.close();
       } catch (SQLException e) {
         logger.error("关闭资源失败：", e);
       } 
     }
     if (connection != null) {
       try {
         connection.close();
       } catch (SQLException e) {
         logger.error("关闭资源失败：", e);
       } 
     }
   }
 
 
 
 
 
 
 
 
 
   
   public static List<Map<String, Object>> getMapList(Connection conn, String sql, Object... params) {
     ResultSet rs = null;
     PreparedStatement ps = null;
     List<Map<String, Object>> mapList = new ArrayList<>();
     try {
       ps = conn.prepareStatement(sql);
       if (params != null) {
         for (int i = 0; i < params.length; i++) {
           ps.setObject(i + 1, params[i]);
         }
       }
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       while (rs.next()) {
         Map<String, Object> maps = new LinkedHashMap<>();
         for (int i = 0; i < rsmd.getColumnCount(); i++) {
           String colName = rsmd.getColumnName(i + 1);
           Object colValue = rs.getObject(colName);
           if (colValue == null) {
             colValue = "";
           }
           maps.put(colName, colValue);
         } 
         mapList.add(maps);
       } 
       return mapList;
     } catch (SQLException e) {
       logger.error("查询Map集合失败：", e);
     } finally {
       release(rs, conn, ps);
     } 
     return null;
   }
 }

