package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class CiGroupAttrRel implements Serializable {
	private static final long serialVersionUID = 1L;
//	private CiGroupAttrRelId id;
	private String customGroupId;
	private String attrCol;
	private Date modifyTime;
	
	private String attrColName;
	private String attrColType;
	private int attrSource;
	private String labelOrCustomId;
	private String labelOrCustomColumn;
	private int isVerticalAttr;
	private Integer status;
	private String attrVal;
	private String listTableName;
	private String tableName;
	private String sortType;
	private Integer sortNum;
	private String customName;
	private String customId;
	private String columnName;
	private String attrName;
	private String dataDateStr;
	private String colTypeCode;
	private Map<String, Object> attrRange;

	public CiGroupAttrRel() {
	}
	public CiGroupAttrRel(String customGroupId, String attrCol, Date modifyTime) {
		this.customGroupId = customGroupId;
		this.attrCol = attrCol;
		this.modifyTime = modifyTime;
	}
	public CiGroupAttrRel(String customGroupId, String attrCol, Date modifyTime, String attrColName, String attrColType, int attrSource,
			String labelOrCustomId, String labelOrCustomColumn, String customName, String customId, String columnName) {
		this.customGroupId = customGroupId;
		this.attrCol = attrCol;
		this.modifyTime = modifyTime;
		this.attrColName = attrColName;
		this.attrColType = attrColType;
		this.attrSource = attrSource;
		this.labelOrCustomId = labelOrCustomId;
		this.labelOrCustomColumn = labelOrCustomColumn;
		this.customName = customName;
		this.customId = customId;
		this.columnName = columnName;
	}
	
//	public CiGroupAttrRel(CiGroupAttrRelId id) {
//		this.id = id;
//	}
//
//	public CiGroupAttrRel(CiGroupAttrRelId id, String attrColName, String attrColType, int attrSource,
//			String labelOrCustomId, String labelOrCustomColumn, String customName, String customId, String columnName) {
//		this.id = id;
//		this.attrColName = attrColName;
//		this.attrColType = attrColType;
//		this.attrSource = attrSource;
//		this.labelOrCustomId = labelOrCustomId;
//		this.labelOrCustomColumn = labelOrCustomColumn;
//		this.customName = customName;
//		this.customId = customId;
//		this.columnName = columnName;
//	}
//
//	public CiGroupAttrRelId getId() {
//		return this.id;
//	}
//
//	public void setId(CiGroupAttrRelId id) {
//		this.id = id;
//	}

	public String getAttrColName() {
		return this.attrColName;
	}

	public void setAttrColName(String attrColName) {
		this.attrColName = attrColName;
	}

	public String getAttrColType() {
		return this.attrColType;
	}

	public void setAttrColType(String attrColType) {
		this.attrColType = attrColType;
	}

	public String getCustomName() {
		return this.customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getLabelOrCustomId() {
		return this.labelOrCustomId;
	}

	public void setLabelOrCustomId(String labelOrCustomId) {
		this.labelOrCustomId = labelOrCustomId;
	}

	public String getCustomId() {
		return this.customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getLabelOrCustomColumn() {
		return this.labelOrCustomColumn;
	}

	public void setLabelOrCustomColumn(String labelOrCustomColumn) {
		this.labelOrCustomColumn = labelOrCustomColumn;
	}

	public int getAttrSource() {
		return this.attrSource;
	}

	public void setAttrSource(int attrSource) {
		this.attrSource = attrSource;
	}

	public int getIsVerticalAttr() {
		return this.isVerticalAttr;
	}

	public void setIsVerticalAttr(int isVerticalAttr) {
		this.isVerticalAttr = isVerticalAttr;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAttrVal() {
		return this.attrVal;
	}

	public void setAttrVal(String attrVal) {
		this.attrVal = attrVal;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getSortType() {
		return this.sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Integer getSortNum() {
		return this.sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getDataDateStr() {
		return this.dataDateStr;
	}

	public void setDataDateStr(String dataDateStr) {
		this.dataDateStr = dataDateStr;
	}

	public String getColTypeCode() {
		return this.colTypeCode;
	}

	public void setColTypeCode(String colTypeCode) {
		this.colTypeCode = colTypeCode;
	}

	public Map<String, Object> getAttrRange() {
		return this.attrRange;
	}

	public void setAttrRange(Map<String, Object> attrRange) {
		this.attrRange = attrRange;
	}
	
	public String getCustomGroupId() {
		return this.customGroupId;
	}

	public void setCustomGroupId(String customGroupId) {
		this.customGroupId = customGroupId;
	}

	public String getAttrCol() {
		return this.attrCol;
	}

	public void setAttrCol(String attrCol) {
		this.attrCol = attrCol;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getListTableName() {
		return listTableName;
	}
	public void setListTableName(String listTableName) {
		this.listTableName = listTableName;
	}
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof CiGroupAttrRelId))
			return false;
		CiGroupAttrRelId castOther = (CiGroupAttrRelId) other;

		return ((getCustomGroupId() == castOther.getCustomGroupId() || (getCustomGroupId() != null
				&& castOther.getCustomGroupId() != null && getCustomGroupId().equals(castOther.getCustomGroupId())))
				&& (getAttrCol() == castOther.getAttrCol() || (getAttrCol() != null && castOther.getAttrCol() != null
						&& getAttrCol().equals(castOther.getAttrCol())))
				&& (getModifyTime() == castOther.getModifyTime() || (getModifyTime() != null
						&& castOther.getModifyTime() != null && getModifyTime().equals(castOther.getModifyTime()))));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + ((getCustomGroupId() == null) ? 0 : getCustomGroupId().hashCode());

		result = 37 * result + ((getAttrCol() == null) ? 0 : getAttrCol().hashCode());
		return result;
	}
}
