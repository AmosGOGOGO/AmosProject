package com.ai.rti.ic.grp.ci.utils;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

import com.ai.rti.ic.grp.ci.exception.CIServiceException;
import com.ai.rti.ic.grp.utils.Config;

public class DataSourceFactory {
	private static Logger logger = Logger.getLogger(DataSourceFactory.class);

	public static DataSource getDataSource(String jndi) {
		if (!dss.containsKey(jndi)) {
			addDataSource(jndi);
		}
		return dss.get(jndi);
	}

	private static void addDataSource(String jndi) {
		try {
			Context ctx = new InitialContext();
			String appServerType = null;
			try {
				appServerType = Config.getObject("APP_SERVER_TYPE");
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
			Context envContext = null;
			if (appServerType != null
					&& ("weblogic".equalsIgnoreCase(appServerType) || "websphere".equalsIgnoreCase(appServerType))) {
				envContext = ctx;
			} else {
				envContext = (Context) ctx.lookup("java:comp/env");
			}
			if (envContext == null) {
				logger.error("Boom - No Context");
				throw new Exception("Boom - No Context");
			}
			if (jndi != null) {
				String strDbJndi = jndi;
				if (strDbJndi.startsWith("java:comp/env/"))
					strDbJndi = strDbJndi.substring(14);
				DataSource ds = (DataSource) envContext.lookup(strDbJndi);
				if (ds != null) {
					dss.put(jndi, ds);
				} else {
					logger.error("can't find new datasource!");
					throw new CIServiceException("can't find new datasource!");
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public void destory() {
		dss.clear();
	}

	private static Hashtable<String, DataSource> dss = new Hashtable<>();
}
