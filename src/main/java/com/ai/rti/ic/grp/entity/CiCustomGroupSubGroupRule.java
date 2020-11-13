 package  com.ai.rti.ic.grp.entity;
 
 
 public class CiCustomGroupSubGroupRule
 {
   private String subGroupId;
   private String groupId;
   private String subGroupName;
   private String chnnlId;
   private String rule;
   private String luaExpression;
   private String remark;
   private String cityId;
   private Long customNum;
   private String activityId;
   private String sqlCondition;
   private String groupName;
   
   public String getActivityId() {
     return this.activityId;
   }
   public void setActivityId(String activityId) {
     this.activityId = activityId;
   }
   public String getGroupName() {
     return this.groupName;
   }
   public void setGroupName(String groupName) {
     this.groupName = groupName;
   }
   public Long getCustomNum() {
     return this.customNum;
   }
   public void setCustomNum(Long customNum) {
     this.customNum = customNum;
   }
   public String getSubGroupId() {
     return this.subGroupId;
   }
   public void setSubGroupId(String subGroupId) {
     this.subGroupId = subGroupId;
   }
   public String getGroupId() {
     return this.groupId;
   }
   public void setGroupId(String groupId) {
     this.groupId = groupId;
   }
   public String getSubGroupName() {
     return this.subGroupName;
   }
   public void setSubGroupName(String subGroupName) {
     this.subGroupName = subGroupName;
   }
   public String getRule() {
     return this.rule;
   }
   public void setRule(String rule) {
     this.rule = rule;
   }
   public String getLuaExpression() {
     return this.luaExpression;
   }
   public void setLuaExpression(String luaExpression) {
     this.luaExpression = luaExpression;
   }
   public String getRemark() {
     return this.remark;
   }
   public void setRemark(String remark) {
     this.remark = remark;
   }
   public String getChnnlId() {
     return this.chnnlId;
   }
   public void setChnnlId(String chnnlId) {
     this.chnnlId = chnnlId;
   }
   public String getCityId() {
     return this.cityId;
   }
   public void setCityId(String cityId) {
     this.cityId = cityId;
   }
   public String getSqlCondition() {
     return this.sqlCondition;
   }
   public void setSqlCondition(String sqlCondition) {
     this.sqlCondition = sqlCondition;
   }
 }

