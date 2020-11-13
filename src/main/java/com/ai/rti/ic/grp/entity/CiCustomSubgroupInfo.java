 package  com.ai.rti.ic.grp.entity;
 
 import java.io.Serializable;
 import java.util.Date;
 
 
 
 public class CiCustomSubgroupInfo
   implements Serializable, Cloneable
 {
   private static final long serialVersionUID = -3845956392521281164L;
   private String subgroupListId;
   private String dataDate;
   private String subGroupId;
   private Long customNum;
   private Long incrementNum;
   private Long decrementNum;
   private Integer dataStatus;
   private Date dataTime;
   private String excpInfo;
   private Integer fileCreateStatus;
   private Integer fileApproveStatus;
   private String monthLabelDate;
   private String dayLabelDate;
   private Long duplicateNum;
   private Integer listMaxNum;
   
   public String getSubgroupListId() {
     return this.subgroupListId;
   }
   
   public void setSubgroupListId(String subgroupListId) {
     this.subgroupListId = subgroupListId;
   }
   
   public String getDataDate() {
     return this.dataDate;
   }
   
   public void setDataDate(String dataDate) {
     this.dataDate = dataDate;
   }
   
   public String getSubGroupId() {
     return this.subGroupId;
   }
   
   public void setSubGroupId(String subGroupId) {
     this.subGroupId = subGroupId;
   }
   
   public Long getCustomNum() {
     return this.customNum;
   }
   
   public void setCustomNum(Long customNum) {
     this.customNum = customNum;
   }
   
   public Long getIncrementNum() {
     return this.incrementNum;
   }
   
   public void setIncrementNum(Long incrementNum) {
     this.incrementNum = incrementNum;
   }
   
   public Long getDecrementNum() {
     return this.decrementNum;
   }
   
   public void setDecrementNum(Long decrementNum) {
     this.decrementNum = decrementNum;
   }
   
   public Integer getDataStatus() {
     return this.dataStatus;
   }
   
   public void setDataStatus(Integer dataStatus) {
     this.dataStatus = dataStatus;
   }
   
   public Date getDataTime() {
     return this.dataTime;
   }
   
   public void setDataTime(Date dataTime) {
     this.dataTime = dataTime;
   }
   
   public String getExcpInfo() {
     return this.excpInfo;
   }
   
   public void setExcpInfo(String excpInfo) {
     this.excpInfo = excpInfo;
   }
   
   public Integer getFileCreateStatus() {
     return this.fileCreateStatus;
   }
   
   public void setFileCreateStatus(Integer fileCreateStatus) {
     this.fileCreateStatus = fileCreateStatus;
   }
   
   public Integer getFileApproveStatus() {
     return this.fileApproveStatus;
   }
   
   public void setFileApproveStatus(Integer fileApproveStatus) {
     this.fileApproveStatus = fileApproveStatus;
   }
   
   public String getMonthLabelDate() {
     return this.monthLabelDate;
   }
   
   public void setMonthLabelDate(String monthLabelDate) {
     this.monthLabelDate = monthLabelDate;
   }
   
   public String getDayLabelDate() {
     return this.dayLabelDate;
   }
   
   public void setDayLabelDate(String dayLabelDate) {
     this.dayLabelDate = dayLabelDate;
   }
   
   public Long getDuplicateNum() {
     return this.duplicateNum;
   }
   
   public void setDuplicateNum(Long duplicateNum) {
     this.duplicateNum = duplicateNum;
   }
   
   public Integer getListMaxNum() {
     return this.listMaxNum;
   }
   
   public void setListMaxNum(Integer listMaxNum) {
     this.listMaxNum = listMaxNum;
   }
 }

