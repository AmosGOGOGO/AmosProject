package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;

public class ThresholdVarRef implements Serializable {
	private static final long serialVersionUID = -6670367431096069744L;
	private String conditionId;
	private String thresholdId;
	private String attColumn;
	private String maxValue;
	private String minValue;
	private String maxValueName;
	private String minValueName;
	private String comparisonType;
	private String dateComparisonType;

	public ThresholdVarRef(String conditionId, String thresholdId, String attColumn, String maxValue, String minValue,
			String maxValueName, String minValueName, String comp, String dateComp) {
		this.attColumn = attColumn;
		this.comparisonType = comp;
		this.conditionId = conditionId;
		this.dateComparisonType = dateComp;
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.maxValueName = maxValueName;
		this.minValueName = minValueName;
		this.thresholdId = thresholdId;
	}

	public ThresholdVarRef() {
	}

	public String getConditionId() {
		return this.conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public String getThresholdId() {
		return this.thresholdId;
	}

	public void setThresholdId(String thresholdId) {
		this.thresholdId = thresholdId;
	}

	public String getAttColumn() {
		return this.attColumn;
	}

	public void setAttColumn(String attColumn) {
		this.attColumn = attColumn;
	}

	public String getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinValue() {
		return this.minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getComparisonType() {
		return this.comparisonType;
	}

	public void setComparisonType(String comparisonType) {
		this.comparisonType = comparisonType;
	}

	public String getDateComparisonType() {
		return this.dateComparisonType;
	}

	public void setDateComparisonType(String dateComparisonType) {
		this.dateComparisonType = dateComparisonType;
	}

	public String getMaxValueName() {
		return this.maxValueName;
	}

	public void setMaxValueName(String maxValueName) {
		this.maxValueName = maxValueName;
	}

	public String getMinValueName() {
		return this.minValueName;
	}

	public void setMinValueName(String minValueName) {
		this.minValueName = minValueName;
	}
}
