package com.ai.rti.ic.grp.ci.utils.adapter;

import com.ai.rti.ic.grp.utils.Config;

public class MySqlAdapter implements IDbAdapter {
	public String getDbType() throws RuntimeException {
		return "MYSQL";
	}

	public String getTimeStamp(String strDateStr, String strH, String strM, String strS) throws RuntimeException {
		if (null == strDateStr || strDateStr.length() < 1) {
			return null;
		}
		if (strDateStr.indexOf("0000-00-00") >= 0) {
			return "null";
		}
		String strRet = "";
		strRet = "'" + strDateStr + " " + strH + ":" + strM + ":" + strS + "'";

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
		if (i > 0)
			strDateStr = strDateStr.substring(0, i);
		strRet = "'" + strDateStr + "'";
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
		if (i > 0)
			strDateStr = strDateStr.substring(0, i);
		strRet = "'" + strDateStr + "'";

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
		if (i > 0)
			strDateStr = strDateStr.substring(0, i);
		strRet = "'" + strDateStr + "'";
		return strRet;
	}

	public String getFullDate(String strDateColName) throws RuntimeException {
		String strRet = "";
		strRet = "Date_Format(" + strDateColName + ",'%Y-%m-%d %H:%i:%s')";
		return strRet;
	}

	public String getNow() throws RuntimeException {
		String strRet = "";
		strRet = "now()";
		return strRet;
	}

	public String getSqlLimit(String strSql, int limitnum) {
		String strRet = "";
		strRet = strSql + " limit " + limitnum;
		return strRet;
	}

	public String getPagedSql(String srcSql, int currPage, int pageSize) {
		int begin = (currPage - 1) * pageSize;
		String strRet = srcSql;
		strRet = srcSql + " limit " + begin + "," + pageSize;
		return strRet;
	}

	public String getPagedSql(String sql, String column, String strPrimaryKey, int curpage, int pagesize)
			throws RuntimeException {
		return "";
	}

	public String getSubString(String strColName, int pos, int len) throws RuntimeException {
		String strRet = "";
		if (len == -1) {
			strRet = "substring(" + strColName + "," + pos + ")";
		} else {
			strRet = "substring(" + strColName + "," + pos + "," + len + ")";
		}
		return strRet;
	}

	public String getAddDate(String interv, String unit) throws RuntimeException {
		String strRet = "";
		unit = unit.trim().toUpperCase();
		interv = interv.trim();
		if (!interv.startsWith("-"))
			interv = "+" + interv;
		strRet = "ADDDATE(now(),INTERVAL " + interv + " " + unit + ")";
		return strRet;
	}

	public String getDateAddMonth(String monthNum) throws RuntimeException {
		String strRet = "";
		strRet = "DATE_ADD(curdate(),INTERVAL " + monthNum + " month)";
		return strRet;
	}

	public String getIntToChar(String strColName) throws RuntimeException {
		String strRet = "";
		strRet = strColName;
		return strRet;
	}

	public String getCharToInt(String strColName) throws RuntimeException {
		String strRet = "";
		strRet = strColName;
		return strRet;
	}

	public String getCharToDouble(String strColName) {
		String strRet = "";
		strRet = strColName;
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
		strRet = "ifnull(" + str1 + "," + str2 + ")";
		return strRet;
	}

	public String getCreateAsTableSql(String newtable, String templettable, String tableSpace) throws RuntimeException {
		String ss = "";
		return ss;
	}

	public String getCreateTableInTableSpaceSql(String tableDDLSql, String tableSpace) throws RuntimeException {
		return tableDDLSql;
	}

	public String getCreateIndexInTableSpaceSql(String createIndexSql, String tableSpace) throws RuntimeException {
		return createIndexSql;
	}

	public String getCheckTableIsExistSql(String tableName) throws RuntimeException {
		String strSql = "";
		return strSql;
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
		throw new RuntimeException("不能取得函数定义");
	}

	public String getPosString(String strColName, String charName) throws RuntimeException {
		throw new RuntimeException("不能取得函数定义");
	}

	public String getSubString(String strColName, String pos, String len) throws RuntimeException {
		String strRet = "";
		strRet = "substring(" + strColName + "," + pos + "," + len + ")";
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
		sqlBuilder.append("select nextval('").append(sequenceName).append("')");
		return sqlBuilder.toString();
	}

	public String getLimtCountSql(String srcSql, int currPage, int pageSize, String attrSqlStr) {
		int begin = (currPage - 1) * pageSize;
		String strRet = srcSql;
		strRet = srcSql + " limit " + begin + "," + pageSize;

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
		String result = "";
		result = "concat(";
		for (String arg : args) {
			result = result + arg + ",";
		}
		result = result.substring(0, result.length() - 1) + ")";
		return result;
	}

	public String getColumnToChar(String strColName) throws RuntimeException {
		String strRet = "";
		strRet = strColName;
		return strRet;
	}

	public String getChar2Timestamp(String colName, String format) {
		String strRet = "";
		if ("yyyy-MM-dd".equals(format)) {
			strRet = "to_date(" + colName + ",'%Y-%m-%d')";
		} else if ("yyyyMMdd".equals(format)) {
			strRet = "to_date(" + colName + ",'%Y%m%d')";
		} else if ("yyyy-MM".equals(format)) {
			strRet = "to_date(" + colName + ",'%Y-%m')";
		} else if ("yyyyMM".equals(format)) {
			strRet = "to_date(" + colName + ",'%Y%m')";
		} else if ("yyyy".equals(format)) {
			strRet = "to_date(" + colName + ",'%Y')";
		} else if ("yyyy-MM-dd HH:mm".equals(format)) {
			strRet = "to_date(" + colName + ",'%Y-%m-%d %H:%i')";
		} else if ("yyyy-MM-dd HH:mm:ss".equals(format)) {
			strRet = "to_date(" + colName + ",'%Y-%m-%d %H:%i:%s')";
		} else {
			throw new RuntimeException("不支持的时间格式转换");
		}
		return strRet;
	}

	public String getTimestamp2Char(String colName, String format) {
		String strRet = "";
		if ("yyyy-MM-dd".equals(format)) {
			strRet = "Date_Format(" + colName + ",'%Y-%m-%d')";
		} else if ("yyyyMMdd".equals(format)) {
			strRet = "Date_Format(" + colName + ",'%Y%m%d')";
		} else if ("yyyy-MM".equals(format)) {
			strRet = "Date_Format(" + colName + ",'%Y-%m')";
		} else if ("yyyyMM".equals(format)) {
			strRet = "Date_Format(" + colName + ",'%Y%m')";
		} else if ("yyyy".equals(format)) {
			strRet = "Date_Format(" + colName + ",'%Y')";
		} else if ("yyyy-MM-dd HH:mm".equals(format)) {
			strRet = "Date_Format(" + colName + ",'%Y-%m-%d %H:%i')";
		} else if ("yyyy-MM-dd HH:mm:ss".equals(format)) {
			strRet = "Date_Format(" + colName + ",'%Y-%m-%d %H:%i:%s')";
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
