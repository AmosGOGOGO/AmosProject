package com.ai.rti.ic.grp.ci.service.impl;

import com.ai.rti.ic.grp.ci.dao.ICiCustomListInfoHDao;
import com.ai.rti.ic.grp.ci.dao.ICustomGroupInfoJDao;
import com.ai.rti.ic.grp.ci.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.ci.entity.CiCustomListInfo;
import com.ai.rti.ic.grp.ci.entity.CiGroupAttrRel;
import com.ai.rti.ic.grp.ci.entity.MarketManageSubDomain;
import com.ai.rti.ic.grp.ci.service.ICiCustomFileRelService;
import com.ai.rti.ic.grp.ci.service.ICiLoadCustomGroupFileJDao;
import com.ai.rti.ic.grp.ci.service.IMarketManageSubDomainService;
import com.ai.rti.ic.grp.ci.utils.RedisUtil;
import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.HttpClientUtil;
import com.ai.rti.ic.grp.utils.SpringContextUtil;
import com.ai.rti.ic.grp.utils.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customFileRelService")
@Transactional
public class CiCustomFileRelServiceImpl implements ICiCustomFileRelService {
	private final Logger log = Logger.getLogger(CiCustomFileRelServiceImpl.class);


	@Autowired
	private ICiCustomListInfoHDao ciCustomListInfoHDao;

	@Autowired
	private ICiLoadCustomGroupFileJDao ciLoadCustomGroupFileJdao;

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private ICustomGroupInfoJDao customGroupInfoJDao;
	@Autowired
	private IMarketManageSubDomainService domainService;


	@Override
	public boolean refreshCustListToRedis(Integer specifications, String tabName, String customGroupId, String column,
			JSONArray activityInfoList, long custNum) {
		long incrementnum = 0L;
		long decrementnum = 0L;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start = dateformat.format(Long.valueOf(System.currentTimeMillis()));

		int pointsDataLimit = 50000;
		Map<String, String> data = new HashMap<>();
		String rowkey = "";
		String redisKey = "CTT:CINFO:";
		try {
			if (specifications.intValue() == 0) {

				String activityType = "2";

				this.log.debug("开始从Spark获取数据....................");
				if (activityType.equals("2")) {
					List<CiCustomListInfo> list = this.ciCustomListInfoHDao.selectPeriodCustomListInfo(customGroupId);
					if (list.size() == 2) {
						CiCustomListInfo customListInfoA = list.get(1);
						CiCustomListInfo customListInfoB = list.get(0);
						String tableA = customListInfoA.getListTableName();
						String tableB = customListInfoB.getListTableName();

						JSONObject res = fullDosePushRedis(tableA, tableB, column, customGroupId);
						fullDosePushRedis(tabName, column, customGroupId, custNum);
						incrementnum = res.getLong("incrementNum").longValue();
						decrementnum = res.getLong("decrementNum").longValue();
					} else {
						fullDosePushRedis(tabName, column, customGroupId, custNum);
					}

				}

			} else if (specifications.intValue() == 1) {
				for (Object obj : activityInfoList) {
					JSONObject json = (JSONObject) obj;

					if (json.getString("activityType").equals("2")) {
// TODO
						decrementnum = decrementPushRedis(tabName, column, customGroupId, custNum);

						incrementnum = incrementPushRedis(tabName, column, customGroupId, custNum);
					}
				}
			}
		} catch (Exception e) {
			this.log.error("客户群推送异常", e);
			smsForRedis("客户群推送发生异常：" + e.getMessage() + "，请注意检查！");
			throw e;
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyMMddHHmm");
		String end = dateformat.format(Long.valueOf(System.currentTimeMillis()));

		String url = Config.getObject("NOTIC_CUSTOMGROUP_OPTIONS_URL");
		this.log.info("调用营服通知接口");

		CiCustomGroupInfo demo = new CiCustomGroupInfo();
		demo.setCustomGroupId(customGroupId);
		CiCustomGroupInfo groupInfo = this.customGroupInfoJDao.queryCustomer(demo);

		if (this.redisUtil.hexists("EC:PUSH:GRPINFO", customGroupId).booleanValue()) {

			String infos = this.redisUtil.hashGet("EC:PUSH:GRPINFO", customGroupId);

			this.log.info("第一次刷新 客户群ID" + customGroupId);

			this.log.info("第一次刷新 信息" + infos);

			String[] infoArr = infos.split(",");
			String activityId = infoArr[0];
			this.log.info(activityId);
			String strategyId = infoArr[1];
			this.log.info(strategyId);
			String compInstIds = infoArr[2];
			this.log.info(compInstIds);
			String oprPosCode = infoArr[3];
			this.log.info(oprPosCode);

			String[] compInstIdArr = compInstIds.split(",");
			for (String compInstId : compInstIdArr) {
				JSONObject param = new JSONObject();
				String batchId = compInstId + sf.format(new Date());
				param.put("batchId", batchId);
				param.put("oprPosId", oprPosCode);
				param.put("compInstId", compInstId);
				param.put("custGroupId", groupInfo.getCustomGroupId());
				param.put("custGroupName", groupInfo.getCustomGroupName());
				param.put("custGroupCount", String.valueOf(custNum));
				param.put("custGroupType", String.valueOf(groupInfo.getCustomGroupDataType()));
				param.put("startTime", start);
				param.put("endTime", end);
				param.put("activityId", activityId);
				this.log.info("调用营服通知接口：" + url);
				this.log.info("调用营服通知接口入参：" + param);
				String result = HttpClientUtil.doPost(url, param.toJSONString());
				this.log.info("通知营服返回" + result);
				this.redisUtil.hashSet("ACTIVITY_BATCH_ID", compInstId, batchId);
			}

			synchronized (this) {
				List<String> groupIds = this.redisUtil.listGetAll("ACTIVITY_STRATEGY_GROUP:" + strategyId);
				this.log.info("同一策略未处理过的的客户群IDs" + groupIds);
				this.redisUtil.lrem("ACTIVITY_STRATEGY_GROUP:" + strategyId, 1, customGroupId);
				groupIds = this.redisUtil.listGetAll("ACTIVITY_STRATEGY_GROUP:" + strategyId);
				this.log.info("同一策略处理过的的客户群IDs" + groupIds);
				if (groupIds == null || groupIds.size() == 0) {
					this.redisUtil.hashSet("JUST_PUT_STRATEGY", strategyId, strategyId);
					this.redisUtil.hdelkey("ACTIVITY_STRATEGY_GROUP", strategyId);
				}
			}

			this.redisUtil.hdelkey("EC:PUSH:GRPINFO", customGroupId);

		} else {

			label72: for (Object obj : activityInfoList) {

				JSONObject json = (JSONObject) obj;
				String strategyId = json.getString("strategyId");

				Map<String, String> compInstList = this.redisUtil.hashGetAll("STRATEGY_COMP_INST:" + strategyId);
				if (compInstList != null && compInstList.size() > 0) {
					Iterator<Map.Entry<String, String>> instIt = compInstList.entrySet().iterator();
					while (instIt.hasNext()) {
						Map.Entry<String, String> entry = instIt.next();
						JSONObject inst = JSONObject.parseObject(entry.getValue());
						String custGroupId = inst.getString("CUST_GROUP_ID");
						String supCustGroupId = inst.getString("SUPER_CUST_GROUP_ID");
						if (custGroupId.equals(customGroupId) || supCustGroupId.equals(customGroupId)) {
							JSONObject param = new JSONObject();
							String compInstId = inst.getString("COMP_INST_ID");
							String batchId = compInstId + sf.format(new Date());
							param.put("batchId", batchId);
							param.put("oprPosId", inst.getString("OPR_POS_CODE"));
							param.put("compInstId", compInstId);
							param.put("custGroupId", groupInfo.getCustomGroupId());
							param.put("custGroupName", groupInfo.getCustomGroupName());

							param.put("custGroupCount", Long.valueOf(incrementnum + decrementnum));

							param.put("custGroupType", String.valueOf(groupInfo.getCustomGroupDataType()));
							param.put("startTime", start);
							param.put("endTime", end);
							param.put("activityId", json.getString("activityId"));
							this.log.info("调用营服通知接口：" + url);
							this.log.info("调用营服通知接口入参：" + param);
							String result = HttpClientUtil.doPost(url, param.toJSONString());
							this.log.info("通知营服返回" + result);
							this.redisUtil.hashSet("ACTIVITY_BATCH_ID", compInstId, batchId);
						}
					}
				}

				if (json.getString("activityType").equals("2")) {
					while (true) {
						if (!this.redisUtil.hexists("JUST_PUT_STRATEGY", strategyId).booleanValue()) {
							JSONArray arr = getStrategyCompInstList(strategyId, customGroupId,
									specifications.intValue());
							Map<String, String> justPutStrategy = new HashMap<>();
							justPutStrategy.put(strategyId, arr.toString());
							long res = this.redisUtil.hashSetByPipeline("JUST_PUT_STRATEGY", justPutStrategy)
									.longValue();
							this.redisUtil.hashSetByPipeline("JUST_PUT_STRATEGY_TEST", justPutStrategy);

							if (res == 1L) {
								this.log.info("触发工单下派写入redis数据返回" + res + "，key:JUST_PUT_STRATEGY;filed:" + strategyId
										+ ";value:" + arr.toString());
							} else {
								this.log.info("触发工单下派写入redis数据返回" + res + "，key:JUST_PUT_STRATEGY;filed:" + strategyId
										+ ";value:" + arr.toString());
							}
							this.log.info(strategyId + "工单已经推送到DC..........");
							continue label72;
						}
						try {
							this.log.info("等待刷工单..................");
							Thread.sleep(120000L);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		return true;
	}

	private long decrementPushRedis(String tabName, String column, String customGroupId, long custNum) {
		long decrementNum = 0L;

		Map<String, String> data = new HashMap<>();
		String rowkey = "";
		String redisKey = "CTT:CINFO:";
		StringBuilder fields = new StringBuilder();

		this.log.debug("开始从Spark获取数据....................");

		int readFlag = -1;
		long customPageSize = Long.parseLong(Config.getObject("CUSTOM_PAGE_SIZE"));
		long startLine = 0L;
		long endLine = customPageSize;

		List<Map<String, Object>> check = new ArrayList<>();

		do {
			List<Map<String, Object>> customGroupList = this.ciLoadCustomGroupFileJdao.getCustomGroupList(tabName,
					readFlag, startLine, endLine);

			if (customGroupList.size() > 0) {
				check.add(customGroupList.get(0));
			}
// TOTO
			this.redisUtil.delKey(redisKey + customGroupId + "_decrement");
			for (Map<String, Object> custInfo : customGroupList) {
				StringBuffer valuesStrBuf = new StringBuffer();
				for (String key : custInfo.keySet()) {
					if (!String.valueOf(key).toUpperCase().equals("ROWNUM") && !String.valueOf(key)
							.equalsIgnoreCase(Config.getObject("CUSTOM_INCREMENT_ATTR"))) {
						if (String.valueOf(key).toUpperCase().equals(column)) {
							rowkey = String.valueOf(key);
						}
						valuesStrBuf.append(custInfo.get(key));
						valuesStrBuf.append("|");
					}
				}
				fields.append(custInfo.get(rowkey).toString().trim());
				fields.append(",");
				data.put(custInfo.get(rowkey).toString().trim(),
						valuesStrBuf.toString().substring(0, valuesStrBuf.length() - 1));
			}

			this.redisUtil.hashSetByPipeline(redisKey + customGroupId + "_decrement", data);
			this.redisUtil.hdelKey(redisKey + customGroupId, fields.toString().split(","));

			fields = new StringBuilder();
			data.clear();
			this.log.debug("客户群" + customGroupId + "减量刷新redis完成=====================================刷新"
					+ customGroupList.size() + "条");
			decrementNum = customGroupList.size() + 0L;
			startLine = endLine;
			endLine += customPageSize;
		} while (custNum > startLine);

		long decCount = this.redisUtil.hlen(redisKey + customGroupId + "_decrement").longValue();
		if (decCount == 0L && check.size() != 0) {
			smsForRedis(
					"增减量客户群：" + customGroupId + "  刷减量key:" + redisKey + customGroupId + "_decrement  时数据为空，请注意检查！");
		}
		for (Map<String, Object> map : check) {
			for (String key : map.keySet()) {
				if (!String.valueOf(key).toUpperCase().equals("ROWNUM")
						&& !String.valueOf(key)
								.equalsIgnoreCase(Config.getObject("CUSTOM_INCREMENT_ATTR"))
						&& String.valueOf(key).toUpperCase().equals(column)) {
					rowkey = String.valueOf(key);
				}
			}

			String value = this.redisUtil.hashGet(redisKey + customGroupId, map.get(rowkey).toString().trim());
			if (StringUtil.isNotEmpty(value)) {
				smsForRedis("增减量客户群：" + customGroupId + "  减量数据删除时异常，请注意检查！");
			}
		}
		return decrementNum;
	}

	private long incrementPushRedis(String tabName, String column, String customGroupId, long custNum) {
		Long incrementNum = Long.valueOf(0L);

		Map<String, String> data = new HashMap<>();
		String rowkey = "";
		String redisKey = "CTT:CINFO:";

		this.log.debug("开始从Spark获取数据....................");

		int readFlag = 1;
		long customPageSize = Long.parseLong(Config.getObject("CUSTOM_PAGE_SIZE"));
		long startLine = 0L;
		long endLine = customPageSize;

		List<Map<String, Object>> check = new ArrayList<>();

		do {
			List<Map<String, Object>> customGroupList = this.ciLoadCustomGroupFileJdao.getCustomGroupList(tabName,
					readFlag, startLine, endLine);
			if (customGroupList.size() > 0) {
				check.add(customGroupList.get(0));
			}

			this.redisUtil.delKey(redisKey + customGroupId + "_increment");
			for (Map<String, Object> custInfo : customGroupList) {
				StringBuffer valuesStrBuf = new StringBuffer();
				for (String key : custInfo.keySet()) {
					if (!String.valueOf(key).toUpperCase().equals("ROWNUM") && !String.valueOf(key)
							.equalsIgnoreCase(Config.getObject("CUSTOM_INCREMENT_ATTR"))) {
						if (String.valueOf(key).toUpperCase().equals(column)) {
							rowkey = String.valueOf(key);
						}
						valuesStrBuf.append(custInfo.get(key));
						valuesStrBuf.append("|");
					}
				}
				data.put(custInfo.get(rowkey).toString().trim(),
						valuesStrBuf.toString().substring(0, valuesStrBuf.length() - 1));
			}

			this.redisUtil.hashSetByPipeline(redisKey + customGroupId + "_increment", data);
			this.redisUtil.hashSetByPipeline(redisKey + customGroupId, data);
			data.clear();
			this.log.debug("客户群" + customGroupId + "增量刷新redis完成=====================================刷新"
					+ customGroupList.size() + "条");
			incrementNum = Long.valueOf(customGroupList.size() + 0L);
			startLine = endLine;
			endLine += customPageSize;
		} while (custNum > startLine);

		long incCount = this.redisUtil.hlen(redisKey + customGroupId + "_increment").longValue();
		if (incCount == 0L && check.size() != 0) {
			smsForRedis(
					"增减量客户群：" + customGroupId + "  刷增量key:" + redisKey + customGroupId + "_increment  时数据为空，请注意检查！");
		}
		for (Map<String, Object> map : check) {
			for (String key : map.keySet()) {
				if (!String.valueOf(key).toUpperCase().equals("ROWNUM")
						&& !String.valueOf(key)
								.equalsIgnoreCase(Config.getObject("CUSTOM_INCREMENT_ATTR"))
						&& String.valueOf(key).toUpperCase().equals(column)) {
					rowkey = String.valueOf(key);
				}
			}

			String value = this.redisUtil.hashGet(redisKey + customGroupId, map.get(rowkey).toString().trim());
			if (StringUtil.isEmpty(value)) {
				smsForRedis("增减量客户群：" + customGroupId + "  增量数据刷入时异常，请注意检查！");
			}
		}

		return incrementNum.longValue();
	}

	private synchronized void fullDosePushRedis(String tabName, String column, String customGroupId, long custNum) {
		Map<String, String> data = new HashMap<>();
		String rowkey = "";
		String redisKey = "CTT:CINFO:";

		long customPageSize = Long.parseLong(Config.getObject("CUSTOM_PAGE_SIZE"));
		long startLine = 0L;
		long endLine = customPageSize;

		this.redisUtil.delKey(redisKey + customGroupId);

		do {
			List<Map<String, Object>> customGroupList = this.ciLoadCustomGroupFileJdao.getCustomGroupList(tabName,
					startLine, endLine, column);
			for (Map<String, Object> custInfo : customGroupList) {
				StringBuffer valuesStrBuf = new StringBuffer();
				for (String key : custInfo.keySet()) {
					if (!String.valueOf(key).toUpperCase().equals("ROWNUM")) {
						if (String.valueOf(key).toUpperCase().equals(column)) {
							rowkey = String.valueOf(key);
						}
						valuesStrBuf.append(custInfo.get(key));
						valuesStrBuf.append("|");
					}
				}
				data.put(custInfo.get(rowkey).toString().trim(),
						valuesStrBuf.toString().substring(0, valuesStrBuf.length() - 1));
			}

			this.redisUtil.hashSetByPipeline(redisKey + customGroupId, data);
			this.log.info("客户群" + customGroupId + " 全量刷新redis=====================================spark数据"
					+ customGroupList.size() + "条,写入redis数据" + data.size() + "条");
			data.clear();

			startLine = endLine;
			endLine += customPageSize;
		} while (custNum > startLine);

		Long count = this.redisUtil.hlen(redisKey + customGroupId);
		if (count.longValue() == 0L) {
			smsForRedis("全量客户群：" + customGroupId + " 刷入redis数据为空，请注意检查!");
		}
	}

	private synchronized JSONObject fullDosePushRedis(String tabNameA, String tabNameB, String column,
			String customGroupId) {
		JSONObject result = new JSONObject();

		String redisKey = "CTT:CINFO:";

		List<Map<String, Object>> incrementCustomGroupList = this.ciLoadCustomGroupFileJdao.getCustomGroupList(tabNameB,
				tabNameA, column);

		List<Map<String, Object>> decrementCustomGroupList = this.ciLoadCustomGroupFileJdao.getCustomGroupList(tabNameA,
				tabNameB, column);
		long incrementNum = incrementPushRedis(redisKey, customGroupId, incrementCustomGroupList, column);
		long decrementNum = decrementPushRedis(redisKey, customGroupId, decrementCustomGroupList, column);
		result.put("incrementNum", Long.valueOf(incrementNum));
		result.put("decrementNum", Long.valueOf(decrementNum));
		return result;
	}

	public long incrementPushRedis(String redisKey, String customGroupId,
			List<Map<String, Object>> incrementCustomGroupList, String column) {
		long incrementNum = 0L;
		int pointsDataLimit = 50000;
		int i = 0;
		Map<String, String> incrementData = new HashMap<>();
		String rowkey = "";
		this.redisUtil.delKey(redisKey + customGroupId + "_increment");
		for (Map<String, Object> custInfo : incrementCustomGroupList) {
			StringBuffer valuesStrBuf = new StringBuffer();
			for (String key : custInfo.keySet()) {
				if (!String.valueOf(key).toUpperCase().equals("ROWNUM")) {
					if (String.valueOf(key).toUpperCase().equals(column)) {
						rowkey = String.valueOf(key);
					}
					valuesStrBuf.append(custInfo.get(key));
					valuesStrBuf.append("|");
				}
			}
			incrementData.put(custInfo.get(rowkey).toString().trim(),
					valuesStrBuf.toString().substring(0, valuesStrBuf.length() - 1));
			if (i % pointsDataLimit == 0) {
				this.redisUtil.hashSetByPipeline(redisKey + customGroupId + "_increment", incrementData);

				incrementData.clear();
			}
			i++;
		}
		this.redisUtil.hashSetByPipeline(redisKey + customGroupId + "_increment", incrementData);

		this.log.info(
				"Redis Key" + redisKey + customGroupId + "_increment 刷新" + incrementCustomGroupList.size() + "条记录");
		this.log.info("Redis Key " + redisKey + customGroupId + " 追加" + incrementCustomGroupList.size() + "条记录");
		incrementNum = incrementCustomGroupList.size() + 0L;
		long count = this.redisUtil.hlen(redisKey + customGroupId + "_increment").longValue();
		if (count == 0L && incrementCustomGroupList.size() != 0) {
			smsForRedis("全量客户群：" + customGroupId + " 刷入增量key时数据为空，请注意检查!");
		}
		return incrementNum;
	}

	public long decrementPushRedis(String redisKey, String customGroupId,
			List<Map<String, Object>> decrementCustomGroupList, String column) {
		long decrementNum = 0L;
		Map<String, String> decrementData = new HashMap<>();
		StringBuilder fields = new StringBuilder();
		int pointsDataLimit = 50000;
		int i = 0;
		String rowkey = "";
		this.redisUtil.delKey(redisKey + customGroupId + "_decrement");
		for (Map<String, Object> custInfo : decrementCustomGroupList) {
			StringBuffer valuesStrBuf = new StringBuffer();
			for (String key : custInfo.keySet()) {
				if (!String.valueOf(key).toUpperCase().equals("ROWNUM")) {
					if (String.valueOf(key).toUpperCase().equals(column)) {
						rowkey = String.valueOf(key);
					}
					valuesStrBuf.append(custInfo.get(key));
					valuesStrBuf.append("|");
				}
			}
			decrementData.put(custInfo.get(rowkey).toString().trim(),
					valuesStrBuf.toString().substring(0, valuesStrBuf.length() - 1));
			fields.append(custInfo.get(rowkey).toString().trim());
			fields.append(",");
			if (i % pointsDataLimit == 0) {
				this.redisUtil.hashSetByPipeline(redisKey + customGroupId + "_decrement", decrementData);

				fields = new StringBuilder();
				decrementData = new HashMap<>();
			}
			i++;
		}
		this.redisUtil.hashSetByPipeline(redisKey + customGroupId + "_decrement", decrementData);

		this.log.info(
				"Redis Key " + redisKey + customGroupId + "_decrement 刷新" + decrementCustomGroupList.size() + "条记录");
		decrementNum = decrementCustomGroupList.size() + 0L;
		long count = this.redisUtil.hlen(redisKey + customGroupId + "_decrement").longValue();
		if (count == 0L && decrementCustomGroupList.size() != 0) {
			smsForRedis("全量客户群：" + customGroupId + " 刷入减量key时数据为空，请注意检查!");
		}
		return decrementNum;
	}

	public void pushIncrementIdToRedis(String customId) {
		this.redisUtil.addWithSet("CTT:CINFO:INCREMENT", new String[] { customId });
	}

	private JSONArray getStrategyCompInstList(String strategyId, String customGroupId, int specifications) {
		Map<String, String> compInstList = this.redisUtil.hashGetAll("STRATEGY_COMP_INST:" + strategyId);
		JSONArray arr = new JSONArray();
		if (compInstList != null && compInstList.size() > 0) {
			this.log.info("获取活动执行组件信息:" + compInstList);
			Iterator<Map.Entry<String, String>> instIt = compInstList.entrySet().iterator();
			while (instIt.hasNext()) {
				Map.Entry<String, String> entry = instIt.next();
				JSONObject inst = JSONObject.parseObject(entry.getValue());
				if (inst.getLong("SUP_COMP_ID").longValue() == 1L
						&& StringUtils.isBlank(inst.getString("EVENT_CODE"))) {
					String custGroupId = inst.getString("CUST_GROUP_ID");
					String supCustGroupId = inst.getString("SUPER_CUST_GROUP_ID");
					if (custGroupId.equals(customGroupId)) {

						inst.put("isIncrementGroup", Boolean.valueOf(true));
						arr.add(inst);
						continue;
					}
					if (supCustGroupId.equals(customGroupId)) {

						inst.put("isIncrementGroup", Boolean.valueOf(true));
						arr.add(inst);
					}
				}
			}
		}
		return arr;
	}


	@Override
	public boolean attrPushRedis(List<CiGroupAttrRel> attrList, String customGroupId) {
		try {
			String key = "CTT:METAINFO";
			JSONArray mataData = new JSONArray();
			for (CiGroupAttrRel attr : attrList) {
				StringBuilder value = new StringBuilder();
				value.append(attr.getAttrCol());
				value.append("|");
				value.append(attr.getAttrColName());
				mataData.add(value);
			}
			this.redisUtil.hdelkey(key, String.valueOf(customGroupId));
			this.redisUtil.hashSet(key, String.valueOf(customGroupId), mataData.toJSONString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void smsForRedis(String msg) {
		SmsNotificationImpl smsNotification = null;

		try {
			smsNotification = (SmsNotificationImpl) SpringContextUtil.getBean("SmsNotificationImpl");
		} catch (Exception e) {
			this.log.error("客户群推送异常", e);
		}
		MarketManageSubDomain demo = new MarketManageSubDomain();
		demo.setMasterDataCode("CAM-C-0019");
		demo.setCodeValue("1000");
		List<MarketManageSubDomain> domains = this.domainService.queryObjList(demo);
		for (MarketManageSubDomain domain : domains) {
			String message = domain.getMasterDataName() + " 您好:\t" + msg;
			smsNotification.onMessage(message, domain.getValue());
		}
	}


}
