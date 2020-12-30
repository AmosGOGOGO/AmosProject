package com.ai.rti.ic.grp.ci.utils.adapter;

import com.ai.rti.ic.grp.utils.Config;

public class SqlServerAdapter implements IDbAdapter {
	public String getDbType() throws RuntimeException {
		return "SQLSERVER";
	}

	public String getTimeStamp(String strDateStr, String strH, String strM, String strS) throws RuntimeException {
		if (null == strDateStr || strDateStr.length() < 1) {
			return null;
		}
		if (strDateStr.indexOf("0000-00-00") >= 0) {
			return "null";
		}
		String strRet = "";
		strRet = "CONVERT(Datetime,'" + strDateStr + " " + strH + ":" + strM + ":" + strS + "',20)";
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
		strRet = "convert(varchar(10), convert(datetime,'" + strDateStr + "'), 111)";
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
		strRet = "CONVERT(Datetime,'" + strDateStr + "',20)";
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
		strRet = "convert(varchar, convert(datetime, '" + strDateStr + "'), 112)";
		return strRet;
	}

	public String getFullDate(String strDateColName) throws RuntimeException {
		String strRet = "";
		strRet = "CONVERT(Varchar," + strDateColName + ",20)";
		strRet = "CONVERT(Varchar," + strDateColName + ",120)";
		return strRet;
	}

	public String getNow() throws RuntimeException {
		String strRet = "";
		strRet = "getdate()";
		return strRet;
	}

	public String getSqlLimit(String strSql, int limitnum) {
		String strRet = "";
		strRet = "select top " + limitnum + " * from(" + strSql + ") a";
		return strRet;
	}

	public String getPagedSql(String srcSql, int currPage, int pageSize) {
		return srcSql;
	}

	public String getPagedSql(String sql, String column, String strPrimaryKey, int curpage, int pagesize)
			throws RuntimeException {
		throw new RuntimeException("请实现SQLServer函数定义");
	}

	public String getSubString(String strColName, int pos, int len) throws RuntimeException {
		throw new RuntimeException("请实现SQLServer函数定义");
	}

	public String getAddDate(String interv, String unit) throws RuntimeException {
		throw new RuntimeException("请实现SQLServer函数定义");
	}

	public String getDateAddMonth(String monthNum) throws RuntimeException {
		throw new RuntimeException("请实现SQLServer函数定义");
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
		throw new RuntimeException("不能取得函数定义");
	}

	public String getRound(String str1, String str2) throws RuntimeException {
		return " round(" + str1 + "," + str2 + ") ";
	}

	public String getNotEqual() throws RuntimeException {
		return "!=";
	}

	public String getNvl(String str1, String str2) throws RuntimeException {
		String strRet = "";
		strRet = "isnull(" + str1 + "," + str2 + ")";
		return strRet;
	}

	public String getCreateAsTableSql(String newtable, String templettable, String tableSpace) throws RuntimeException {
		throw new RuntimeException("请实现SQLServer函数定义");
	}

	public String getCreateTableInTableSpaceSql(String tableDDLSql, String tableSpace) throws RuntimeException {
		return tableDDLSql;
	}

	public String getCreateIndexInTableSpaceSql(String createIndexSql, String tableSpace) throws RuntimeException {
		return createIndexSql;
	}

	public String getCheckTableIsExistSql(String tableName) throws RuntimeException {
		throw new RuntimeException("请实现SQLServer函数定义");
	}

	public String queryTree(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		throw new RuntimeException("请实现SQLServer的树查询！");
	}

	public String getTreeSql(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		throw new RuntimeException("请实现SQLServer的树查询！");
	}

	public String getStringLen(String strColName) {
		throw new RuntimeException("请实现SQLServer函数定义");
	}

	public String getPosString(String strColName, String charName) throws RuntimeException {
		throw new RuntimeException("请实现SQLServer函数定义");
	}

	public String getSubString(String strColName, String pos, String len) throws RuntimeException {
		throw new RuntimeException("请实现SQLServer函数定义");
	}

	public String getSqlCreateAsTable(String newTab, String tmpTable, String priKey) throws Exception {
		throw new RuntimeException("请实现SQLServer函数定义");
	}

	public String getTrim(String column) {
		return "trim(" + column + ")";
	}

	public String getSequenceSql(String sequenceName) throws Exception {
		return null;
	}

	public String getLimtCountSql(String srcSql, int currPage, int pageSize, String attrSqlStr) {
		return null;
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
		strRet = "cast(" + strColName + " as varchar(12))";
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
