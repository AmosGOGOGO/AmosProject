package com.ai.rti.ic.grp.ci.utils.adapter;

import com.ai.rti.ic.grp.constant.CommonConstants;
import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.StringUtil;

public class OracleAdapter implements IDbAdapter {
	public String getDbType() throws RuntimeException {
		return "ORACLE";
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
		if (i > 0)
			strDateStr = strDateStr.substring(0, i);
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
		if (i > 0)
			strDateStr = strDateStr.substring(0, i);
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
		if (i > 0)
			strDateStr = strDateStr.substring(0, i);
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
		strRet = "sysdate";
		return strRet;
	}

	public String getSqlLimit(String strSql, int limitnum) {
		String strRet = "";
		limitnum++;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (").append(strSql).append(") where ROWNUM<").append(limitnum);
		strRet = sql.toString();
		return strRet;
	}

	public String getPagedSql(String srcSql, int currPage, int pageSize) {
		int begin = (currPage - 1) * pageSize;
		int end = begin + pageSize;
		String strRet = srcSql;

		if (end < Integer.MAX_VALUE) {
			end++;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT ROWNUM AS NUMROW, d.* from (").append(srcSql).append(") d) WHERE NUMROW >")
				.append(begin);
		sql.append(" AND NUMROW <").append(end);
		strRet = sql.toString();

		return strRet;
	}

	public String getPagedSql(String sql, String column, String strPrimaryKey, int curpage, int pagesize)
			throws RuntimeException {
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		buffer.append("select * from ( ");
		buffer.append("select ").append(column).append(" rownum as my_rownum from( ");
		buffer.append(sql).append(") ");
		int pageAll = pagesize * curpage + pagesize;
		buffer.append("where rownum <= " + pageAll + ") a ");
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
		if (!interv.startsWith("-"))
			interv = "+" + interv;
		if (unit.compareTo("MINUTE") == 0) {
			strRet = "(sysdate" + interv + "/(24*60))";
		} else if (unit.compareTo("SECOND") == 0) {
			strRet = "(sysdate" + interv + "/(24*60*60))";
		} else if (unit.compareTo("HOUR") == 0) {
			strRet = "(sysdate" + interv + "/24)";
		} else if (unit.compareTo("DAY") == 0) {
			strRet = "(sysdate" + interv + ")";
		} else if (unit.compareTo("MONTH") == 0) {
			strRet = "(add_months(sysdate," + interv + "))";
		} else if (unit.compareTo("YEAR") == 0) {
			strRet = "(add_months(sysdate,(" + interv + "*12)))";
		}
		return strRet;
	}

	public String getDateAddMonth(String monthNum) throws RuntimeException {
		String strRet = "";
		strRet = "to_char(add_months(sysdate," + monthNum + "),'YYYY-mm-dd')";
		return strRet;
	}

	public String getIntToChar(String strColName) throws RuntimeException {
		String strRet = "";
		strRet = "cast(" + strColName + " as varchar2(12))";
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
		strRet = "nvl(" + str1 + "," + str2 + ")";
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
		if (tableSpace == null || tableSpace.length() < 1)
			return createIndexSql;
		createIndexSql = createIndexSql + " using index tablespace " + tableSpace;
		return createIndexSql;
	}

	public String getCheckTableIsExistSql(String tableName) throws RuntimeException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from TAB where tname='").append(tableName.toUpperCase()).append("'");
		return sql.toString();
	}

	public String queryTree(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		String strSql = null;
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
			StringBuffer sql = new StringBuffer();
			sql.append("select ").append(selectRet).append(" from ").append(tableName).append(" start with ")
					.append(start).append(" connect by prior ");
			sql.append(idName).append("=").append(pidName).append(" order by ").append(idName).append(" ")
					.append(order);
			strSql = sql.toString();
		}
		if (orientation == 0) {
			StringBuffer sql = new StringBuffer();
			sql.append("select ").append(selectRet).append(" from ").append(tableName).append(" start with ")
					.append(start).append(" connect by prior ");
			sql.append(pidName).append("=").append(idName).append(" order by ").append(idName).append(" ")
					.append(order);
			strSql = sql.toString();
		}
		return strSql;
	}

	public String getTreeSql(String tableName, String idName, String pidName, Object startId, int orientation,
			String orderBy, String... args) throws RuntimeException {
		String strSql = null;
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
			StringBuffer sql = new StringBuffer();
			sql.append("select ").append(selectRet).append(" from ").append(tableName).append(" start with ")
					.append(start).append(" connect by prior ");
			sql.append(idName).append("=").append(pidName).append(" order by ").append(idName).append(" ")
					.append(order);
			strSql = sql.toString();
		}
		if (orientation == 0) {
			StringBuffer sql = new StringBuffer();
			sql.append("select ").append(selectRet).append(" from ").append(tableName).append(" start with ");
			sql.append(start).append(" connect by prior ").append(pidName).append("=").append(idName);
			sql.append(" order by ").append(idName).append(" ").append(order);
			strSql = sql.toString();
		}
		return strSql;
	}

	public String getStringLen(String strColName) {
		String strRet = "";
		strRet = "length(" + strColName + ")";
		return strRet;
	}

	public String getPosString(String strColName, String charName) throws RuntimeException {
		String strRet = "";
		strRet = "instr(" + strColName + ",'" + charName + "')";
		return strRet;
	}

	public String getSubString(String strColName, String pos, String len) throws RuntimeException {
		String strRet = "";
		strRet = "substr(" + strColName + "," + pos + "," + len + ")";
		return strRet;
	}

	public String getSqlCreateAsTable(String newTab, String tmpTable, String priKey) throws Exception {
		StringBuilder strRet = new StringBuilder();
		String tabSpace = Config.getObject("CI_TABLESPACE");
		if (StringUtil.isNotEmpty(tabSpace)) {
			strRet.append("create table ").append(newTab).append(" tablespace ").append(tabSpace)
					.append(" as select * from ").append(tmpTable).append(" where 1=2 ");
		} else {
			strRet.append("create table ").append(newTab).append(" as select * from ").append(tmpTable)
					.append(" where 1=2 ");
		}
		return strRet.toString();
	}

	public String getTrim(String column) {
		return "trim(" + column + ")";
	}

	public String getSequenceSql(String sequenceName) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select ").append(sequenceName).append(".nextval from dual");
		return sqlBuilder.toString();
	}

	public String getLimtCountSql(String srcSql, int currPage, int pageSize, String attrSqlStr) {
		int begin = (currPage - 1) * pageSize;
		int end = begin + pageSize;
		String strRet = srcSql;

		if (end < Integer.MAX_VALUE) {
			end++;
		}
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(" SELECT ").append(attrSqlStr).append(" FROM (SELECT ROWNUM AS NUMROW, d.* from (")
				.append(srcSql).append(") d) WHERE NUMROW >").append(begin).append(" AND NUMROW <").append(end);
		strRet = selectSql.toString();
		return strRet;
	}

	public String getOverFunPostfix(String column) {
		StringBuffer result = new StringBuffer();
		result.append(" order by ").append(column);
		return result.toString();
	}

	public String getSqlAddColumn(String tableName, String column, String type) throws Exception {
		String schema = Config.getObject("CI_SCHEMA");
		if (StringUtil.isNotEmpty(schema)) {
			tableName = schema + "." + tableName;
		}
		StringBuffer sql = new StringBuffer("");
		sql.append(" ALTER TABLE ").append(tableName).append(" ADD ").append(column).append(" ").append(type);
		return sql.toString();
	}

	public String getColumnType(String columnType) {
		if (StringUtil.isEmpty(columnType)) {
			columnType = "VARCHAR2(512)";
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
		renameSql.append("ALTER TABLE ").append(tableName).append(" RENAME TO ").append(toTableName);
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
		strRet = strColName;
		return strRet;
	}

	public String getChar2Timestamp(String colName, String format) {
		String strRet = "";
		if ("yyyy-MM-dd".equals(format)) {
			strRet = "to_date(" + colName + ",'YYYY-MM-DD')";
		} else if ("yyyyMMdd".equals(format)) {
			strRet = "to_date(" + colName + ",'YYYYMMDD')";
		} else if ("yyyy-MM".equals(format)) {
			strRet = "to_date(" + colName + ",'YYYY-MM')";
		} else if ("yyyyMM".equals(format)) {
			strRet = "to_date(" + colName + ",'YYYYMM')";
		} else if ("yyyy".equals(format)) {
			strRet = "to_date(" + colName + ",'YYYY')";
		} else if ("yyyy-MM-dd HH:mm".equals(format)) {
			strRet = "to_date(" + colName + ",'YYYY-MM-DD HH24:MI')";
		} else if ("yyyy-MM-dd HH:mm:ss".equals(format)) {
			strRet = "to_date(" + colName + ",'YYYY-MM-DD HH24:MI:SS')";
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
