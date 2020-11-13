 package  com.ai.rti.ic.grp.entity;
 
 import java.util.Date;
 
 
 
 
 
 public class CiCustomSubgroupListExeInfo
 {
   private String exeInfoId;
   private String subgroupId;
   private String expression;
   private String excpInfo;
   private Date startTime;
   private Date endTime;
   
   public String getExeInfoId() {
     return this.exeInfoId;
   }
   
   public void setExeInfoId(String exeInfoId) {
     this.exeInfoId = (exeInfoId == null) ? null : exeInfoId.trim();
   }
   
   public String getSubgroupId() {
     return this.subgroupId;
   }
   
   public void setSubgroupId(String subgroupId) {
     this.subgroupId = (subgroupId == null) ? null : subgroupId.trim();
   }
   
   public String getExpression() {
     return this.expression;
   }
   
   public void setExpression(String expression) {
     this.expression = (expression == null) ? null : expression.trim();
   }
   
   public String getExcpInfo() {
     return this.excpInfo;
   }
   
   public void setExcpInfo(String excpInfo) {
     this.excpInfo = (excpInfo == null) ? null : excpInfo.trim();
   }
   
   public Date getStartTime() {
     return this.startTime;
   }
   
   public void setStartTime(Date startTime) {
     this.startTime = startTime;
   }
   
   public Date getEndTime() {
     return this.endTime;
   }
   
   public void setEndTime(Date endTime) {
     this.endTime = endTime;
   }
 }

