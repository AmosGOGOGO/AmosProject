package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;

public class CiCustomSceneRelId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String customGroupId;
	private String sceneId;

	public CiCustomSceneRelId() {
	}

	public CiCustomSceneRelId(String userId, String customGroupId, String sceneId) {
		this.userId = userId;
		this.customGroupId = customGroupId;
		this.sceneId = sceneId;
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
