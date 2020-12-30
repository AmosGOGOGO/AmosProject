package com.ai.rti.ic.grp.ci.utils.adapter;

import com.ai.rti.ic.grp.utils.Config;

public class TeraAdapter implements IDbAdapter {
	public String getDbType() throws RuntimeException {
		return "TERA";
	}

	public String getTimeStamp(String strDateStr, String strH, String strM, String strS) throws RuntimeException {
		if (null == strDateStr || strDateStr.length() < 1) {
			return null;
		}
		if (strDateStr.indexOf("0000-00-00") >= 0) {
			return "null";
		}
		String strRet = "";
		strRet = strDateStr + " (FORMAT 'YYYY-MM-DD')";
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
		strRet = "cast('" + strDateStr + "' as date FORMAT 'YYYY-MM-DD')";
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
		strRet = "cast('" + strDateStr + "' as date FORMAT 'YYYY/MM/DD')";
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
		strRet = strDateStr + " (FORMAT 'YYYYMMDD')";
		return strRet;
	}

	public String getFullDate(String strDateColName) throws RuntimeException {
		String strRet = "";
		strRet = strDateColName + " (FORMAT 'YYYY-MM-DD')";
		return strRet;
	}

	public String getNow() throws RuntimeException {
		String strRet = "";
		strRet = "cast((date (format 'yyyy-mm-dd' )) as char(10)) ||' '|| time";
		return strRet;
	}

	public String getSqlLimit(String strSql, int limitnum) {
		String strRet = "";
		StringBuffer buffer = new StringBuffer(strSql.length() + 100);
		buffer.append(strSql);
		int orderByIndex = buffer.toString().toLowerCase().lastIndexOf("order by");
		if (orderByIndex > 0) {

			String orderBy = buffer.substring(orderByIndex);

			buffer.insert(orderByIndex, " QUALIFY row_number() OVER( ");
			buffer.append(" ) ");
			buffer.append(" between 1 and " + limitnum);
			buffer.append(orderBy);
		} else {
			buffer.append(" QUALIFY sum(1) over (rows unbounded preceding) between 1 and " + limitnum);
		}
		strRet = buffer.toString();
		return strRet;
	}

	public String getPagedSql(String srcSql, int currPage, int pageSize) {
		int begin = (currPage - 1) * pageSize;
		int end = begin + pageSize;
		String strRet = srcSql;
		StringBuffer buffer = new StringBuffer(srcSql.length() + 100);
		buffer.append(srcSql);
		int orderByIndex = buffer.toString().toLowerCase().lastIndexOf("order by");
		if (orderByIndex > 0) {
			String orderBy = buffer.substring(orderByIndex);
			buffer.insert(orderByIndex, " QUALIFY row_number() OVER( ");
			buffer.append(" ) ");
			buffer.append(" between " + begin + " and " + end);
			buffer.append(orderBy);
		} else {
			buffer.append(" QUALIFY sum(1) over (rows unbounded preceding) between " + begin + " and " + end);
		}
		strRet = buffer.toString();
		return strRet;
	}

	public String getPagedSql(String sql, String column, String strPrimaryKey, int curpage, int pagesize)
			throws RuntimeException {
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		buffer.append(sql);
		int orderByIndex = buffer.toString().toLowerCase().lastIndexOf("order by");
		if (orderByIndex > 0) {
			String orderBy = buffer.substring(orderByIndex);

			buffer.insert(orderByIndex, " QUALIFY row_number() OVER( ");
			buffer.append(" ) ");
			buffer.append(" between " + (pagesize * curpage) + " and " + (pagesize * curpage + pagesize));
			buffer.append(orderBy);
		} else {
			buffer.append(" QUALIFY sum(1) over (rows unbounded preceding) between " + (pagesize * curpage) + " and "
					+ (pagesize * curpage + pagesize));
		}

		return buffer.toString();
	}

	public String getSubString(String strColName, int pos, int len) throws RuntimeException {
		String strRet = "";
		if (len == -1) {
			strRet = "substring(" + strColName + " form " + pos + ")";
		} else {
			strRet = "substring(" + strColName + " from " + pos + " for " + len + ")";
		}
		return strRet;
	}

	public String getAddDate(String interv, String unit) throws RuntimeException {
		throw new RuntimeException("请实现Tera函数定义");
	}

	public String getDateAddMonth(String monthNum) throws RuntimeException {
		String strRet = "";
		strRet = "add_months(date," + monthNum + ")";
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
		throw new RuntimeException("不能取得函数定义");
	}

	public String getRound(String str1, String str2) throws RuntimeException {
		return " cast ((" + str1 + ") as decimal(10," + str2 + ")) ";
	}

	public String getNotEqual() throws RuntimeException {
		return "<>";
	}

	public String getNvl(String str1, String str2) throws RuntimeException {
		String strRet = "";
		strRet = "COALESCE(" + str1 + "," + str2 + ")";
		return strRet;
	}

	public String getCreateAsTableSql(String newtable, String templettable, String tableSpace) throws RuntimeException {
		String ss = "";
		ss = "create table " + newtable + " as " + templettable + " with no data";
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
		StringBuffer sql = new StringBuffer();
		sql.append("select * from dbc.tables where tablename='").append(tableName.toUpperCase()).append("'");
		strSql = sql.toString();
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
		throw new RuntimeException("请实现Tera函数定义");
	}

	public String getPosString(String strColName, String charName) throws RuntimeException {
		throw new RuntimeException("请实现Tera函数定义");
	}

	public String getSubString(String strColName, String pos, String len) throws RuntimeException {
		String strRet = "";
		strRet = "substring(" + strColName + " from " + pos + " for " + len + ")";
		return strRet;
	}

	public String getSqlCreateAsTable(String newTab, String tmpTable, String priKey) throws Exception {
		throw new RuntimeException("请实现Tera函数定义");
	}

	public String getTrim(String column) {
		return "trim(" + column + ")";
	}

	public String getSequenceSql(String sequenceName) throws Exception {
		return null;
	}

	public String getLimtCountSql(String srcSql, int currPage, int pageSize, String attrSqlStr) {
		int begin = (currPage - 1) * pageSize;
		int end = begin + pageSize;
		String strRet = srcSql;
		StringBuffer buffer = new StringBuffer(srcSql.length() + 100);
		buffer.append(srcSql);

		int orderByIndex = buffer.toString().toLowerCase().lastIndexOf("order by");
		if (orderByIndex > 0) {
			String orderBy = buffer.substring(orderByIndex);
			buffer.insert(orderByIndex, " QUALIFY row_number() OVER( ");
			buffer.append(" ) ");
			buffer.append(" between " + begin + " and " + end);
			buffer.append(orderBy);
		} else {
			buffer.append(" QUALIFY sum(1) over (rows unbounded preceding) between " + begin + " and " + end);
		}

		strRet = buffer.toString();
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
		String DW_LABEL_FORM_TABLE =Config.getObject("DW_LABEL_FORM_TABLE");

		return DW_LABEL_FORM_TABLE + date;
	}
}
