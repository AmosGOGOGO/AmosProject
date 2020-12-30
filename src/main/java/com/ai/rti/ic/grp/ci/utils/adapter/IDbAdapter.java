package com.ai.rti.ic.grp.ci.utils.adapter;

public interface IDbAdapter {
	String getDbType() throws RuntimeException;

	String getChar2Timestamp(String paramString1, String paramString2);

	String getTimestamp2Char(String paramString1, String paramString2);

	String getTimeStamp(String paramString1, String paramString2, String paramString3, String paramString4)
			throws RuntimeException;

	String getDate(String paramString);

	String getDate2(String paramString) throws RuntimeException;

	String getDate3(String paramString) throws RuntimeException;

	String getFullDate(String paramString) throws RuntimeException;

	String getNow() throws RuntimeException;

	String getSqlLimit(String paramString, int paramInt) throws RuntimeException;

	String getPagedSql(String paramString, int paramInt1, int paramInt2) throws RuntimeException;

	String getPagedSql(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
			throws RuntimeException;

	String getSubString(String paramString, int paramInt1, int paramInt2) throws RuntimeException;

	String getAddDate(String paramString1, String paramString2) throws RuntimeException;

	String getDateAddMonth(String paramString) throws RuntimeException;

	String getIntToChar(String paramString) throws RuntimeException;

	String getCharToInt(String paramString) throws RuntimeException;

	String getCharToDouble(String paramString);

	String getRound(String paramString1, String paramString2) throws RuntimeException;

	String getNotEqual() throws RuntimeException;

	String getNvl(String paramString1, String paramString2) throws RuntimeException;

	String getCreateAsTableSql(String paramString1, String paramString2, String paramString3) throws RuntimeException;

	String getCreateTableInTableSpaceSql(String paramString1, String paramString2) throws RuntimeException;

	String getCreateIndexInTableSpaceSql(String paramString1, String paramString2) throws RuntimeException;

	String getCheckTableIsExistSql(String paramString) throws RuntimeException;

	String queryTree(String paramString1, String paramString2, String paramString3, Object paramObject, int paramInt,
			String paramString4, String... paramVarArgs) throws RuntimeException;

	String getTreeSql(String paramString1, String paramString2, String paramString3, Object paramObject, int paramInt,
			String paramString4, String... paramVarArgs) throws RuntimeException;

	String getStringLen(String paramString);

	String getPosString(String paramString1, String paramString2) throws RuntimeException;

	String getSubString(String paramString1, String paramString2, String paramString3) throws RuntimeException;

	String getSqlCreateAsTable(String paramString1, String paramString2, String paramString3) throws Exception;

	String getTrim(String paramString);

	String getSequenceSql(String paramString) throws Exception;

	String getLimtCountSql(String paramString1, int paramInt1, int paramInt2, String paramString2);

	String getOverFunPostfix(String paramString);

	String getSqlAddColumn(String paramString1, String paramString2, String paramString3) throws Exception;

	String getColumnType(String paramString);

	String getInsertIntoSql(String paramString1, String paramString2, String paramString3);

	String getRenameTableSql(String paramString1, String paramString2);

	String getConnectorSql(String... paramVarArgs);

	String getColumnToChar(String paramString) throws RuntimeException;

	String getDwLableFromTable(int paramInt, String paramString);
}
