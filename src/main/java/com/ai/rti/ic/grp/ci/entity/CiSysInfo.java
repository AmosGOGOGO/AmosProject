package com.ai.rti.ic.grp.ci.entity;

import java.io.Serializable;

public class CiSysInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sysId;
	private String sysName;
	private String ftpServerIp;
	private String ftpUser;
	private String ftpPort;
	private String ftpPwd;
	private String ftpPath;
	private String localPath;
	private String webserviceWSDL;
	private String webserviceTargetNamespace;
	private String webserviceMethod;
	private String webserviceArgs;
	private Integer showInPage;
	private String descTxt;
	private Integer isNeedXml;
	private Integer isNeedDes;
	private String desKey;
	private Integer isNeedCycle;
	private String actionId;
	private String functionId;
	private String pushClassName;
	private String reqId;

	public String getPushClassName() {
		return this.pushClassName;
	}

	public void setPushClassName(String pushClassName) {
		this.pushClassName = pushClassName;
	}

	public String getActionId() {
		return this.actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getSysId() {
		return this.sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getSysName() {
		return this.sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getFtpServerIp() {
		return this.ftpServerIp;
	}

	public void setFtpServerIp(String ftpServerIp) {
		this.ftpServerIp = ftpServerIp;
	}

	public String getFtpUser() {
		return this.ftpUser;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	public String getFtpPort() {
		return this.ftpPort;
	}

	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpPwd() {
		return this.ftpPwd;
	}

	public void setFtpPwd(String ftpPwd) {
		this.ftpPwd = ftpPwd;
	}

	public String getFtpPath() {
		return this.ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}

	public String getLocalPath() {
		return this.localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getWebserviceWSDL() {
		return this.webserviceWSDL;
	}

	public void setWebserviceWSDL(String webserviceWSDL) {
		this.webserviceWSDL = webserviceWSDL;
	}

	public String getWebserviceTargetNamespace() {
		return this.webserviceTargetNamespace;
	}

	public void setWebserviceTargetNamespace(String webserviceTargetNamespace) {
		this.webserviceTargetNamespace = webserviceTargetNamespace;
	}

	public String getWebserviceMethod() {
		return this.webserviceMethod;
	}

	public void setWebserviceMethod(String webserviceMethod) {
		this.webserviceMethod = webserviceMethod;
	}

	public String getWebserviceArgs() {
		return this.webserviceArgs;
	}

	public void setWebserviceArgs(String webserviceArgs) {
		this.webserviceArgs = webserviceArgs;
	}

	public String getDescTxt() {
		return this.descTxt;
	}

	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	public Integer getShowInPage() {
		return this.showInPage;
	}

	public void setShowInPage(Integer showInPage) {
		this.showInPage = showInPage;
	}

	public Integer getIsNeedXml() {
		return this.isNeedXml;
	}

	public void setIsNeedXml(Integer isNeedXml) {
		this.isNeedXml = isNeedXml;
	}

	public Integer getIsNeedDes() {
		return this.isNeedDes;
	}

	public void setIsNeedDes(Integer isNeedDes) {
		this.isNeedDes = isNeedDes;
	}

	public String getDesKey() {
		return this.desKey;
	}

	public void setDesKey(String desKey) {
		this.desKey = desKey;
	}

	public Integer getIsNeedCycle() {
		return this.isNeedCycle;
	}

	public void setIsNeedCycle(Integer isNeedCycle) {
		this.isNeedCycle = isNeedCycle;
	}

	public String getReqId() {
		return this.reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String toString() {
		return "CiSysInfo [sysId=" + this.sysId + ", sysName=" + this.sysName + ", ftpServerIp=" + this.ftpServerIp
				+ ", ftpUser=" + this.ftpUser + ", ftpPort=" + this.ftpPort + ", ftpPwd=" + this.ftpPwd + ", ftpPath="
				+ this.ftpPath + ", localPath=" + this.localPath + ", webserviceWSDL=" + this.webserviceWSDL
				+ ", webserviceTargetNamespace=" + this.webserviceTargetNamespace + ", webserviceMethod="
				+ this.webserviceMethod + ", webserviceArgs=" + this.webserviceArgs + ", showInPage=" + this.showInPage
				+ ", descTxt=" + this.descTxt + ", isNeedXml=" + this.isNeedXml + ", isNeedDes=" + this.isNeedDes
				+ ", desKey=" + this.desKey + ", isNeedCycle=" + this.isNeedCycle + ", actionId=" + this.actionId
				+ ", functionId=" + this.functionId + ", pushClassName=" + this.pushClassName + "]";
	}
}
