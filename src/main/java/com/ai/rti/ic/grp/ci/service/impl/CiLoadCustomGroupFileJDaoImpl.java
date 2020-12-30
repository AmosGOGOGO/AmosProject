package com.ai.rti.ic.grp.ci.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ai.rti.ic.grp.ci.service.ICiLoadCustomGroupFileJDao;
import com.ai.rti.ic.grp.dao.base.BackDataSourceFactory;
import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.StringUtil;

@Repository
public class CiLoadCustomGroupFileJDaoImpl implements ICiLoadCustomGroupFileJDao {
	private Logger log = Logger.getLogger(CiLoadCustomGroupFileJDaoImpl.class);


	public List<Map<String, Object>> getCustomGroupList(String tableName, long startLine, long endLine, String column) {
		try {
			StringBuffer sql = new StringBuffer();
			String schema =  Config.getObject("CI_SCHEMA");
			if (StringUtil.isNotEmpty(schema)) {
				tableName = schema + "." + tableName;
			}
			sql.append("select * from ").append(tableName);
			sql.append("   where rownum > ");
			sql.append(startLine);
			sql.append(" and rownum <=");
			sql.append(endLine);
			this.log.info("SQL:" + sql.toString());
			return BackDataSourceFactory.getBackSimpleJdbcTemplate().queryForList(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
			this.log.warn("table " + tableName + " is not exists!");
			return null;
		}
	}

	public List<Map<String, Object>> getCustomGroupList(String tableName, int readFlag, long startLine, long endLine) {
		try {
			String customIncrementAttr = Config.getObject("CUSTOM_INCREMENT_ATTR");
			StringBuffer sql = new StringBuffer();
			String schema = Config.getObject("CI_SCHEMA");
			if (StringUtil.isNotEmpty(schema)) {
				tableName = schema + "." + tableName;
			}
			sql.append("select * from ").append(tableName);
			sql.append("  where 1=1 ");
			if (StringUtil.isNotEmpty(customIncrementAttr)) {
				sql.append(" and ");
				sql.append(customIncrementAttr);
				sql.append("=");

				sql.append(readFlag);
			}

			sql.append("  and rownum > ");
			sql.append(startLine);
			sql.append(" and rownum <=");
			sql.append(endLine);
			this.log.info("SQL:" + sql.toString());
			return BackDataSourceFactory.getBackSimpleJdbcTemplate().queryForList(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
			this.log.warn("table " + tableName + " is not exists!");
			return null;
		}
	}

	public List<Map<String, Object>> getCustomGroupList(String tabNameA, String tabNameB, String column) {
		StringBuffer sql = new StringBuffer();
		String schema = Config.getObject("CI_SCHEMA");
		if (StringUtil.isNotEmpty(schema)) {
			tabNameA = schema + "." + tabNameA;
			tabNameB = schema + "." + tabNameB;
		}
		sql.append("select a.* from ");
		sql.append(tabNameB);
		sql.append(" b RIGHT JOIN ");
		sql.append(tabNameA);
		sql.append(" a on b.");
		sql.append(column);
		sql.append(" = a.");
		sql.append(column);
		sql.append(" where b.");
		sql.append(column);
		sql.append(" is null");
		this.log.info("SQL:" + sql.toString());
		return BackDataSourceFactory.getBackSimpleJdbcTemplate().queryForList(sql.toString());
	}
}
