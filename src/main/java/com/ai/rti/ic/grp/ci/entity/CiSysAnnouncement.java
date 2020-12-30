package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class CiSysAnnouncement implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String announcementId;
	private String announcementName;
	private String announcementDetail;
	private Integer typeId;
	private Integer priorityId;
	private Timestamp effectiveTime;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	private Integer status;

	private Timestamp releaseDate;

	private Integer deleteStatus;

	private String userId;
	private Timestamp readTime;
	private Integer readStatus;
	private List<String> announcementIdList;

	public List<String> getAnnouncementIdList() {
		return this.announcementIdList;
	}

	public void setAnnouncementIdList(List<String> announcementIdList) {
		this.announcementIdList = announcementIdList;
	}

	public CiSysAnnouncement() {
	}

	public CiSysAnnouncement(String announcementName, String announcementDetail, Integer typeId, Integer priorityId,
			Timestamp effectiveTime, Integer status, Timestamp releaseDate, Integer deleteStatus, String userId,
			Timestamp readTime, Integer readStatus) {
		this.announcementName = announcementName;
		this.announcementDetail = announcementDetail;
		this.typeId = typeId;
		this.priorityId = priorityId;
		this.effectiveTime = effectiveTime;
		this.status = status;
		this.releaseDate = releaseDate;
		this.deleteStatus = deleteStatus;
		this.userId = userId;
		this.readTime = readTime;
		this.readStatus = readStatus;
	}

	public String getAnnouncementId() {
		return this.announcementId;
	}

	public void setAnnouncementId(String announcementId) {
		this.announcementId = announcementId;
	}

	public String getAnnouncementName() {
		return this.announcementName;
	}

	public void setAnnouncementName(String announcementName) {
		this.announcementName = announcementName;
	}

	public String getAnnouncementDetail() {
		return this.announcementDetail;
	}

	public void setAnnouncementDetail(String announcementDetail) {
		this.announcementDetail = announcementDetail;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getPriorityId() {
		return this.priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Timestamp getEffectiveTime() {
		return this.effectiveTime;
	}

	public void setEffectiveTime(Timestamp effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Timestamp releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getDeleteStatus() {
		return this.deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getReadStatus() {
		return this.readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	public Timestamp getReadTime() {
		return this.readTime;
	}

	public void setReadTime(Timestamp readTime) {
		this.readTime = readTime;
	}
}
