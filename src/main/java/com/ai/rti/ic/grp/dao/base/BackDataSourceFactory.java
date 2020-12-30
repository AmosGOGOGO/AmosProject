 package  com.ai.rti.ic.grp.dao.base;
 
 import com.ai.rti.ic.grp.dao.base.BackDataSourcesProperties;
 import com.ai.rti.ic.grp.utils.SpringContextUtil;
 import com.mchange.v2.c3p0.ComboPooledDataSource;
 import java.beans.PropertyVetoException;
 import java.sql.Connection;
 import java.sql.SQLException;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
 
 
 
  
 public class BackDataSourceFactory
 {
   private static final transient Logger logger = LoggerFactory.getLogger(com.ai.rti.ic.grp.dao.base.BackDataSourceFactory.class);
   
   private static ComboPooledDataSource dataSource;
   
   private static JdbcTemplate backSimpleJdbcTemplate;
   
   static {
     try {
       BackDataSourcesProperties dataSourcesProperties = (BackDataSourcesProperties)SpringContextUtil.getBean(BackDataSourcesProperties.class);
       dataSource = new ComboPooledDataSource();
       dataSource.setUser(dataSourcesProperties.getUsername());
       dataSource.setPassword(dataSourcesProperties.getPassword());
       dataSource.setJdbcUrl(dataSourcesProperties.getUrl());
       dataSource.setDriverClass(dataSourcesProperties.getDriver());
 
       
       dataSource.setInitialPoolSize(20);
       dataSource.setMinPoolSize(10);     
       dataSource.setMaxPoolSize(50);
       dataSource.setMaxIdleTime(180);
       
       dataSource.setMaxStatements(0); 
       dataSource.setStatementCacheNumDeferredCloseThreads(1);
       dataSource.setNumHelperThreads(5);
       dataSource.setMaxAdministrativeTaskTime(60);
       
       dataSource.setIdleConnectionTestPeriod(180);
       
       backSimpleJdbcTemplate = new JdbcTemplate(dataSource);
     } catch (PropertyVetoException e) {
       logger.error("初始化连接池失败!", e);
     } 
   }
   
   public static synchronized Connection getConnection() {
     Connection conn = null;
     try {
       conn = dataSource.getConnection();
     } catch (SQLException e) {
       logger.error("获取连接失败!", e);
     } 
     return conn;
   }
   
	public static JdbcTemplate getBackSimpleJdbcTemplate() {
		return backSimpleJdbcTemplate;
	}
 }

