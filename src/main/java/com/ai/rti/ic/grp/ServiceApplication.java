 package com.ai.rti.ic.grp;
 
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.boot.web.servlet.ServletComponentScan;
 import org.springframework.transaction.annotation.EnableTransactionManagement;
 
 
 @SpringBootApplication
 @EnableTransactionManagement
 @ServletComponentScan
 public class ServiceApplication
 {
   public static void main(String[] args) {
     SpringApplication.run(com.ai.rti.ic.grp.ServiceApplication.class, args);
   }
 }

