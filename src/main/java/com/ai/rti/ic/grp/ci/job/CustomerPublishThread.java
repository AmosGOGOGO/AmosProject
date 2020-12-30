package com.ai.rti.ic.grp.ci.job;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.HttpClientUtil;
import com.ai.rti.ic.grp.ci.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.ci.entity.CiCustomGroupPushCycle;
import com.ai.rti.ic.grp.ci.entity.CiCustomListInfo;
import com.ai.rti.ic.grp.ci.entity.CiCustomPushReq;
import com.ai.rti.ic.grp.ci.entity.CiSysInfo;
import com.ai.rti.ic.grp.ci.exception.CIServiceException;
import com.ai.rti.ic.grp.ci.service.ICiSysInfoService;
import com.ai.rti.ic.grp.ci.service.ICustomerPush;
import com.ai.rti.ic.grp.ci.service.ICustomersManagerService;
import com.ai.rti.ic.grp.utils.SpringContextUtil;

@Service
@Scope("prototype")
public class CustomerPublishThread extends Thread {
	private static Logger log = Logger.getLogger(CustomerPublishThread.class);

	@Autowired
	private ICiSysInfoService ciSysInfoService;
	@Autowired
	private ICustomersManagerService customersManagerService;
	
	private CiCustomListInfo ciCustomListInfo;
	private CiCustomGroupInfo ciCustomGroupInfo;
	private CiSysInfo sysInfo;
	private CiCustomPushReq ciCustomPushReq;
	private String fileName;
	private String pushCycle;
	private List<CiCustomPushReq> customPushReqList;
	private String pushFailReason;

	public void initParamter(List<CiCustomPushReq> customPushReqList, String pushCycle) {
		try {
			this.customPushReqList = customPushReqList;
			this.pushCycle = pushCycle;
		} catch (Exception e) {
			log.error("init CustomerPublishThread error", e);
			throw new CIServiceException("init CustomerPublishThread error");
		}
	}

	public void run() {
		log.info("Enter CustomerPublishThread.run()");
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			log.error("InterruptedException", e);
		}
		long start = System.currentTimeMillis();
		for (CiCustomPushReq customPushReq : this.customPushReqList) {
			this.fileName = "COC_" + customPushReq.getUserId() + "_" + customPushReq.getReqId().replace("COC", "");
			this.ciCustomListInfo = this.customersManagerService.queryCiCustomListInfoById(customPushReq.getListTableName());
			this.ciCustomGroupInfo = this.customersManagerService.queryCiCustomGroupInfo(this.ciCustomListInfo.getCustomGroupId());
			this.ciCustomPushReq = customPushReq;
			if (this.ciCustomListInfo == null || this.ciCustomListInfo.getDataStatus().intValue() != 3) {
				log.error("清单状态不正确");

				break;
			}

			List<String> sysIds = customPushReq.getSysIds();

			if (sysIds != null) {
				for (String sysId : sysIds) {
					try {
						customPublish(sysId, customPushReq.getUserId());
					} catch (Exception e) {
						log.error("推送失败", e);
						delNormal(0);

						return;
					}
				}

				for (int i = 0; i < sysIds.size(); i++) {

//					CiCustomGroupPushCycleId cycleId = new CiCustomGroupPushCycleId(this.ciCustomGroupInfo.getCustomGroupId(), sysIds.get(i));
					CiCustomGroupPushCycle pushCycleObj = new CiCustomGroupPushCycle(this.ciCustomGroupInfo.getCustomGroupId(), sysIds.get(i), Integer.valueOf(1));
					pushCycleObj.setPushCycle(Integer.valueOf(this.pushCycle));
					pushCycleObj.setIsPushed(Integer.valueOf(1));
					pushCycleObj.setModifyTime(new Date());
					this.customersManagerService.saveCiCustomGroupPushCycle(pushCycleObj);
				}
			}
		}

		log.debug("cost:" + (System.currentTimeMillis() - start) + "ms");
		log.info("Exist CustomerPublishThread.run()");
	}

	private void customPublish(String sysId, String pushUserId) {
		this.sysInfo = this.ciSysInfoService.queryById(sysId);
		this.sysInfo.setReqId(this.ciCustomPushReq.getReqId());
		if (this.sysInfo == null) {
			log.error("系统信息不存在");
			return;
		}
		this.ciCustomPushReq.setSysId(sysId);

		this.ciCustomPushReq.setReqId(null);
		this.customersManagerService.saveCiCustomPushReq(this.ciCustomPushReq);
		this.ciCustomPushReq.setStartTime(new Date());
		delNormal(2);
		boolean flag = false;
//		Long duplicateNum = this.ciCustomListInfo.getDuplicateNum();

		if (this.sysInfo.getSysId().equals(Config.getObject("MCD_SYS_ID"))) {

//			flag = publish2McdByJdbc();
		} else if (this.sysInfo.getSysId().equals(Config.getObject("OLD_CI_SYS_ID"))) {

//			flag = publishToOldCi();
		} else if (StringUtils.isNotEmpty(this.sysInfo.getPushClassName())) {
			try {
				ICustomerPush ipush = (ICustomerPush) SpringContextUtil.getBean(this.sysInfo.getPushClassName());
				flag = ipush.push(this.ciCustomGroupInfo, this.ciCustomListInfo, this.sysInfo);
			} catch (Exception e) {
				log.error("个性化推送失败：", e);
				flag = false;
				delException("个性化推送失败：", e);
			}
		} else {
//			String needTxtIds = Config.getObject("FTP_NEED_TXT_SYS_IDS");
//			if (StringUtils.isNotEmpty(needTxtIds)) {
//				String[] sysIdsArray = needTxtIds.split(",");
//				List<String> ids = Arrays.asList(sysIdsArray);
//				if (ids != null && ids.size() > 0 && ids.contains(this.sysInfo.getSysId())) {
//					flag = publishTxtByFtp();
//				}
//			} else {
//				flag = publishByFtp(pushUserId, this.ciCustomPushReq);
//			}
		}

		if (flag) {
			this.ciCustomPushReq.setEndTime(new Date());
			delNormal(3);
		} else {
			delNormal(0);
		}

		if (1 == this.sysInfo.getShowInPage().intValue()) {
			
			/*  调用ci系统进行通知    */
			sendNotice(ciCustomPushReq.getReqId());
		}
	}

	private void delException(String msg, Exception e) {
		this.ciCustomPushReq.setStatus(Integer.valueOf(0));
		String expctionInfo = "";
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			expctionInfo = sw.toString();
		} catch (Exception e2) {
			log.error(" get message error");
		}
		expctionInfo = msg + expctionInfo;
		if (expctionInfo.length() > 2048) {
			expctionInfo = expctionInfo.substring(0, 1024);
		}
		this.ciCustomPushReq.setExeInfo(expctionInfo);
		this.customersManagerService.saveCiCustomPushReq(this.ciCustomPushReq);
	}

	private void delNormal(int status) {
		this.ciCustomPushReq.setStatus(Integer.valueOf(status));
		this.customersManagerService.saveCiCustomPushReq(this.ciCustomPushReq);
	}


	public void sendNotice(String reqId) {
		try {
			
			
	       Map<String, String> param = new HashMap<>(8);
	       param.put("reqId", reqId);
	       String url = Config.getObject("SEND_NOTICE_URL");
	       log.info("调用智慧营销通知接口：" + url);
		   log.info("调用智慧营销通知接口入参：" + param);
	       String resp = HttpClientUtil.postMethod(url, param);
	       log.info("客户群推送完成发送通知给aibici结果：" + resp);
			
		} catch (CIServiceException e) {
			log.error("send notice error", (Throwable) e);
		}
	}



	public boolean isExistsSame() {
		CiCustomPushReq queryParam = new CiCustomPushReq();
		queryParam.setUserId(this.ciCustomGroupInfo.getCreateUserId());
		queryParam.setSysId(this.ciCustomPushReq.getSysId());
		queryParam.setListTableName(this.ciCustomListInfo.getListTableName());
		queryParam.setStatus(Integer.valueOf(3));

		List<CiCustomPushReq> pushreq = this.customersManagerService.queryCiCustomPushReq(queryParam);
		if (pushreq != null && pushreq.size() > 0) {
			return true;
		}
		return false;
	}



	public String getPushFailReason() {
		return this.pushFailReason;
	}

	public void setPushFailReason(String pushFailReason) {
		this.pushFailReason = pushFailReason;
	}
}
