 package  com.ai.rti.ic.grp.entity;
 
 import java.io.Serializable;
 import java.util.Date;
 
 
 
 
 public class TarGrpImportTask implements Serializable
 {
   private Long taskId;
   private String tarGrpId;
   private String tarGrpName;
   private String tarGrpDesc;
   private Integer tarGrpType;
   private Integer tarGrpDataType;
   private Long dataSourceId;
   private Integer updateCycle;
   private String createTime;
   private Integer isPrivate;
   private Integer tarGrpNetType;
   private String tarGrpEntendCol;
   private String tarGrpKey;
   private String tableRelation;
   private String fileName;
   private String rulesJson;
   private String fileUrl;
   private String dataDate;
   private Integer status;
   private String cityId;
   private String statusCd;
   private String errorsLog;
   private String createStaff;
   private String updateStaff;
   private Date createDate;
   private Date statusDate;
   private Date updateDate;
   private String customGroupClassifyId;
   private String isDispatch;
   private String dispatchTypeCode;
   private Integer isTactic = Integer.valueOf(0);
 
   
   public Long getTaskId() {
     return this.taskId;
   }
   
   public void setTaskId(Long taskId) {
     this.taskId = taskId;
   }
   
   public String getTarGrpId() {
     return this.tarGrpId;
   }
   
   public void setTarGrpId(String tarGrpId) {
     this.tarGrpId = (tarGrpId == null) ? null : tarGrpId.trim();
   }
   
   public String getTarGrpName() {
     return this.tarGrpName;
   }
   
   public void setTarGrpName(String tarGrpName) {
     this.tarGrpName = (tarGrpName == null) ? null : tarGrpName.trim();
   }
   
   public String getTarGrpDesc() {
     return this.tarGrpDesc;
   }
   
   public void setTarGrpDesc(String tarGrpDesc) {
     this.tarGrpDesc = (tarGrpDesc == null) ? null : tarGrpDesc.trim();
   }
   
   public Integer getTarGrpType() {
     return this.tarGrpType;
   }
   
   public void setTarGrpType(Integer tarGrpType) {
     this.tarGrpType = tarGrpType;
   }
   
   public Long getDataSourceId() {
     return this.dataSourceId;
   }
   
   public void setDataSourceId(Long dataSourceId) {
     this.dataSourceId = dataSourceId;
   }
   
   public Integer getUpdateCycle() {
     return this.updateCycle;
   }
   
   public void setUpdateCycle(Integer updateCycle) {
     this.updateCycle = updateCycle;
   }
   
   public String getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(String createTime) {
     this.createTime = createTime;
   }
   
   public Integer getIsPrivate() {
     return this.isPrivate;
   }
   
   public void setIsPrivate(Integer isPrivate) {
     this.isPrivate = isPrivate;
   }
   
   public Integer getTarGrpNetType() {
     return this.tarGrpNetType;
   }
   
   public void setTarGrpNetType(Integer tarGrpNetType) {
     this.tarGrpNetType = tarGrpNetType;
   }
   
   public String getTarGrpEntendCol() {
     return this.tarGrpEntendCol;
   }
   
   public void setTarGrpEntendCol(String tarGrpEntendCol) {
     this.tarGrpEntendCol = (tarGrpEntendCol == null) ? null : tarGrpEntendCol.trim();
   }
   
   public String getTarGrpKey() {
     return this.tarGrpKey;
   }
   
   public void setTarGrpKey(String tarGrpKey) {
     this.tarGrpKey = (tarGrpKey == null) ? null : tarGrpKey.trim();
   }
   
   public String getTableRelation() {
     return this.tableRelation;
   }
   
   public void setTableRelation(String tableRelation) {
     this.tableRelation = (tableRelation == null) ? null : tableRelation.trim();
   }
 
   
   public String getRulesJson() {
     return this.rulesJson;
   }
   
   public void setRulesJson(String rulesJson) {
     this.rulesJson = rulesJson;
   }
   
   public String getFileName() {
     return this.fileName;
   }
   
   public void setFileName(String fileName) {
     this.fileName = (fileName == null) ? null : fileName.trim();
   }
   
   public String getFileUrl() {
     return this.fileUrl;
   }
   
   public void setFileUrl(String fileUrl) {
     this.fileUrl = (fileUrl == null) ? null : fileUrl.trim();
   }
   
   public String getDataDate() {
     return this.dataDate;
   }
   
   public void setDataDate(String dataDate) {
     this.dataDate = (dataDate == null) ? null : dataDate.trim();
   }
   
   public Integer getStatus() {
     return this.status;
   }
   
   public void setStatus(Integer status) {
     this.status = status;
   }
   
   public String getCityId() {
     return this.cityId;
   }
   
   public void setCityId(String cityId) {
     this.cityId = (cityId == null) ? null : cityId.trim();
   }
   
   public String getStatusCd() {
     return this.statusCd;
   }
   
   public void setStatusCd(String statusCd) {
     this.statusCd = (statusCd == null) ? null : statusCd.trim();
   }
   
   public String getErrorsLog() {
     return this.errorsLog;
   }
   
   public void setErrorsLog(String errorsLog) {
     this.errorsLog = (errorsLog == null) ? null : errorsLog.trim();
   }
   
   public String getCreateStaff() {
     return this.createStaff;
   }
   
   public void setCreateStaff(String createStaff) {
     this.createStaff = createStaff;
   }
   
   public String getUpdateStaff() {
     return this.updateStaff;
   }
   
   public void setUpdateStaff(String updateStaff) {
     this.updateStaff = updateStaff;
   }
   
   public Date getCreateDate() {
     return this.createDate;
   }
   
   public void setCreateDate(Date createDate) {
     this.createDate = createDate;
   }
   
   public Date getStatusDate() {
     return this.statusDate;
   }
   
   public void setStatusDate(Date statusDate) {
     this.statusDate = statusDate;
   }
   
   public Date getUpdateDate() {
     return this.updateDate;
   }
   
   public void setUpdateDate(Date updateDate) {
     this.updateDate = updateDate;
   }
   
   public Integer getTarGrpDataType() {
     return this.tarGrpDataType;
   }
   
   public void setTarGrpDataType(Integer tarGrpDataType) {
     this.tarGrpDataType = tarGrpDataType;
   }
   
   public String getCustomGroupClassifyId() {
     return this.customGroupClassifyId;
   }
   
   public void setCustomGroupClassifyId(String customGroupClassifyId) {
     this.customGroupClassifyId = customGroupClassifyId;
   }
   
   public Integer getIsTactic() {
     return this.isTactic;
   }
   
   public void setIsTactic(Integer isTactic) {
     this.isTactic = isTactic;
   }
 
 
   
   public String getIsDispatch() {
     return this.isDispatch;
   }
   
   public void setIsDispatch(String isDispatch) {
     this.isDispatch = isDispatch;
   }
   
   public String getDispatchTypeCode() {
     return this.dispatchTypeCode;
   }
   
   public void setDispatchTypeCode(String dispatchTypeCode) {
     this.dispatchTypeCode = dispatchTypeCode;
   }
 
 
   
   public String toString() {
     return "TarGrpImportTask{taskId=" + this.taskId + ", tarGrpId='" + this.tarGrpId + '\'' + ", tarGrpName='" + this.tarGrpName + '\'' + ", tarGrpDesc='" + this.tarGrpDesc + '\'' + ", tarGrpType=" + this.tarGrpType + ", tarGrpDataType=" + this.tarGrpDataType + ", dataSourceId=" + this.dataSourceId + ", updateCycle=" + this.updateCycle + ", createTime='" + this.createTime + '\'' + ", isPrivate=" + this.isPrivate + ", tarGrpNetType=" + this.tarGrpNetType + ", tarGrpEntendCol='" + this.tarGrpEntendCol + '\'' + ", tarGrpKey='" + this.tarGrpKey + '\'' + ", tableRelation='" + this.tableRelation + '\'' + ", fileName='" + this.fileName + '\'' + ", rulesJson='" + this.rulesJson + '\'' + ", fileUrl='" + this.fileUrl + '\'' + ", dataDate='" + this.dataDate + '\'' + ", status=" + this.status + ", cityId='" + this.cityId + '\'' + ", statusCd='" + this.statusCd + '\'' + ", errorsLog='" + this.errorsLog + '\'' + ", createStaff='" + this.createStaff + '\'' + ", updateStaff='" + this.updateStaff + '\'' + ", createDate=" + this.createDate + ", statusDate=" + this.statusDate + ", updateDate=" + this.updateDate + ", customGroupClassifyId='" + this.customGroupClassifyId + '\'' + ", isDispatch='" + this.isDispatch + '\'' + ", dispatchTypeCode='" + this.dispatchTypeCode + '\'' + ", isTactic=" + this.isTactic + '}';
   }
 }

