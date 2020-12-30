package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;

public class DimCustomCreateType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer createTypeId;
	private String createTypeName;
	private String createTypeDesc;

	public DimCustomCreateType() {
	}

	public DimCustomCreateType(String createTypeName, String createTypeDesc) {
		this.createTypeName = createTypeName;
		this.createTypeDesc = createTypeDesc;
	}

	public Integer getCreateTypeId() {
		return this.createTypeId;
	}

	public void setCreateTypeId(Integer createTypeId) {
		this.createTypeId = createTypeId;
	}

	public String getCreateTypeName() {
		return this.createTypeName;
	}

	public void setCreateTypeName(String createTypeName) {
		this.createTypeName = createTypeName;
	}

	public String getCreateTypeDesc() {
		return this.createTypeDesc;
	}

	public void setCreateTypeDesc(String createTypeDesc) {
		this.createTypeDesc = createTypeDesc;
	}
}
