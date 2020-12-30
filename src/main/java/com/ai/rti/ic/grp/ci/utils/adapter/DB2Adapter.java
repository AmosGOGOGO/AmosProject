package com.ai.rti.ic.grp.ci.utils.adapter;

import com.ai.rti.ic.grp.constant.CommonConstants;
import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.StringUtil;

public class DB2Adapter implements IDbAdapter {
	public String getDbType() throws RuntimeException {
		return "DB2";
	}

	public String getTimeStamp(String strDateStr, String strH, String strM, String strS) throws RuntimeException {
		return "TIMESTAMP('" + strDateStr + " " + strH + ":" + strM + ":" + strS + "')";
	}

	public String getDate(String strDateStr) {
		if (null == strDateStr || strDateStr.length() < 1) {
			return null;
		}
		if (strDateStr.indexOf("0000-00-00") >= 0) {
			return "null";
		}
		int i = strDateStr.indexOf(" ");
		if (i > 0) {
			strDateStr = strDateStr.substring(0, i);
		}
		return "DATE('" + strDateStr + "')";
	}

	public String getDate2(String strDateStr) throws RuntimeException {
		if (null == strDateStr || strDateStr.length() < 1) {
			return null;
		}
		if (strDateStr.indexOf("000000") >= 0) {
			return "null";
		}
		int i = strDateStr.indexOf(" ");
		if (i > 0) {
			strDateStr = strDateStr.substring(0, i);
		}
		return "DATE('" + strDateStr + "')";
	}

	public String getDate3(String strDateStr) throws RuntimeException {
		if (null == strDateStr || strDateStr.length() < 1) {
			return null;
		}
		if (strDateStr.indexOf("000000") >= 0) {
			return "null";
		}
		int i = strDateStr.indexOf(" ");
		if (i > 0) {
			strDateStr = strDateStr.substring(0, i);
		}
		return "DATE('" + strDateStr + "')";
	}

	public String getFullDate(String strDateColName) throws RuntimeException {
		return "ts_fmt(" + strDateColName + ",'yyyy-mm-dd hh:mi:ss')";
	}

	public String getNow() throws RuntimeException {
		return "current timestamp";
	}

	public String getSqlLimit(String strSql, int limitnum) throws RuntimeException {
		return strSql + " FETCH FIRST " + limitnum + " ROWS ONLY";
	}

	public String getPagedSql(String srcSql, int currPage, int pageSize) throws RuntimeException {
		int begin = (currPage - 1) * pageSize;
		int end = begin + pageSize;

		StringBuffer rownumber = new StringBuffer(" rownumber() over(");
		int orderByIndex = srcSql.toLowerCase().indexOf("order by");

		if (orderByIndex > 0) {
			String[] tempStr = srcSql.substring(orderByIndex).split("\\.");
			for (int i = 0; i < tempStr.length - 1; i++) {
				int dotIndex = tempStr[i].lastIndexOf(",");
				if (dotIndex < 0)
					dotIndex = tempStr[i].lastIndexOf(" ");
				String result = tempStr[i].substring(0, dotIndex + 1);
				rownumber.append(result).append(" temp_.");
			}
			rownumber.append(tempStr[tempStr.length - 1]);
		}

		rownumber.append(") as row_,");

		StringBuffer sql = (new StringBuffer(srcSql.length() + 100)).append("select * from ( ").append(" select ")
				.append(rownumber.toString()).append("temp_.* from (").append(srcSql).append(" ) as temp_")
				.append(" ) as temp2_").append(" where row_  between " + begin + "+1 and " + end);
		return sql.toString();
	}

	public String getPagedSql(String sql, String column, String strPrimaryKey, int curpage, int pagesize)
			throws RuntimeException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from ( ");
		buffer.append("select ").append(column)
				.append("  rownumber() over (order by " + strPrimaryKey + ") as my_rownum from( ");
		buffer.append(sql).append(") as temp ");
		buffer.append("fetch first " + (pagesize * curpage + pagesize) + " rows only) as a ");
		buffer.append("where a.my_rownum > " + (pagesize * curpage));
		return buffer.toString();
	}

	public String getSubString(String strColName, int pos, int len) throws RuntimeException {
		String strRet = "";
		if (len == -1) {
			strRet = "substr(" + strColName + "," + pos + ")";
		} else {
			strRet = "substr(" + strColName + "," + pos + "," + len + ")";
		}
		return strRet;
	}

	public String getAddDate(String interv, String unit) throws RuntimeException {
		String strRet = "";
		unit = unit.trim().toUpperCase();
		interv = interv.trim();
		if (!interv.startsWith("-")) {
			interv = "+" + interv;
		}
		strRet = "(current timestamp " + interv + " " + unit + ")";
		return strRet;
	}

	public String getDateAddMonth(String monthNum) throws RuntimeException {
		return "char((current date + " + monthNum + " month))";
	}

	public String getIntToChar(String strColName) throws RuntimeException {
		return "char(" + strColName + ")";
	}

	public String getCharToInt(String strColName) throws RuntimeException {
		return "int(" + strColName + ")";
	}

	public String getCharToDouble(String strColName) {
		return "double(" + strColName + ")";
	}

	public String getRound(String str1, String str2) throws RuntimeException {
		return " round(" + str1 + "," + str2 + ") ";
	}

	public String getNotEqual() throws RuntimeException {
		return "!=";
	}

	public String getNvl(String str1, String str2) throws RuntimeException {
		return "value(" + str1 + "," + str2 + ")";
	}

	public String getCreateAsTableSql(String newtable, String templettable, String tableSpace) throws RuntimeException {
		return "create table " + newtable + " like " + templettable + " in " + tableSpace;
	}

	public String getCreateTableInTableSpaceSql(String tableDDLSql, String tableSpace) throws RuntimeException {
		if (tableSpace == null || tableSpace.length() < 1) {
			return tableDDLSql;
		}
		tableDDLSql = tableDDLSql + " in " + tableSpace;
		return tableDDLSql;
	}

	public String getCreateIndexInTableSpaceSql(String createIndexSql, String tableSpace) throws RuntimeException {
		return createIndexSql;
	}

	public String getCheckTableIsExistSql(String tableName) throws RuntimeException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from syscat.tables where tabname='").append(tableName.toUpperCase()).append("'");
		return sql.toString();
	}

	public String queryTree(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		StringBuffer sql = new StringBuffer();
		String order = null;

		Object start = null;
		if (startId == null)
			throw new RuntimeException("起始查询条件不能为空，请参考本方法使用文档");
		if (startId instanceof String) {
			start = idName + "='" + startId + "' ";
		} else if (startId instanceof Number) {
			start = idName + "=" + startId + " ";
		} else {
			throw new RuntimeException("参数传递错误，请参考本方法使用文档");
		}
		if (orderBy != null && orderBy.trim() != null) {
			if (orderBy.trim().equalsIgnoreCase("asc")) {
				order = "asc";
			} else if (orderBy.trim().equalsIgnoreCase("desc")) {
				order = "desc";
			} else {
				throw new RuntimeException("参数传递错误，请参考本方法使用文档");
			}
		}

		String selectRet = idName + "," + pidName + " ";
		for (String arg : args) {
			selectRet = selectRet + "," + arg + " ";
		}

		String tempRet = "";
		for (String t : selectRet.split(",")) {
			tempRet = tempRet + ",CHILD." + t.trim();
		}

		tempRet = tempRet.substring(1);

		if (orientation == 1) {

			sql.append("WITH TEMP(").append(selectRet).append(") AS ").append("(SELECT ").append(selectRet)
					.append("  FROM ").append(tableName);
			sql.append(" WHERE ").append(start).append("UNION ALL ").append("SELECT ").append(tempRet)
					.append("  FROM TEMP PARENT,  ").append(tableName);
			sql.append(" CHILD WHERE  PARENT.").append(idName).append("=CHILD.").append(pidName).append(")")
					.append("SELECT  ").append(selectRet);
			sql.append("  FROM TEMP order by ").append(idName).append(" ").append(order);
		} else if (orientation == 0) {
			sql.append("WITH TEMP(").append(selectRet).append(") AS ").append("(SELECT ").append(selectRet)
					.append("  FROM ").append(tableName);
			sql.append(" WHERE ").append(start).append("UNION ALL ").append("SELECT ").append(tempRet)
					.append("  FROM TEMP PARENT,  ").append(tableName);
			sql.append(" CHILD WHERE  CHILD.").append(idName).append("=PARENT.").append(pidName).append(")")
					.append("SELECT  ").append(selectRet);
			sql.append("  FROM TEMP order by ").append(idName).append(" ").append(order);
		}

		return sql.toString();
	}

	public String getTreeSql(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		StringBuffer sql = new StringBuffer();
		String order = null;

		Object start = null;
		if (startId == null)
			throw new RuntimeException("起始查询条件不能为空，请参考本方法使用文档");
		if (startId instanceof String) {
			start = idName + "='" + startId + "' ";
		} else if (startId instanceof Number) {
			start = idName + "=" + startId + " ";
		} else {
			throw new RuntimeException("参数传递错误，请参考本方法使用文档");
		}
		if (orderBy != null && orderBy.trim() != null) {
			if (orderBy.trim().equalsIgnoreCase("asc")) {
				order = "asc";
			} else if (orderBy.trim().equalsIgnoreCase("desc")) {
				order = "desc";
			} else {
				throw new RuntimeException("参数传递错误，请参考本方法使用文档");
			}
		}

		String selectRet = idName + "," + pidName + " ";
		for (String arg : args) {
			selectRet = selectRet + "," + arg + " ";
		}

		String tempRet = "";
		for (String t : selectRet.split(",")) {
			tempRet = tempRet + ",CHILD." + t.trim();
		}

		tempRet = tempRet.substring(1);

		if (orientation == 1) {
			sql.append("WITH TEMP(").append(selectRet).append(") AS ").append("(SELECT ").append(selectRet)
					.append("  FROM ").append(tableName);
			sql.append(" WHERE ").append(start).append("UNION ALL ").append("SELECT ").append(tempRet)
					.append("  FROM TEMP PARENT,  ").append(tableName);
			sql.append(" CHILD WHERE  PARENT.").append(idName).append("=CHILD.").append(pidName).append(")")
					.append("SELECT  ").append(selectRet);
			sql.append("  FROM TEMP order by ").append(idName).append(" ").append(order);
		} else if (orientation == 0) {
			sql.append("WITH TEMP(").append(selectRet).append(") AS ").append("(SELECT ").append(selectRet)
					.append("  FROM ").append(tableName);
			sql.append(" WHERE ").append(start).append("UNION ALL ").append("SELECT ").append(tempRet)
					.append("  FROM TEMP PARENT,  ").append(tableName);
			sql.append(" CHILD WHERE  CHILD.").append(idName).append("=PARENT.").append(pidName).append(")")
					.append("SELECT  ").append(selectRet);
			sql.append("  FROM TEMP order by ").append(idName).append(" ").append(order);
		}

		return sql.toString();
	}

	public String getStringLen(String strColName) {
		return "length(" + strColName + ")";
	}

	public String getPosString(String strColName, String charName) throws RuntimeException {
		return "locate('" + charName + "'," + strColName + ")";
	}

	public String getSubString(String strColName, String pos, String len) throws RuntimeException {
		return "substr(" + strColName + "," + pos + "," + len + ")";
	}

	public String getSqlCreateAsTable(String newTab, String tmpTable, String priKey) throws Exception {
		StringBuilder strRet = new StringBuilder();
		String tabSpace = Config.getObject("CI_TABLESPACE");
		String indexSpace = Config.getObject("CI_INDEX_TABLESPACE");
		String schema = Config.getObject("CI_SCHEMA");

		strRet.append("create table ");
		if (StringUtil.isNotEmpty(schema)) {
			strRet.append(schema).append(".");
		}
		strRet.append(newTab).append(" like ").append(tmpTable);
		if (StringUtil.isNotEmpty(tabSpace)) {
			strRet.append(" NOT LOGGED INITIALLY COMPRESS YES DISTRIBUTE BY HASH (").append(priKey).append(")")
					.append(" IN ").append(tabSpace);
			if (StringUtil.isNotEmpty(indexSpace)) {
				strRet.append(" INDEX IN ").append(indexSpace);
			}
		}

		return strRet.toString();
	}

	public String getTrim(String column) {
		return "rtrim(ltrim(" + column + "))";
	}

	public String getSequenceSql(String sequenceName) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select nextval for ").append(sequenceName).append(" from sysibm.sysdummy1");
		return sqlBuilder.toString();
	}

	public String getLimtCountSql(String srcSql, int currPage, int pageSize, String attrSqlStr) {
		int begin = (currPage - 1) * pageSize;
		int end = begin + pageSize;
		String strRet = srcSql;
		StringBuffer rownumber = new StringBuffer(" rownumber() over(");
		int orderByIndex = srcSql.toLowerCase().indexOf("order by");

		if (orderByIndex > 0) {
			String[] tempStr = srcSql.substring(orderByIndex).split("\\.");
			for (int i = 0; i < tempStr.length - 1; i++) {
				int dotIndex = tempStr[i].lastIndexOf(",");
				if (dotIndex < 0)
					dotIndex = tempStr[i].lastIndexOf(" ");
				String result = tempStr[i].substring(0, dotIndex + 1);
				rownumber.append(result).append(" temp_.");
			}
			rownumber.append(tempStr[tempStr.length - 1]);
		}

		rownumber.append(") as row_,");

		StringBuffer pagingSelect = (new StringBuffer(srcSql.length() + 100))
				.append(" select " + attrSqlStr + " from ( ").append(" select ").append(rownumber.toString())
				.append("temp_.* from (").append(srcSql).append(" ) as temp_").append(" ) as temp2_")
				.append(" where row_  between " + begin + "+1 and " + end);
		strRet = pagingSelect.toString();
		return strRet;
	}

	public String getOverFunPostfix(String column) {
		StringBuffer result = new StringBuffer();
		result.append(" ");
		return result.toString();
	}

	public String getSqlAddColumn(String tableName, String column, String type) throws Exception {
		String schema = Config.getObject("CI_SCHEMA");
		if (StringUtil.isNotEmpty(schema)) {
			tableName = schema + "." + tableName;
		}
		StringBuffer sql = new StringBuffer("");
		sql.append(" ALTER TABLE ").append(tableName).append(" ADD COLUMN ").append(column).append(" ").append(type);
		return sql.toString();
	}

	public String getColumnType(String columnType) {
		if (StringUtil.isEmpty(columnType)) {
			columnType = "VARCHAR(512)";
		}
		return columnType;
	}

	public String getInsertIntoSql(String selectSql, String intoTableName, String columns) {
		StringBuffer insertSql = new StringBuffer();
		String schema = Config.getObject("CI_SCHEMA");
		if (StringUtil.isNotEmpty(schema)) {
			intoTableName = schema + "." + intoTableName;
		}
		insertSql.append("insert ").append(CommonConstants.sqlAppend).append(" into ").append(intoTableName)
				.append(" ( ").append(columns).append(") ").append(selectSql).toString();
		return insertSql.toString();
	}

	public String getRenameTableSql(String tableName, String toTableName) {
		StringBuffer renameSql = new StringBuffer();
		renameSql.append("RENAME TABLE ").append(tableName).append(" TO ").append(toTableName);
		return renameSql.toString();
	}

	public String getConnectorSql(String... args) {
		String result = "";
		for (String arg : args) {
			result = result + arg + "||";
		}
		result = result.substring(0, result.length() - 2);
		return result;
	}

	public String getColumnToChar(String strColName) throws RuntimeException {
		String strRet = "";
		strRet = "char(" + strColName + ")";
		return strRet;
	}

	public String getChar2Timestamp(String colName, String format) {
		String strRet = "";
		if ("yyyy-MM-dd".equals(format)) {
			strRet = "timestamp(" + colName + "||' 00:00:00')";
		} else if ("yyyy-MM".equals(format)) {
			strRet = "timestamp(" + colName + "||'-01 00:00:00')";
		} else if ("yyyy-MM-dd HH:mm".equals(format)) {
			strRet = "timestamp(" + colName + "||':00')";
		} else if ("yyyy-MM-dd HH:mm:ss".equals(format)) {
			strRet = "timestamp(" + colName + ")";
		} else {
			throw new RuntimeException("不支持的时间格式转换");
		}
		return strRet;
	}

	public String getTimestamp2Char(String colName, String format) {
		String strRet = "";
		if ("yyyy-MM-dd".equals(format)) {
			strRet = "to_char(" + colName + ",'YYYY-MM-DD')";
		} else if ("yyyyMMdd".equals(format)) {
			strRet = "to_char(" + colName + ",'YYYYMMDD')";
		} else if ("yyyy-MM".equals(format)) {
			strRet = "to_char(" + colName + ",'YYYY-MM')";
		} else if ("yyyyMM".equals(format)) {
			strRet = "to_char(" + colName + ",'YYYYMM')";
		} else if ("yyyy".equals(format)) {
			strRet = "to_char(" + colName + ",'YYYY')";
		} else if ("yyyy-MM-dd HH:mm".equals(format)) {
			strRet = "to_char(" + colName + ",'YYYY-MM-DD HH24:MI')";
		} else if ("yyyy-MM-dd HH:mm:ss".equals(format)) {
			strRet = "to_char(" + colName + ",'YYYY-MM-DD HH24:MI:SS')";
		} else {
			throw new RuntimeException("不支持的时间格式转换");
		}
		return strRet;
	}

	public String getDwLableFromTable(int cycle, String date) {
		String DW_LABEL_FORM_TABLE = Config.getObject("DW_LABEL_FORM_TABLE");

		return DW_LABEL_FORM_TABLE + date;
	}
}
