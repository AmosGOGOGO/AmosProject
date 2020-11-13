 package com.ai.rti.ic.grp.entity;
 
 import java.io.Serializable;
 
 
 
 public class MarketManageSubDomain
   implements Serializable
 {
   private static final long serialVersionUID = 212732276795260400L;
   private String masterDataCode;
   private String masterDataName;
   private String codeValue;
   private String value;
   private String illustrate;
   private String remain;
   
   public String getMasterDataCode() {
     return this.masterDataCode;
   }
   
   public void setMasterDataCode(String masterDataCode) {
     this.masterDataCode = masterDataCode;
   }
   
   public String getMasterDataName() {
     return this.masterDataName;
   }
   
   public void setMasterDataName(String masterDataName) {
     this.masterDataName = masterDataName;
   }
   
   public String getCodeValue() {
     return this.codeValue;
   }
   
   public void setCodeValue(String codeValue) {
     this.codeValue = codeValue;
   }
   
   public String getValue() {
     return this.value;
   }
   
   public void setValue(String value) {
     this.value = value;
   }
   
   public String getIllustrate() {
     return this.illustrate;
   }
   
   public void setIllustrate(String illustrate) {
     this.illustrate = illustrate;
   }
   
   public String getRemain() {
     return this.remain;
   }
   
   public void setRemain(String remain) {
     this.remain = remain;
   }
 }

