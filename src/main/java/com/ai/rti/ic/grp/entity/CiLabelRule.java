 package  com.ai.rti.ic.grp.entity;
 
 import java.math.BigDecimal;
 
 
 
 public class CiLabelRule
 {
   private String ruleId;
   private String parentId;
   private String customId;
   private String calcuElement;
   private BigDecimal minVal;
   private BigDecimal maxVal;
   private Integer sortNum;
   private Short customType;
   private Short elementType;
   private Short labelFlag;
   private String attrVal;
   private String startTime;
   private String endTime;
   private String contiueMinVal;
   private String contiueMaxVal;
   private String leftZoneSign;
   private String rightZoneSign;
   private String exactValue;
   private String darkValue;
   private String tableName;
   private Short isNeedOffset;
   
   public String getRuleId() {
     return this.ruleId;
   }
   
   public void setRuleId(String ruleId) {
     this.ruleId = (ruleId == null) ? null : ruleId.trim();
   }
   
   public String getParentId() {
     return this.parentId;
   }
   
   public void setParentId(String parentId) {
     this.parentId = (parentId == null) ? null : parentId.trim();
   }
   
   public String getCustomId() {
     return this.customId;
   }
   
   public void setCustomId(String customId) {
     this.customId = (customId == null) ? null : customId.trim();
   }
   
   public String getCalcuElement() {
     return this.calcuElement;
   }
   
   public void setCalcuElement(String calcuElement) {
     this.calcuElement = (calcuElement == null) ? null : calcuElement.trim();
   }
   
   public BigDecimal getMinVal() {
     return this.minVal;
   }
   
   public void setMinVal(BigDecimal minVal) {
     this.minVal = minVal;
   }
   
   public BigDecimal getMaxVal() {
     return this.maxVal;
   }
   
   public void setMaxVal(BigDecimal maxVal) {
     this.maxVal = maxVal;
   }
   
   public Integer getSortNum() {
     return this.sortNum;
   }
   
   public void setSortNum(Integer sortNum) {
     this.sortNum = sortNum;
   }
   
   public Short getCustomType() {
     return this.customType;
   }
   
   public void setCustomType(Short customType) {
     this.customType = customType;
   }
   
   public Short getElementType() {
     return this.elementType;
   }
   
   public void setElementType(Short elementType) {
     this.elementType = elementType;
   }
   
   public Short getLabelFlag() {
     return this.labelFlag;
   }
   
   public void setLabelFlag(Short labelFlag) {
     this.labelFlag = labelFlag;
   }
   
   public String getAttrVal() {
     return this.attrVal;
   }
   
   public void setAttrVal(String attrVal) {
     this.attrVal = (attrVal == null) ? null : attrVal.trim();
   }
   
   public String getStartTime() {
     return this.startTime;
   }
   
   public void setStartTime(String startTime) {
     this.startTime = (startTime == null) ? null : startTime.trim();
   }
   
   public String getEndTime() {
     return this.endTime;
   }
   
   public void setEndTime(String endTime) {
     this.endTime = (endTime == null) ? null : endTime.trim();
   }
   
   public String getContiueMinVal() {
     return this.contiueMinVal;
   }
   
   public void setContiueMinVal(String contiueMinVal) {
     this.contiueMinVal = (contiueMinVal == null) ? null : contiueMinVal.trim();
   }
   
   public String getContiueMaxVal() {
     return this.contiueMaxVal;
   }
   
   public void setContiueMaxVal(String contiueMaxVal) {
     this.contiueMaxVal = (contiueMaxVal == null) ? null : contiueMaxVal.trim();
   }
   
   public String getLeftZoneSign() {
     return this.leftZoneSign;
   }
   
   public void setLeftZoneSign(String leftZoneSign) {
     this.leftZoneSign = (leftZoneSign == null) ? null : leftZoneSign.trim();
   }
   
   public String getRightZoneSign() {
     return this.rightZoneSign;
   }
   
   public void setRightZoneSign(String rightZoneSign) {
     this.rightZoneSign = (rightZoneSign == null) ? null : rightZoneSign.trim();
   }
   
   public String getExactValue() {
     return this.exactValue;
   }
   
   public void setExactValue(String exactValue) {
     this.exactValue = (exactValue == null) ? null : exactValue.trim();
   }
   
   public String getDarkValue() {
     return this.darkValue;
   }
   
   public void setDarkValue(String darkValue) {
     this.darkValue = (darkValue == null) ? null : darkValue.trim();
   }
   
   public String getTableName() {
     return this.tableName;
   }
   
   public void setTableName(String tableName) {
     this.tableName = (tableName == null) ? null : tableName.trim();
   }
   
   public Short getIsNeedOffset() {
     return this.isNeedOffset;
   }
   
   public void setIsNeedOffset(Short isNeedOffset) {
     this.isNeedOffset = isNeedOffset;
   }
 }

