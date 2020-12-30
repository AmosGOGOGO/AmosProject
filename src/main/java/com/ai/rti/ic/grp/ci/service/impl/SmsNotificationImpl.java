package com.ai.rti.ic.grp.ci.service.impl;

import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("SmsNotificationImpl")
public class SmsNotificationImpl {
	private static final transient Logger logger = LoggerFactory.getLogger(SmsNotificationImpl.class);
  
  public boolean onMessage(String msg, String phoneNum) {
    try {
      String topic = "SMS";
      String tmpId = "8";
      String systemId = "4000";
      String url = Config.getObject("JS_EXECUTE_SMS_URL");
      logger.info(phoneNum + " " + msg);
      JSONObject param = new JSONObject();
      param.put("mobile", phoneNum);
      param.put("topic", topic);
      param.put("tmpId", tmpId);
      param.put("systemId", systemId);
      param.put("message", msg);
      logger.info( param.toJSONString());
      String result = HttpClientUtil.doPost(url, param.toJSONString());
      logger.info( result);
      return true;
    } catch (Exception e) {
    	logger.error("", e);
      return false;
    } 
  }
}
