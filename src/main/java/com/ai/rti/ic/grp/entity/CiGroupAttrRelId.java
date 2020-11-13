 package  com.ai.rti.ic.grp.entity;
 
 import java.io.Serializable;
 import java.util.Date;
 
 
 public class CiGroupAttrRelId
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private String customGroupId;
   private String attrCol;
   private Date modifyTime;
   
   public CiGroupAttrRelId() {}
   
   public CiGroupAttrRelId(String customGroupId, String attrCol, Date modifyTime) {
     this.customGroupId = customGroupId;
     this.attrCol = attrCol;
     this.modifyTime = modifyTime;
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
   
   public boolean equals(Object other) {
     if (this == other)
       return true; 
     if (other == null)
       return false; 
     if (!(other instanceof com.ai.rti.ic.grp.entity.CiGroupAttrRelId))
       return false; 
     com.ai.rti.ic.grp.entity.CiGroupAttrRelId castOther = (com.ai.rti.ic.grp.entity.CiGroupAttrRelId)other;
     
     return ((getCustomGroupId() == castOther.getCustomGroupId() || (
       getCustomGroupId() != null && castOther
       .getCustomGroupId() != null && 
       getCustomGroupId().equals(castOther.getCustomGroupId()))) && (
       getAttrCol() == castOther.getAttrCol() || (
       getAttrCol() != null && castOther
       .getAttrCol() != null && getAttrCol()
       .equals(castOther.getAttrCol()))) && (
       getModifyTime() == castOther.getModifyTime() || (
       getModifyTime() != null && castOther
       .getModifyTime() != null && getModifyTime()
       .equals(castOther.getModifyTime()))));
   }
   
   public int hashCode() {
     int result = 17;
 
 
 
     
     result = 37 * result + ((getCustomGroupId() == null) ? 0 : getCustomGroupId().hashCode());
     
     result = 37 * result + ((getAttrCol() == null) ? 0 : getAttrCol().hashCode());
     return result;
   }
 }

