package com.ai.rti.ic.grp.ci.utils.adapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.StringUtil;

public class SparkSqlAdapter implements IDbAdapter {
	public String getDbType() throws RuntimeException {
		return "SPARKSQL";
	}

	public String getTimeStamp(String strDateStr, String strH, String strM, String strS) throws RuntimeException {
		return null;
	}

	public String getDate(String strDateStr) {
		return null;
	}

	public String getDate2(String strDateStr) throws RuntimeException {
		return null;
	}

	public String getDate3(String strDateStr) throws RuntimeException {
		return null;
	}

	public String getFullDate(String strDateColName) throws RuntimeException {
		return null;
	}

	public String getNow() throws RuntimeException {
		return null;
	}

	public String getSqlLimit(String strSql, int limitnum) throws RuntimeException {
		String strRet = "";
		strRet = strSql + " limit " + limitnum;
		return strRet;
	}

	public String getPagedSql(String srcSql, int currPage, int pageSize) throws RuntimeException {
		int begin = (currPage - 1) * pageSize;
		int end = begin + pageSize;

		srcSql = srcSql.toLowerCase().replaceFirst("select", "select row_number() over() as rownum,");
		StringBuffer strRetBuf = new StringBuffer();
		strRetBuf.append("select * from (").append(srcSql).append(") a where a.rownum in (")
				.append(getRowNumEnum(begin + 1, end)).append(")");
		String strRet = strRetBuf.toString();
		return strRet;
	}

	public String getPagedSql(String sql, String column, String strPrimaryKey, int curpage, int pagesize)
			throws RuntimeException {
		return null;
	}

	public String getSubString(String strColName, int pos, int len) throws RuntimeException {
		return null;
	}

	public String getAddDate(String interv, String unit) throws RuntimeException {
		return null;
	}

	public String getDateAddMonth(String monthNum) throws RuntimeException {
		return null;
	}

	public String getIntToChar(String strColName) throws RuntimeException {
		return null;
	}

	public String getCharToInt(String strColName) throws RuntimeException {
		return null;
	}

	public String getCharToDouble(String strColName) {
		return null;
	}

	public String getRound(String str1, String str2) throws RuntimeException {
		return null;
	}

	public String getNotEqual() throws RuntimeException {
		return null;
	}

	public String getNvl(String str1, String str2) throws RuntimeException {
		return null;
	}

	public String getCreateAsTableSql(String newtable, String templettable, String tableSpace) throws RuntimeException {
		return null;
	}

	public String getCreateTableInTableSpaceSql(String tableDDLSql, String tableSpace) throws RuntimeException {
		return null;
	}

	public String getCreateIndexInTableSpaceSql(String createIndexSql, String tableSpace) throws RuntimeException {
		return null;
	}

	public String getCheckTableIsExistSql(String tableName) throws RuntimeException {
		return null;
	}

	public String queryTree(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		return null;
	}

	public String getTreeSql(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		return null;
	}

	public String getStringLen(String strColName) {
		return null;
	}

	public String getPosString(String strColName, String charName) throws RuntimeException {
		return null;
	}

	public String getSubString(String strColName, String pos, String len) throws RuntimeException {
		return null;
	}

	public String getSqlCreateAsTable(String newTab, String tmpTable, String priKey) throws Exception {
		StringBuilder strRet = new StringBuilder();
		String schema = Config.getObject("CI_SCHEMA");
		if (StringUtil.isNotEmpty(schema)) {
			newTab = schema + "." + newTab;
			tmpTable = schema + "." + tmpTable;
		}
		strRet.append("create table ").append(newTab).append(" like ").append(tmpTable);
		return strRet.toString();
	}

	public String getTrim(String column) {
		return null;
	}

	public String getSequenceSql(String sequenceName) throws Exception {
		return null;
	}

	public String getLimtCountSql(String srcSql, int currPage, int pageSize, String attrSqlStr) {
		int begin = (currPage - 1) * pageSize;
		int end = begin + pageSize;
		String strRet = srcSql;
		strRet = srcSql + " limit " + end;
		return strRet;
	}

	public String getOverFunPostfix(String column) {
		return null;
	}

	public String getSqlAddColumn(String tableName, String column, String type) throws Exception {
		String schema = Config.getObject("CI_SCHEMA");
		if (StringUtil.isNotEmpty(schema)) {
			tableName = schema + "." + tableName;
		}
		StringBuffer sql = new StringBuffer("");

		if (type.toLowerCase().contains("decimal")) {
			type = "double";
		}
		sql.append(" ALTER TABLE ").append(tableName).append(" ADD COLUMNS( ").append(column).append(" ")
				.append(getColumnType(type)).append(")");
		return sql.toString();
	}

	public String getColumnType(String columnType) {
		if (StringUtil.isNotEmpty(columnType)) {
			Map<String, String> map = new HashMap<>();
			map.put("NUMBER(\\(\\d+\\))?", "INT");
			map.put("NUMBER\\(\\d+,\\d+\\)", "DECIMAL");
			map.put("VARCHAR(\\(\\d+\\))?", "STRING");
			map.put("VARCHAR2(\\(\\d+\\))?", "STRING");
			map.put("CHAR(\\(\\d+\\))?", "STRING");
			map.put("INTEGER", "INT");
			map.put("FLOAT\\(\\d+\\)", "FLOAT");
			map.put("DOUBLE", "DOUBLE");
			map.put("DECIMAL(\\(\\d+,\\d+\\))?", "DOUBLE");
			map.put("DECIMAL(\\(\\d+\\))?", "DOUBLE");
			Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {

				Map.Entry<String, String> entry = it.next();
				String key = entry.getKey();
				if (columnType.toUpperCase().matches(key)) {
					columnType = entry.getValue();
					break;
				}
			}
		} else if (StringUtil.isEmpty(columnType)) {
			columnType = "STRING";
		}

		return columnType;
	}

	public String getInsertIntoSql(String selectSql, String intoTableName, String columns) {
		StringBuffer insertSql = new StringBuffer();
		String schema = Config.getObject("CI_SCHEMA");
		if (StringUtil.isNotEmpty(schema)) {
			intoTableName = schema + "." + intoTableName;
		}
		insertSql.append("insert into table ").append(intoTableName).append(" ").append(selectSql).toString();
		return insertSql.toString();
	}

	public String getRenameTableSql(String tableName, String toTableName) {
		StringBuffer renameSql = new StringBuffer();
		renameSql.append("ALTER TABLE ").append(tableName).append(" RENAME TO ").append(toTableName);
		return renameSql.toString();
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
		String strRet = "";
		if ("yyyy-MM-dd".equals(format)) {
			strRet = "unix_timestamp(" + colName + ",'YYYY-MM-DD')";
		} else if ("yyyyMMdd".equals(format)) {
			strRet = "unix_timestamp(" + colName + ",'YYYYMMDD')";
		} else if ("yyyy-MM".equals(format)) {
			strRet = "unix_timestamp(" + colName + ",'YYYY-MM')";
		} else if ("yyyyMM".equals(format)) {
			strRet = "unix_timestamp(" + colName + ",'YYYYMM')";
		} else if ("yyyy".equals(format)) {
			strRet = "unix_timestamp(" + colName + ",'YYYY')";
		} else if ("yyyy-MM-dd HH:mm".equals(format)) {
			strRet = "unix_timestamp(" + colName + ",'YYYY-MM-DD HH:mm')";
		} else if ("yyyy-MM-dd HH:mm:ss".equals(format)) {
			strRet = "unix_timestamp(" + colName + ",'YYYY-MM-DD HH:mm:ss')";
		} else {
			throw new RuntimeException("不支持的时间格式转换");
		}
		return strRet;
	}

	public String getTimestamp2Char(String colName, String format) {
		return null;
	}

	public String getRowNumEnum(int begin, int end) {
		StringBuffer sReturn = new StringBuffer();
		if (begin < end) {
			for (int i = begin; i <= end; i++) {
				sReturn.append(String.valueOf(i));
				if (i < end) {
					sReturn.append(",");
				}
			}

		} else if (begin >= end) {
			sReturn.append(String.valueOf(begin));
		} else {
			sReturn.append(String.valueOf(end));
		}

		return sReturn.toString();
	}

	public String getDwLableFromTable(int cycle, String date) {
		String sql = "";
		if (cycle == (new Long(1L)).intValue()) {
			String DW_LABEL_FORM_TABLE = Config.getObject("DW_LABEL_FORM_TABLE");
			sql = " (select * from  " + DW_LABEL_FORM_TABLE + " where stat_month = '" + date + "') ";
		} else {
			String DW_LABEL_FORM_TABLE = Config.getObject("DW_LABEL_FORM_TABLE_DAY");
			sql = " (select * from  " + DW_LABEL_FORM_TABLE + " where stat_date = '" + date + "') ";
		}
		return sql;
	}
}
