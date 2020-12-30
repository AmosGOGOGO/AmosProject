package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;

public class CiUserNoticeSetId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String noticeId;
	private Integer noticeType;
	private Integer isSuccess;
	private String sendModeId;

	public CiUserNoticeSetId() {
	}

	public CiUserNoticeSetId(String userId, String noticeId, Integer noticeType, Integer isSuccess, String sendModeId) {
		this.userId = userId;
		this.noticeId = noticeId;
		this.noticeType = noticeType;
		this.isSuccess = isSuccess;
		this.sendModeId = sendModeId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public Integer getNoticeType() {
		return this.noticeType;
	}

	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}

	public Integer getIsSuccess() {
		return this.isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}

	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof CiUserNoticeSet))
			return false;
		CiUserNoticeSet castOther = (CiUserNoticeSet) other;

		return ((getUserId() == castOther.getUserId()
				|| (getUserId() != null && castOther.getUserId() != null && getUserId().equals(castOther.getUserId())))
				&& (getNoticeId() == castOther.getNoticeId() || (getNoticeId() != null
						&& castOther.getNoticeId() != null && getNoticeId().equals(castOther.getNoticeId())))
				&& (getNoticeType() == castOther.getNoticeType() || (getNoticeType() != null
						&& castOther.getNoticeType() != null && getNoticeType().equals(castOther.getNoticeType())))
				&& (getIsSuccess() == castOther.getIsSuccess() || (getIsSuccess() != null
						&& castOther.getIsSuccess() != null && getIsSuccess().equals(castOther.getIsSuccess())))
				&& (getSendModeId() == castOther.getSendModeId() || (getSendModeId() != null
						&& castOther.getSendModeId() != null && getSendModeId().equals(castOther.getSendModeId()))));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + ((getUserId() == null) ? 0 : getUserId().hashCode());

		result = 37 * result + ((getNoticeId() == null) ? 0 : getNoticeId().hashCode());

		result = 37 * result + ((getNoticeType() == null) ? 0 : getNoticeType().hashCode());

		result = 37 * result + ((getIsSuccess() == null) ? 0 : getIsSuccess().hashCode());
		return result;
	}

	public String toString() {
		return "CiUserNoticeSetId [isSuccess=" + this.isSuccess + ", noticeId=" + this.noticeId + ", noticeType="
				+ this.noticeType + ", userId=" + this.userId + "]";
	}

	public String getSendModeId() {
		return this.sendModeId;
	}

	public void setSendModeId(String sendModeId) {
		this.sendModeId = sendModeId;
	}
}
