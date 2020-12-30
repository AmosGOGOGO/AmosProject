package com.ai.rti.ic.grp.ci.utils.adapter;

import com.ai.rti.ic.grp.utils.Config;

public class AcessAdapter implements IDbAdapter {
	public String getDbType() throws RuntimeException {
		return "ACESS";
	}

	public String getTimeStamp(String strDateStr, String strH, String strM, String strS) throws RuntimeException {
		return "'" + strDateStr + "'";
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
		return "'" + strDateStr + "'";
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
		return "'" + strDateStr + "'";
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
		return "'" + strDateStr + "'";
	}

	public String getFullDate(String strDateColName) throws RuntimeException {
		return strDateColName;
	}

	public String getNow() throws RuntimeException {
		return "date()+time()";
	}

	public String getSqlLimit(String strSql, int limitnum) {
		throw new RuntimeException("不能取得函数定义");
	}

	public String getPagedSql(String srcSql, int currPage, int pageSize) {
		throw new RuntimeException("不能取得函数定义");
	}

	public String getPagedSql(String sql, String column, String strPrimaryKey, int curpage, int pagesize)
			throws RuntimeException {
		throw new RuntimeException("不能取得函数定义");
	}

	public String getSubString(String strColName, int pos, int len) throws RuntimeException {
		throw new RuntimeException("不能取得函数定义");
	}

	public String getAddDate(String interv, String unit) throws RuntimeException {
		throw new RuntimeException("不能取得当前日期的函数定义");
	}

	public String getDateAddMonth(String monthNum) throws RuntimeException {
		throw new RuntimeException("不能取得当前日期的函数定义");
	}

	public String getIntToChar(String strColName) throws RuntimeException {
		throw new RuntimeException("不能取得函数定义");
	}

	public String getCharToInt(String strColName) throws RuntimeException {
		throw new RuntimeException("不能取得函数定义");
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
		throw new RuntimeException("不能取得函数定义");
	}

	public String getCreateAsTableSql(String newtable, String templettable, String tableSpace) throws RuntimeException {
		throw new RuntimeException("不能取得函数定义");
	}

	public String getCreateTableInTableSpaceSql(String tableDDLSql, String tableSpace) throws RuntimeException {
		return tableDDLSql;
	}

	public String getCreateIndexInTableSpaceSql(String createIndexSql, String tableSpace) throws RuntimeException {
		return createIndexSql;
	}

	public String getCheckTableIsExistSql(String tableName) throws RuntimeException {
		throw new RuntimeException("不能取得函数定义");
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
		throw new RuntimeException("不能取得函数定义");
	}

	public String getSqlCreateAsTable(String newTab, String tmpTable, String priKey) throws Exception {
		throw new RuntimeException("不能取得函数定义");
	}

	public String getTrim(String column) {
		return "trim(" + column + ")";
	}

	public String getSequenceSql(String sequenceName) throws Exception {
		throw new RuntimeException("不能取得函数定义");
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
		return null;
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
