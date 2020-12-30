package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;

public class DimAnnouncementType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer typeId;
	private String typeName;
	private String typeDesc;
	private Integer hasSuccessFail;

	public DimAnnouncementType() {
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public DimAnnouncementType(Integer typeId, String typeName, String typeDesc, Integer hasSuccessFail) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeDesc = typeDesc;
		this.hasSuccessFail = hasSuccessFail;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDesc() {
		return this.typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Integer getHasSuccessFail() {
		return this.hasSuccessFail;
	}

	public void setHasSuccessFail(Integer hasSuccessFail) {
		this.hasSuccessFail = hasSuccessFail;
	}

	public String toString() {
		return "DimAnnouncementType [hasSuccessFail=" + this.hasSuccessFail + ", typeDesc=" + this.typeDesc
				+ ", typeId=" + this.typeId + ", typeName=" + this.typeName + "]";
	}
}
