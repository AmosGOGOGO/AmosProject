package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;

public class CiCustomGroupPushCycleId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String customGroupId;
	private String sysId;

	public CiCustomGroupPushCycleId() {
	}

	public CiCustomGroupPushCycleId(String customGroupId, String sysId) {
		this.customGroupId = customGroupId;
		this.sysId = sysId;
	}

	public String getCustomGroupId() {
		return this.customGroupId;
	}

	public void setCustomGroupId(String customGroupId) {
		this.customGroupId = customGroupId;
	}

	public String getSysId() {
		return this.sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
}
