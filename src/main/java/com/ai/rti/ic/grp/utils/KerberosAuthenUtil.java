 package com.ai.rti.ic.grp.utils;
 
 import java.io.IOException;
 import org.apache.hadoop.conf.Configuration;
 import org.apache.hadoop.security.UserGroupInformation;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 
 
 
 
 public class KerberosAuthenUtil
 {
   private static final Logger logger = LoggerFactory.getLogger(com.ai.rti.ic.grp.utils.KerberosAuthenUtil.class);
 
 
 
 
 
 
   
   public static boolean getKerberosAuth(String keyPath, String hadoopAuthType, String principal) {
     logger.info("Kerberos认证参数keyPath:{},hadoopAuthType:{},principal:{}", new Object[] { keyPath, hadoopAuthType, principal });
     try {
       Configuration conf = new Configuration();
       conf.set("hadoop.security.authentication", hadoopAuthType);
       conf.set("keytab.file", keyPath);
       conf.set("kerberos.principal", principal);
       UserGroupInformation.setConfiguration(conf);
       UserGroupInformation.loginUserFromKeytab(principal, keyPath);
       return true;
     } catch (IOException e) {
       logger.info("Kerberos认证失败", e);
       return false;
     } 
   }
 
 
 
   
   public static void main(String[] args) {
     getKerberosAuth("ss", "../sds/hive.keytab", "dd");
   }
 }

