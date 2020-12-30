package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AlarmRecord implements Serializable {
	private static final long serialVersionUID = -8503900383499011976L;
	private String recordId;
	private String thresholdId;
	private String alarmCase;
	private Date calculateDate;
	private String weekId;
	private String busiId;
	private String busiName;
	private String columnId;
	private String columnName;
	private BigDecimal resultValue;
	private int checkFlag;

	public String getRecordId() {
		return this.recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getThresholdId() {
		return this.thresholdId;
	}

	public void setThresholdId(String thresholdId) {
		this.thresholdId = thresholdId;
	}

	public String getAlarmCase() {
		return this.alarmCase;
	}

	public void setAlarmCase(String alarmCase) {
		this.alarmCase = alarmCase;
	}

	public Date getCalculateDate() {
		return this.calculateDate;
	}

	public void setCalculateDate(Date calculateDate) {
		this.calculateDate = calculateDate;
	}

	public String getWeekId() {
		return this.weekId;
	}

	public void setWeekId(String weekId) {
		this.weekId = weekId;
	}

	public String getBusiId() {
		return this.busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getBusiName() {
		return this.busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getColumnId() {
		return this.columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getCheckFlag() {
		return this.checkFlag;
	}

	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}

	public BigDecimal getResultValue() {
		return this.resultValue;
	}

	public void setResultValue(BigDecimal resultValue) {
		this.resultValue = resultValue;
	}
}
