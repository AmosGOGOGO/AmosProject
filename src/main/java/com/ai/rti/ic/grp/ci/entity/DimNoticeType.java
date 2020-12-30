package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;

public class DimNoticeType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer noticeTypeId;
	private String noticeTypeName;
	private String noticeTypeDesc;
	private Integer hasSuccessFail;

	public DimNoticeType() {
	}

	public Integer getNoticeTypeId() {
		return this.noticeTypeId;
	}

	public DimNoticeType(Integer noticeTypeId, String noticeTypeName, String noticeTypeDesc, Integer hasSuccessFail) {
		this.noticeTypeId = noticeTypeId;
		this.noticeTypeName = noticeTypeName;
		this.noticeTypeDesc = noticeTypeDesc;
		this.hasSuccessFail = hasSuccessFail;
	}

	public void setNoticeTypeId(Integer noticeTypeId) {
		this.noticeTypeId = noticeTypeId;
	}

	public String getNoticeTypeName() {
		return this.noticeTypeName;
	}

	public void setNoticeTypeName(String noticeTypeName) {
		this.noticeTypeName = noticeTypeName;
	}

	public String getNoticeTypeDesc() {
		return this.noticeTypeDesc;
	}

	public void setNoticeTypeDesc(String noticeTypeDesc) {
		this.noticeTypeDesc = noticeTypeDesc;
	}

	public Integer getHasSuccessFail() {
		return this.hasSuccessFail;
	}

	public void setHasSuccessFail(Integer hasSuccessFail) {
		this.hasSuccessFail = hasSuccessFail;
	}

	public String toString() {
		return "DimNoticeType [hasSuccessFail=" + this.hasSuccessFail + ", noticeTypeDesc=" + this.noticeTypeDesc
				+ ", noticeTypeId=" + this.noticeTypeId + ", noticeTypeName=" + this.noticeTypeName + "]";
	}
}
