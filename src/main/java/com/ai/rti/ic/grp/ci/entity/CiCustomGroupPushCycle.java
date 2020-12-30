package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;
import java.util.Date;

public class CiCustomGroupPushCycle implements Serializable {
	private static final long serialVersionUID = 1L;
//	private CiCustomGroupPushCycleId id;
	private String customGroupId;
	private String sysId;
	private Integer status;
	private Integer pushCycle;
	private Integer isPushed;
	private Date modifyTime;

	public CiCustomGroupPushCycle() {
	}

	public CiCustomGroupPushCycle(String customGroupId, String sysId, Integer status) {
		this.customGroupId = customGroupId;
		this.sysId = sysId;
		this.status = status;
	}
	
//	public CiCustomGroupPushCycle(CiCustomGroupPushCycleId id, Integer status) {
//		this.id = id;
//		this.status = status;
//	}
//
//	public CiCustomGroupPushCycleId getId() {
//		return this.id;
//	}
//
//	public void setId(CiCustomGroupPushCycleId id) {
//		this.id = id;
//	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPushCycle() {
		return this.pushCycle;
	}

	public void setPushCycle(Integer pushCycle) {
		this.pushCycle = pushCycle;
	}

	public Integer getIsPushed() {
		return this.isPushed;
	}

	public void setIsPushed(Integer isPushed) {
		this.isPushed = isPushed;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCustomGroupId() {
		return customGroupId;
	}

	public void setCustomGroupId(String customGroupId) {
		this.customGroupId = customGroupId;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	
}
