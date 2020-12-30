package com.ai.rti.ic.grp.ci.utils.adapter;

import com.ai.rti.ic.grp.utils.Config;

public class PostgreAdapter implements IDbAdapter {
	public String getDbType() throws RuntimeException {
		return "POSTGRESQL";
	}

	public String getTimeStamp(String strDateStr, String strH, String strM, String strS) throws RuntimeException {
		if (null == strDateStr || strDateStr.length() < 1) {
			return null;
		}
		if (strDateStr.indexOf("0000-00-00") >= 0) {
			return "null";
		}
		String strRet = "";
		strRet = "to_date('" + strDateStr + " " + strH + ":" + strM + ":" + strS + "','YYYY-mm-dd hh24:mi:ss')";
		return strRet;
	}

	public String getDate(String strDateStr) {
		if (null == strDateStr || strDateStr.length() < 1) {
			return null;
		}
		if (strDateStr.indexOf("0000-00-00") >= 0) {
			return "null";
		}
		String strRet = "";
		int i = strDateStr.indexOf(" ");
		if (i > 0) {
			strDateStr = strDateStr.substring(0, i);
		}
		strRet = "to_date('" + strDateStr + "','YYYY-mm-dd')";
		return strRet;
	}

	public String getDate2(String strDateStr) throws RuntimeException {
		if (null == strDateStr || strDateStr.length() < 1) {
			return null;
		}
		if (strDateStr.indexOf("000000") >= 0) {
			return "null";
		}
		String strRet = "";
		int i = strDateStr.indexOf(" ");
		if (i > 0) {
			strDateStr = strDateStr.substring(0, i);
		}
		strRet = "to_date('" + strDateStr + "','YYYY/mm/dd')";
		return strRet;
	}

	public String getDate3(String strDateStr) throws RuntimeException {
		if (null == strDateStr || strDateStr.length() < 1) {
			return null;
		}
		if (strDateStr.indexOf("000000") >= 0) {
			return "null";
		}
		String strRet = "";
		int i = strDateStr.indexOf(" ");
		if (i > 0) {
			strDateStr = strDateStr.substring(0, i);
		}
		strRet = "to_date('" + strDateStr + "','YYYYmmdd')";
		return strRet;
	}

	public String getFullDate(String strDateColName) throws RuntimeException {
		String strRet = "";
		strRet = "to_char(" + strDateColName + ",'YYYY-mm-dd hh24:mi:ss')";
		return strRet;
	}

	public String getNow() throws RuntimeException {
		String strRet = "";
		strRet = "now()";
		return strRet;
	}

	public String getSqlLimit(String strSql, int limitnum) {
		String strRet = "";
		strRet = strSql + "fetch first " + limitnum + " rows only";
		return strRet;
	}

	public String getPagedSql(String srcSql, int currPage, int pageSize) {
		int begin = (currPage - 1) * pageSize;
		int end = begin + pageSize;
		String strRet = srcSql;

		StringBuffer rownumber = new StringBuffer(" row_number() over(");
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

		StringBuffer pagingSelect = (new StringBuffer(srcSql.length() + 100)).append("select * from ( ")
				.append(" select ").append(rownumber.toString()).append("temp_.* from (").append(srcSql)
				.append(" ) as temp_").append(" ) as temp2_").append(" where row_  between " + begin + "+1 and " + end);
		strRet = pagingSelect.toString();
		return strRet;
	}

	public String getPagedSql(String sql, String column, String strPrimaryKey, int curpage, int pagesize)
			throws RuntimeException {
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		buffer.append("select * from ( ");
		buffer.append("select ").append(column)
				.append("  row_number() over (order by " + strPrimaryKey + ") as my_rownum from( ");
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
		if (unit.compareTo("MINUTE") == 0) {
			strRet = "now()+interval '" + interv + " minute'";
		} else if (unit.compareTo("SECOND") == 0) {
			strRet = "now()+interval '" + interv + " second'";
		} else if (unit.compareTo("HOUR") == 0) {
			strRet = "now()+interval '" + interv + " hour'";
		} else if (unit.compareTo("DAY") == 0) {
			strRet = "now()+interval '" + interv + " day'";
		} else if (unit.compareTo("MONTH") == 0) {
			strRet = "now()+interval '" + interv + " month'";
		} else if (unit.compareTo("YEAR") == 0) {
			strRet = "now()+interval '" + interv + " year'";
		}

		return strRet;
	}

	public String getDateAddMonth(String monthNum) throws RuntimeException {
		String strRet = "";
		strRet = "to_char(now()+interval '" + monthNum + "' month,'YYYY-mm-dd')";
		return strRet;
	}

	public String getIntToChar(String strColName) throws RuntimeException {
		String strRet = "";
		strRet = "cast(" + strColName + " as varchar(12))";
		return strRet;
	}

	public String getCharToInt(String strColName) throws RuntimeException {
		String strRet = "";
		strRet = "cast(" + strColName + " as integer)";
		return strRet;
	}

	public String getCharToDouble(String strColName) {
		String strRet = "";
		strRet = "cast(" + strColName + " as numeric)";
		return strRet;
	}

	public String getRound(String str1, String str2) throws RuntimeException {
		return " round(" + str1 + "," + str2 + ") ";
	}

	public String getNotEqual() throws RuntimeException {
		return "!=";
	}

	public String getNvl(String str1, String str2) throws RuntimeException {
		String strRet = "";
		strRet = "COALESCE(" + str1 + "," + str2 + ")";
		return strRet;
	}

	public String getCreateAsTableSql(String newtable, String templettable, String tableSpace) throws RuntimeException {
		String ss = "";
		ss = "create table " + newtable + " tablespace " + tableSpace + " as select * from " + templettable
				+ " where 1=2";

		return ss;
	}

	public String getCreateTableInTableSpaceSql(String tableDDLSql, String tableSpace) throws RuntimeException {
		if (tableSpace == null || tableSpace.length() < 1)
			return tableDDLSql;
		tableDDLSql = tableDDLSql + " tablespace " + tableSpace;
		return tableDDLSql;
	}

	public String getCreateIndexInTableSpaceSql(String createIndexSql, String tableSpace) throws RuntimeException {
		return createIndexSql;
	}

	public String getCheckTableIsExistSql(String tableName) throws RuntimeException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from pg_tables where tablename='").append(tableName.toLowerCase()).append("'");
		return sql.toString();
	}

	public String queryTree(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		throw new RuntimeException("不支持的该数据库类型的树查询！");
	}

	public String getTreeSql(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		throw new RuntimeException("不支持的该数据库类型的树查询！");
	}

	public String getStringLen(String strColName) {
		String strRet = "";
		strRet = "length(" + strColName + ")";
		return strRet;
	}

	public String getPosString(String strColName, String charName) throws RuntimeException {
		String strRet = "";
		strRet = "position('" + charName + "' in " + strColName + ")";
		return strRet;
	}

	public String getSubString(String strColName, String pos, String len) throws RuntimeException {
		String strRet = "";
		strRet = "substr(" + strColName + "," + pos + "," + len + ")";
		return strRet;
	}

	public String getSqlCreateAsTable(String newTab, String tmpTable, String priKey) throws Exception {
		StringBuilder strRet = new StringBuilder();
		strRet.append("create table ").append(newTab).append(" as select * from ").append(tmpTable)
				.append(" where 1=2 ");
		return strRet.toString();
	}

	public String getTrim(String column) {
		return "trim(" + column + ")";
	}

	public String getSequenceSql(String sequenceName) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select nextval(").append(sequenceName).append(")");
		return sqlBuilder.toString();
	}

	public String getLimtCountSql(String srcSql, int currPage, int pageSize, String attrSqlStr) {
		int begin = (currPage - 1) * pageSize;
		int end = begin + pageSize;
		String strRet = srcSql;
		StringBuffer rownumber = new StringBuffer(" row_number() over(");
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
		return null;
	}

	public String getSqlAddColumn(String tableName, String column, String type) throws Exception {
		return null;
	}

	public String getColumnType(String columnType) {
		return null;
	}

	public String getInsertIntoSql(String selectSql, String intoTableName, String columns) {
		return null;
	}

	public String getRenameTableSql(String tableName, String toTableName) {
		return null;
	}

	public String getConnectorSql(String... args) {
		return null;
	}

	public String getColumnToChar(String strColName) throws RuntimeException {
		String strRet = "";
		strRet = strColName;
		return strRet;
	}

	public String getChar2Timestamp(String colName, String format) {
		return null;
	}

	public String getTimestamp2Char(String colName, String format) {
		return null;
	}

	public String getDwLableFromTable(int cycle, String date) {
		String DW_LABEL_FORM_TABLE = Config.getObject("DW_LABEL_FORM_TABLE");

		return DW_LABEL_FORM_TABLE + date;
	}
}
