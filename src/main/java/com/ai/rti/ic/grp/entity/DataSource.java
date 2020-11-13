 package  com.ai.rti.ic.grp.entity;
 
 import java.io.Serializable;
 import java.util.Date;
 
 
 
 
 public class DataSource
   implements Serializable
 {
   private static final long serialVersionUID = 457889258394975851L;
   private Long dataSourceId;
   private String dataSourceName;
   private String dataSourceType;
   private String dataSourceIp;
   private Integer dataSourcePort;
   private String schemaValue;
   private String userName;
   private String password;
   private String cityId;
   private Integer connectionState;
   private String statusCd;
   private String createStaff;
   private String updateStaff;
   private Date createDate;
   private Date updateDate;
   private Date statusDate;
   private String remark;
   private String driverClass;
   private String urlTmp;
   
   public Long getDataSourceId() {
     return this.dataSourceId;
   }
   
   public void setDataSourceId(Long dataSourceId) {
     this.dataSourceId = dataSourceId;
   }
   
   public String getDataSourceName() {
     return this.dataSourceName;
   }
   
   public void setDataSourceName(String dataSourceName) {
     this.dataSourceName = dataSourceName;
   }
   
   public String getDataSourceType() {
     return this.dataSourceType;
   }
   
   public void setDataSourceType(String dataSourceType) {
     this.dataSourceType = dataSourceType;
   }
   
   public String getDataSourceIp() {
     return this.dataSourceIp;
   }
   
   public void setDataSourceIp(String dataSourceIp) {
     this.dataSourceIp = dataSourceIp;
   }
   
   public Integer getDataSourcePort() {
     return this.dataSourcePort;
   }
   
   public void setDataSourcePort(Integer dataSourcePort) {
     this.dataSourcePort = dataSourcePort;
   }
   
   public String getSchemaValue() {
     return this.schemaValue;
   }
   
   public void setSchemaValue(String schemaValue) {
     this.schemaValue = schemaValue;
   }
   
   public String getUserName() {
     return this.userName;
   }
   
   public void setUserName(String userName) {
     this.userName = userName;
   }
   
   public String getPassword() {
     return this.password;
   }
   
   public void setPassword(String password) {
     this.password = password;
   }
   
   public String getCityId() {
     return this.cityId;
   }
   
   public void setCityId(String cityId) {
     this.cityId = cityId;
   }
 
   
   public String getStatusCd() {
     return this.statusCd;
   }
   
   public void setStatusCd(String statusCd) {
     this.statusCd = statusCd;
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
   
   public Date getUpdateDate() {
     return this.updateDate;
   }
   
   public void setUpdateDate(Date updateDate) {
     this.updateDate = updateDate;
   }
   
   public Date getStatusDate() {
     return this.statusDate;
   }
   
   public void setStatusDate(Date statusDate) {
     this.statusDate = statusDate;
   }
   
   public String getRemark() {
     return this.remark;
   }
   
   public void setRemark(String remark) {
     this.remark = remark;
   }
   
   public Integer getConnectionState() {
     return this.connectionState;
   }
   
   public void setConnectionState(Integer connectionState) {
     this.connectionState = connectionState;
   }
   
   public String getDriverClass() {
     return this.driverClass;
   }
   
   public void setDriverClass(String driverClass) {
     this.driverClass = driverClass;
   }
   
   public String getUrlTmp() {
     return this.urlTmp;
   }
   
   public void setUrlTmp(String urlTmp) {
     this.urlTmp = urlTmp;
   }
   
   public String toString() {
     return "DataSource{dataSourceId=" + this.dataSourceId + ", dataSourceName='" + this.dataSourceName + '\'' + ", dataSourceType='" + this.dataSourceType + '\'' + ", dataSourceIp='" + this.dataSourceIp + '\'' + ", dataSourcePort=" + this.dataSourcePort + ", schemaValue='" + this.schemaValue + '\'' + ", userName='" + this.userName + '\'' + ", password='" + this.password + '\'' + ", cityId='" + this.cityId + '\'' + ", connectionState='" + this.connectionState + '\'' + ", statusCd='" + this.statusCd + '\'' + ", createStaff='" + this.createStaff + '\'' + ", updateStaff='" + this.updateStaff + '\'' + ", createDate=" + this.createDate + ", updateDate=" + this.updateDate + ", statusDate=" + this.statusDate + ", remark='" + this.remark + '\'' + '}';
   }
 }

