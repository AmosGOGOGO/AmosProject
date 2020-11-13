 package com.ai.rti.ic.grp.dao.base;
 
 import org.springframework.boot.context.properties.ConfigurationProperties;
 import org.springframework.stereotype.Component;
 
 
 @Component
 @ConfigurationProperties(prefix = "ci.back.coc")
 public class BackDataSourcesProperties
 {
   private String url;
   private String driver;
   private String username;
   private String password;
   
   public String getUrl() {
     return this.url;
   }
 
   
   public void setUrl(String url) {
     this.url = url;
   }
 
   
   public String getDriver() {
     return this.driver;
   }
 
   
   public void setDriver(String driver) {
     this.driver = driver;
   }
 
   
   public String getUsername() {
     return this.username;
   }
 
   
   public void setUsername(String username) {
     this.username = username;
   }
 
   
   public String getPassword() {
     return this.password;
   }
 
   
   public void setPassword(String password) {
     this.password = password;
   }
 }


