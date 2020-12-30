 package com.ai.rti.ic.grp.ci.controller;
 
 import com.alibaba.fastjson.JSONObject;
 import java.beans.PropertyEditor;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.propertyeditors.CustomDateEditor;
 import org.springframework.web.bind.WebDataBinder;
 import org.springframework.web.bind.annotation.InitBinder;
 import org.springframework.web.bind.annotation.ModelAttribute;
 
 
 
 
 
 
 
 
 
 
 
 
 public abstract class BaseController
 {
   private static final transient Logger logger = LoggerFactory.getLogger(BaseController.class);
   protected HttpServletRequest request;
   protected HttpServletResponse response;
   protected HttpSession session;
   public int defEnd = 0;
   public int defSTART = 29;
   
   private static final String COC_USER_ID = "COC_USER_ID";
   
   private static final String COC_USER_CITY_ID = "COC_USER_CITY_ID";
 
   
   @ModelAttribute
   public void setReqAndResp(HttpServletRequest request, HttpServletResponse response) {
     this.request = request;
     this.response = response;
     this.session = request.getSession();
   }
   
   @InitBinder
   protected void initBinder(WebDataBinder binder) {
     binder.registerCustomEditor(Date.class, (PropertyEditor)new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
     
     binder.registerCustomEditor(Date.class, (PropertyEditor)new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
   }
 
 
 
 
 
   
   public void printOutMsg(JSONObject result) {
     try {
       this.response.setCharacterEncoding("UTF-8");
       this.response.setContentType("text/html;charset=utf-8");
       this.response.setHeader("Access-Control-Allow-Origin", "*");
       
       PrintWriter out = this.response.getWriter();
       out.print(result.toJSONString());
       logger.error("返回数据:" + result.toJSONString());
       out.flush();
       out.close();
     } catch (IOException e) {
       logger.error(e.toString());
     } 
   }
 
   
   protected String getUserId() {
     return (String)this.request.getSession().getAttribute("COC_USER_ID");
   }
   
   protected String getCityId() {
     return (String)this.request.getSession().getAttribute("COC_USER_CITY_ID");
   }
 }


