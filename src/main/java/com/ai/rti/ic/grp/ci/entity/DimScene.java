package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;

public class DimScene implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sceneId;
	private String sceneName;
	private String sceneDesc;
	private Integer sortNum;

	public DimScene() {
	}

	public DimScene(String sceneId) {
		this.sceneId = sceneId;
	}

	public DimScene(String sceneId, String sceneName, String sceneDesc, Integer sortNum) {
		this.sceneId = sceneId;
		this.sceneName = sceneName;
		this.sceneDesc = sceneDesc;
		this.sortNum = sortNum;
	}

	public String getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getSceneName() {
		return this.sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String getSceneDesc() {
		return this.sceneDesc;
	}

	public void setSceneDesc(String sceneDesc) {
		this.sceneDesc = sceneDesc;
	}

	public Integer getSortNum() {
		return this.sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
}
