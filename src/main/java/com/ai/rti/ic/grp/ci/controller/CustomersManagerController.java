package com.ai.rti.ic.grp.ci.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.rti.ic.grp.ci.service.ICustomersManagerService;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 客户群管理
 * 
 * @author TTH
 *
 */
@Controller
@RequestMapping({"/customer"})
public class CustomersManagerController extends BaseController {
	   private static final transient Logger logger = LoggerFactory.getLogger(CustomersManagerController.class);
	   
		@Autowired
		private ICustomersManagerService customersService;
		
	    @ApiOperation("客户群推送")
	    @RequestMapping(value = {"/push"}, method = {RequestMethod.GET})
	    @ResponseBody
		public void pushCustomerGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
			JSONObject result = new JSONObject();
			try {
				request.setCharacterEncoding("UTF-8");
				String userId = request.getParameter("userId");
				String customGroupId = request.getParameter("customGroupId");
				this.logger.info("userId:" + userId + "推送客户群:" + customGroupId);
				if (StringUtils.isEmpty(customGroupId) || StringUtils.isEmpty(userId)) {
					throw new RuntimeException("入参不全!");
				}
				this.logger.info("推送客户群" + customGroupId + "开始!");
				this.customersService.pushCustomerGroup(customGroupId, userId);
				result.put("code", Integer.valueOf(1000));
				result.put("msg", "客户群推送任务创建成功!");
			} catch (IOException e) {
				this.logger.error("客户群推送失败!", e);
				result.put("code", Integer.valueOf(3000));
				result.put("msg", "客户群推送任务创建失败!");
				result.put("error", e);
			}
			printOutMsg(result);
		}
}
