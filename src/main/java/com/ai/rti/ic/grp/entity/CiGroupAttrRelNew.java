 package  com.ai.rti.ic.grp.entity;
 
 import java.io.Serializable;
 import java.util.Date;
 import java.util.Map;
 
 
 public class CiGroupAttrRelNew
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private String customGroupId;
   private String attrCol;
   private Date modifyTime;
   private String attrColName;
   private String attrColType;
   private int attrSource;
   private String labelOrCustomId;
   private String labelOrCustomColumn;
   private int isVerticalAttr;
   private Integer status;
   private String listTableName;
   private String attrVal;
   private String tableName;
   private String sortType;
   private Integer sortNum;
   private String customName;
   private String customId;
   private String columnName;
   private String attrName;
   private String dataDateStr;
   private String colTypeCode;
   private Map<String, Object> attrRange;
   
   public String getAttrColName() {
     return this.attrColName;
   }
   
   public void setAttrColName(String attrColName) {
     this.attrColName = attrColName;
   }
   
   public String getAttrColType() {
     return this.attrColType;
   }
   
   public void setAttrColType(String attrColType) {
     this.attrColType = attrColType;
   }
   
   public String getCustomName() {
     return this.customName;
   }
   
   public void setCustomName(String customName) {
     this.customName = customName;
   }
   
   public String getLabelOrCustomId() {
     return this.labelOrCustomId;
   }
   
   public void setLabelOrCustomId(String labelOrCustomId) {
     this.labelOrCustomId = labelOrCustomId;
   }
   
   public String getCustomId() {
     return this.customId;
   }
   
   public void setCustomId(String customId) {
     this.customId = customId;
   }
   
   public String getColumnName() {
     return this.columnName;
   }
   
   public void setColumnName(String columnName) {
     this.columnName = columnName;
   }
   
   public String getLabelOrCustomColumn() {
     return this.labelOrCustomColumn;
   }
   
   public void setLabelOrCustomColumn(String labelOrCustomColumn) {
     this.labelOrCustomColumn = labelOrCustomColumn;
   }
   
   public int getAttrSource() {
     return this.attrSource;
   }
   
   public void setAttrSource(int attrSource) {
     this.attrSource = attrSource;
   }
   
   public int getIsVerticalAttr() {
     return this.isVerticalAttr;
   }
   
   public void setIsVerticalAttr(int isVerticalAttr) {
     this.isVerticalAttr = isVerticalAttr;
   }
   
   public Integer getStatus() {
     return this.status;
   }
   
   public void setStatus(Integer status) {
     this.status = status;
   }
   
   public String getAttrVal() {
     return this.attrVal;
   }
   
   public void setAttrVal(String attrVal) {
     this.attrVal = attrVal;
   }
 
   
   public String getTableName() {
     return this.tableName;
   }
   
   public void setTableName(String tableName) {
     this.tableName = tableName;
   }
   
   public String getAttrName() {
     return this.attrName;
   }
   
   public void setAttrName(String attrName) {
     this.attrName = attrName;
   }
   
   public String getSortType() {
     return this.sortType;
   }
   
   public void setSortType(String sortType) {
     this.sortType = sortType;
   }
   
   public Integer getSortNum() {
     return this.sortNum;
   }
   
   public void setSortNum(Integer sortNum) {
     this.sortNum = sortNum;
   }
   
   public String getDataDateStr() {
     return this.dataDateStr;
   }
   
   public void setDataDateStr(String dataDateStr) {
     this.dataDateStr = dataDateStr;
   }
   
   public String getColTypeCode() {
     return this.colTypeCode;
   }
   
   public void setColTypeCode(String colTypeCode) {
     this.colTypeCode = colTypeCode;
   }
   
   public Map<String, Object> getAttrRange() {
     return this.attrRange;
   }
   
   public void setAttrRange(Map<String, Object> attrRange) {
     this.attrRange = attrRange;
   }
   
   public String getCustomGroupId() {
     return this.customGroupId;
   }
   
   public void setCustomGroupId(String customGroupId) {
     this.customGroupId = customGroupId;
   }
   
   public String getAttrCol() {
     return this.attrCol;
   }
   
   public void setAttrCol(String attrCol) {
     this.attrCol = attrCol;
   }
   
   public Date getModifyTime() {
     return this.modifyTime;
   }
   
   public void setModifyTime(Date modifyTime) {
     this.modifyTime = modifyTime;
   }
   
   public String getListTableName() {
     return this.listTableName;
   }
   
   public void setListTableName(String listTableName) {
     this.listTableName = listTableName;
   }
 }

