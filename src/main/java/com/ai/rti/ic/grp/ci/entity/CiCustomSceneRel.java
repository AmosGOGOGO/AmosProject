package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;
import java.util.Date;

public class CiCustomSceneRel implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
//	private CiCustomSceneRelId id;
	private String userId;
	private String customGroupId;
	private String sceneId;
	private int status;
	private Date modifyTime;

	public CiCustomSceneRel() {
	}
	public CiCustomSceneRel(String userId, String customGroupId, String sceneId) {
		this.userId = userId;
		this.customGroupId = customGroupId;
		this.sceneId = sceneId;
	}
//	public CiCustomSceneRel(CiCustomSceneRelId id) {
//		this.id = id;
//	}
//
//	public CiCustomSceneRel(CiCustomSceneRelId id, int status, Date modifyTime) {
//		this.id = id;
//		this.status = status;
//		this.modifyTime = modifyTime;
//	}
//
//	public CiCustomSceneRelId getId() {
//		return this.id;
//	}
//
//	public void setId(CiCustomSceneRelId id) {
//		this.id = id;
//	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomGroupId() {
		return this.customGroupId;
	}

	public void setCustomGroupId(String customGroupId) {
		this.customGroupId = customGroupId;
	}

	public String getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
}
