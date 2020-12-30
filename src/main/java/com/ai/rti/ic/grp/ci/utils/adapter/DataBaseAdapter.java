package com.ai.rti.ic.grp.ci.utils.adapter;

import java.io.BufferedReader;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class DataBaseAdapter {
	private static Logger log = Logger.getLogger(DataBaseAdapter.class);

	public static final String DBMS_ORACLE = "ORACLE";

	public static final String DBMS_ODBC = "ODBC";

	public static final String DBMS_ACESS = "ACESS";

	public static final String DBMS_MYSQL = "MYSQL";

	public static final String DBMS_DB2 = "DB2";

	public static final String DBMS_SQLSERVER = "SQLSERVER";

	public static final String DBMS_TERA = "TERA";

	public static final String DBMS_SYBASE = "SYBASE";

	public static final String DBMS_POSTGRE = "POSTGRESQL";

	public static final String DBMS_GBASE = "GBASE";

	public static final String DBMS_SPARKSQL = "SPARKSQL";

	public static final String DATA_FORMAT1 = "yyyy-MM-dd";

	public static final String DATA_FORMAT2 = "yyyy/MM/dd";

	public static final String DATA_FORMAT3 = "yyyyMMdd";

	public static final String SECOND = "SECOND";

	public static final String MINUTE = "MINUTE";

	public static final String HOUR = "HOUR";

	public static final String DAY = "DAY";

	public static final String MONTH = "MONTH";

	public static final String YEAR = "YEAR";

	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

	public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

	public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

	public static final String DATE_FORMAT_YYYY = "yyyy";

	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public IDbAdapter dbAdapter = null;

	public DataBaseAdapter(String dbType) {
		this.dbAdapter = DbAdapterFactory.dbAdapterInit(dbType);
	}

	public String getDbType() throws RuntimeException {
		return this.dbAdapter.getDbType();
	}

	public String getChar2Timestamp(String colName, String format) {
		return this.dbAdapter.getChar2Timestamp(colName, format);
	}

	public String getTimestamp2Char(String colName, String format) {
		return this.dbAdapter.getTimestamp2Char(colName, format);
	}

	public String getTimeStamp(String strDateStr, String strH, String strM, String strS) throws RuntimeException {
		return this.dbAdapter.getTimeStamp(strDateStr, strH, strM, strS);
	}

	public String getTimeStamp(String strDateStr) throws RuntimeException {
		return getTimeStamp(strDateStr, "00", "00", "00");
	}

	public String getTimeStamp(Date date) {
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dFormat2 = new SimpleDateFormat("HH");
		SimpleDateFormat dFormat3 = new SimpleDateFormat("mm");
		SimpleDateFormat dFormat4 = new SimpleDateFormat("ss");
		return getTimeStamp(dFormat.format(date), dFormat2.format(date), dFormat3.format(date), dFormat4.format(date));
	}

	public String getDate(String strDateStr) {
		return this.dbAdapter.getDate(strDateStr);
	}

	public String getDate(String strDateStr, String format) {
		if (format.equals("yyyy-MM-dd"))
			return getDate(strDateStr);
		if (format.equals("yyyy/MM/dd")) {
			return getDate2(strDateStr);
		}
		return getDate3(strDateStr);
	}

	private String getDate2(String strDateStr) throws RuntimeException {
		return this.dbAdapter.getDate2(strDateStr);
	}

	private String getDate3(String strDateStr) throws RuntimeException {
		return this.dbAdapter.getDate3(strDateStr);
	}

	public String getFullDate(String strDateColName) throws RuntimeException {
		return this.dbAdapter.getFullDate(strDateColName);
	}

	public String getNow() throws RuntimeException {
		return this.dbAdapter.getNow();
	}

	public String getSqlLimit(String strSql, int limitnum) {
		return this.dbAdapter.getSqlLimit(strSql, limitnum);
	}

	public String getPagedSql(String srcSql, int currPage, int pageSize) {
		return this.dbAdapter.getPagedSql(srcSql, currPage, pageSize);
	}

	public String getPagedSql(String sql, String column, String strPrimaryKey, int curpage, int pagesize)
			throws RuntimeException {
		return this.dbAdapter.getPagedSql(sql, column, strPrimaryKey, curpage, pagesize);
	}

	public String getSubString(String strColName, int pos, int len) throws RuntimeException {
		return this.dbAdapter.getSubString(strColName, pos, len);
	}

	public String getSubString(String strColName, int pos) throws RuntimeException {
		return getSubString(strColName, pos, -1);
	}

	public String getAddDate(String interv, String unit) throws RuntimeException {
		return this.dbAdapter.getAddDate(interv, unit);
	}

	public String getDateAddMonth(String monthNum) throws RuntimeException {
		return this.dbAdapter.getDateAddMonth(monthNum);
	}

	public String getIntToChar(String strColName) throws RuntimeException {
		return this.dbAdapter.getIntToChar(strColName);
	}

	public String getCharToInt(String strColName) throws RuntimeException {
		return this.dbAdapter.getCharToInt(strColName);
	}

	public String getCharToDouble(String strColName) {
		return this.dbAdapter.getCharToDouble(strColName);
	}

	public String getRound(String str1, String str2) throws RuntimeException {
		return this.dbAdapter.getRound(str1, str2);
	}

	public String getNotEqual() throws RuntimeException {
		return this.dbAdapter.getNotEqual();
	}

	public String getNvl(String str1, String str2) throws RuntimeException {
		return this.dbAdapter.getNvl(str1, str2);
	}

	public String getCreateAsTableSql(String newtable, String templettable, String tableSpace) throws RuntimeException {
		return this.dbAdapter.getCreateAsTableSql(newtable, templettable, tableSpace);
	}

	public String getCreateTableInTableSpaceSql(String tableDDLSql, String tableSpace) throws RuntimeException {
		return this.dbAdapter.getCreateTableInTableSpaceSql(tableDDLSql, tableSpace);
	}

	public String getCreateIndexInTableSpaceSql(String createIndexSql, String tableSpace) throws RuntimeException {
		return this.dbAdapter.getCreateIndexInTableSpaceSql(createIndexSql, tableSpace);
	}

	public String getCheckTableIsExistSql(String tableName) throws RuntimeException {
		return this.dbAdapter.getCheckTableIsExistSql(tableName);
	}

	public String clobToString(Clob clob) {
		String s = "";
		StringBuffer content = new StringBuffer();

		try {
			BufferedReader br = new BufferedReader(clob.getCharacterStream());
			s = br.readLine();

			return content.toString();
		} catch (Exception e) {
			return content.toString();
		} finally {
			Exception exception = null;
		}

	}

	public String queryTree(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		return this.dbAdapter.queryTree(tableName, idName, pidName, startId, orientation, orderBy, args);
	}

	public String getTreeSql(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		return this.dbAdapter.getTreeSql(tableName, idName, pidName, startId, orientation, orderBy, args);
	}

	public String getStringLen(String strColName) {
		return this.dbAdapter.getStringLen(strColName);
	}

	public String getPosString(String strColName, String charName) throws RuntimeException {
		return this.dbAdapter.getPosString(strColName, charName);
	}

	public String getSubString(String strColName, String pos, String len) throws RuntimeException {
		return this.dbAdapter.getSubString(strColName, pos, len);
	}

	public String getSqlCreateAsTable(String newTab, String tmpTable, String priKey) throws Exception {
		return this.dbAdapter.getSqlCreateAsTable(newTab, tmpTable, priKey);
	}

	public String getTrim(String column) {
		return this.dbAdapter.getTrim(column);
	}

	public String getSequenceSql(String sequenceName) throws Exception {
		return this.dbAdapter.getSequenceSql(sequenceName);
	}

	public String getLimtCountSql(String srcSql, int currPage, int pageSize, String attrSqlStr) {
		return this.dbAdapter.getLimtCountSql(srcSql, currPage, pageSize, attrSqlStr);
	}

	public String getOverFunPostfix(String column) {
		return this.dbAdapter.getOverFunPostfix(column);
	}

	public String getSqlAddColumn(String tableName, String column, String type) throws Exception {
		return this.dbAdapter.getSqlAddColumn(tableName, column, type);
	}

	public String getColumnType(String columnType) {
		return this.dbAdapter.getColumnType(columnType);
	}

	public String getInsertIntoSql(String selectSql, String intoTableName, String columns) {
		return this.dbAdapter.getInsertIntoSql(selectSql, intoTableName, columns);
	}

	public String getRenameTableSql(String tableName, String toTableName) {
		return this.dbAdapter.getRenameTableSql(tableName, toTableName);
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

	public String getConnectorSql(String... args) {
		return this.dbAdapter.getConnectorSql(args);
	}

	public String getColumnToChar(String strColName) throws RuntimeException {
		return this.dbAdapter.getColumnToChar(strColName);
	}

	public String getDwLableFromTable(int cycle, String date) {
		return this.dbAdapter.getDwLableFromTable(cycle, date);
	}
}
