 package  com.ai.rti.ic.grp.entity;
 
 import java.io.Serializable;
 
 
 public class DataSourceAuthConf
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Long dataSourceId;
   private String hdpSecAuth;
   private String keytabFile;
   private String kerberosPrincipal;
   
   public Long getDataSourceId() {
     return this.dataSourceId;
   }
   public void setDataSourceId(Long dataSourceId) {
     this.dataSourceId = dataSourceId;
   }
   public String getHdpSecAuth() {
     return this.hdpSecAuth;
   }
   public void setHdpSecAuth(String hdpSecAuth) {
     this.hdpSecAuth = hdpSecAuth;
   }
   public String getKeytabFile() {
     return this.keytabFile;
   }
   public void setKeytabFile(String keytabFile) {
     this.keytabFile = keytabFile;
   }
   public String getKerberosPrincipal() {
     return this.kerberosPrincipal;
   }
   public void setKerberosPrincipal(String kerberosPrincipal) {
     this.kerberosPrincipal = kerberosPrincipal;
   }
   
   public String toString() {
     return "DataSourceAuthConf [dataSourceId=" + this.dataSourceId + ", hdpSecAuth=" + this.hdpSecAuth + ", keytabFile=" + 
       this.keytabFile + ", kerberosPrincipal=" + this.kerberosPrincipal + "]";
   }
 }

