package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;

public class DimNoticeSendMode implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sendModeId;
	private String sendModeName;
	private String descTxt;
	private Integer sortNum;

	public String getSendModeId() {
		return this.sendModeId;
	}

	public void setSendModeId(String sendModeId) {
		this.sendModeId = sendModeId;
	}

	public String getSendModeName() {
		return this.sendModeName;
	}

	public void setSendModeName(String sendModeName) {
		this.sendModeName = sendModeName;
	}

	public String getDescTxt() {
		return this.descTxt;
	}

	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	public Integer getSortNum() {
		return this.sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
}
