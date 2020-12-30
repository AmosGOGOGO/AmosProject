package com.ai.rti.ic.grp.ci.entity;

public class DimCityThreadConfig {
	private String cityId;
	private String cityName;
	private Integer threadNum;

	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getThreadNum() {
		return this.threadNum;
	}

	public void setThreadNum(Integer threadNum) {
		this.threadNum = threadNum;
	}
}
