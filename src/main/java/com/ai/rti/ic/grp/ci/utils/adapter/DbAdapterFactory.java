package com.ai.rti.ic.grp.ci.utils.adapter;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class DbAdapterFactory {
	private static Logger log = Logger.getLogger(DbAdapterFactory.class);

	public static final Map<String, String> dbType = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
	        this.put(DataBaseAdapter.DBMS_ACESS, "com.ai.rti.ic.grp.ci.utils.adapter.AcessAdapter");
	        this.put(DataBaseAdapter.DBMS_DB2, "com.ai.rti.ic.grp.ci.utils.adapter.DB2Adapter");
	        this.put(DataBaseAdapter.DBMS_GBASE, "com.ai.rti.ic.grp.ci.utils.adapter.GbaseAdapter");
	        this.put(DataBaseAdapter.DBMS_ORACLE, "com.ai.rti.ic.grp.ci.utils.adapter.OracleAdapter");
	        this.put(DataBaseAdapter.DBMS_POSTGRE, "com.ai.rti.ic.grp.ci.utils.adapter.PostgreAdapter");
	        this.put(DataBaseAdapter.DBMS_SQLSERVER, "com.ai.rti.ic.grp.ci.utils.adapter.SqlServerAdapter");
	        this.put(DataBaseAdapter.DBMS_SYBASE, "com.ai.rti.ic.grp.ci.utils.adapter.SybaseAdapter");
	        this.put(DataBaseAdapter.DBMS_TERA, "com.ai.rti.ic.grp.ci.utils.adapter.TeraAdapter");
	        this.put(DataBaseAdapter.DBMS_SPARKSQL, "com.ai.rti.ic.grp.ci.utils.adapter.SparkSqlAdapter");
	        this.put(DataBaseAdapter.DBMS_MYSQL, "com.ai.rti.ic.grp.ci.utils.adapter.MySqlAdapter");
		}
	};

	public static IDbAdapter dbAdapterInit(String dbName) {
		try {
			String clsName = dbType.get(dbName);

			Class<?> cls = Class.forName(clsName);
			return (IDbAdapter) cls.newInstance();
		} catch (ClassNotFoundException e) {
			log.error("get database adapter init ClassNotFoundException：", e);
		} catch (InstantiationException e) {
			log.error("get database adapter init InstantiationException：", e);
		} catch (IllegalAccessException e) {
			log.error("get database adapter init IllegalAccessException：", e);
		}
		log.debug("get database adapter init fail,return null.");
		return null;
	}
}
