package com.ai.rti.ic.grp.ci.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.rti.ic.grp.ci.dao.ICiCustomListInfoHDao;
import com.ai.rti.ic.grp.ci.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.ci.entity.CiCustomListInfo;
import com.ai.rti.ic.grp.ci.entity.CiSysInfo;
import com.ai.rti.ic.grp.ci.service.ICiLoadCustomGroupFileJDao;
import com.ai.rti.ic.grp.ci.service.ICustomerPush;
import com.ai.rti.ic.grp.ci.utils.RedisUtil;
import com.ai.rti.ic.grp.utils.Config;


@Service
@Transactional
@Scope("prototype")
public class JsPPMCustomerPushImpl implements ICustomerPush {
	private static Logger log = Logger.getLogger(JsPPMCustomerPushImpl.class);

	@Autowired
	private ICiCustomListInfoHDao ciCustomListInfoHDao;

	@Autowired
	private ICiLoadCustomGroupFileJDao ciLoadCustomGroupFileJdao;

	@Autowired
	private RedisUtil redisUtil;

	public boolean push(CiCustomGroupInfo ciCustomGroupInfo, CiCustomListInfo ciCustomListInfo, CiSysInfo ciSysInfo) {
		return pushToPPM(ciCustomGroupInfo, ciCustomListInfo, ciSysInfo);
	}

	private boolean pushToPPM(CiCustomGroupInfo ciCustomGroupInfo, CiCustomListInfo ciCustomListInfo,
			CiSysInfo ciSysInfo) {
		log.info(String.format(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始推送客户群 [%s] 至[%s] [%s]",
				new Object[] { ciCustomListInfo.getCustomGroupId(), ciSysInfo.getSysName(), ciSysInfo.getSysId() }));

		String colum = Config.getObject("RELATED_COLUMN_" + ciCustomGroupInfo.getCustomGroupBelong());
		return refreshPPMCustListToRedis(ciCustomGroupInfo.getCustomGroupDataType(),
				ciCustomListInfo.getListTableName(), ciCustomGroupInfo.getCustomGroupId(), colum,
				ciCustomListInfo.getCustomNum().longValue(), ciCustomGroupInfo.getCustomGroupBelong(),
				ciCustomGroupInfo.getGroupId());
	}

	public boolean refreshPPMCustListToRedis(Integer specifications, String tabName, String customGroupId,
			String relatedColumn, long custNum, Integer customGroupBelong, Integer groupId) {
		int pointsDataLimit = 50000;
		String rowkey = "";
		if (customGroupBelong.intValue() == 0) {
			customGroupBelong = Integer.valueOf(2000);
		} else if (customGroupBelong.intValue() == 1) {
			customGroupBelong = Integer.valueOf(1000);
		}
		String redisKey = "PPM:GRPINFO:" + customGroupBelong + ":" + groupId;

		this.redisUtil.addWithSet("PPM:GRPINFO:" + customGroupBelong, new String[] { redisKey });
		if (specifications.intValue() == 0) {

			log.debug("开始从Spark获取数据....................");
			List<CiCustomListInfo> list = this.ciCustomListInfoHDao.selectPeriodCustomListInfo(customGroupId);

			this.redisUtil.delKey(redisKey);

			long customPageSize = Long.parseLong(Config.getObject("CUSTOM_PAGE_SIZE"));
			long startLine = 0L;
			long endLine = customPageSize;
			do {
				List<Map<String, Object>> customGroupList = this.ciLoadCustomGroupFileJdao.getCustomGroupList(tabName,
						startLine, endLine, relatedColumn);
				for (Map<String, Object> custInfo : customGroupList) {

					for (String key : custInfo.keySet()) {
						if (String.valueOf(key).toUpperCase().equals(relatedColumn)) {
							rowkey = String.valueOf(key);
						}
					}
					this.redisUtil.addWithSet(redisKey, new String[] { custInfo.get(rowkey).toString() });
				}
				log.info("客户群" + customGroupId + " 全量刷新redis=====================================spark数据"
						+ customGroupList.size() + "条");
				startLine = endLine;
				endLine += customPageSize;
			} while (custNum > startLine);
		} else if (specifications.intValue() == 1) {

			String readFlag = "";
			Integer increment = Integer.valueOf(0);
			Integer decrement = Integer.valueOf(0);

			long customPageSize = Long.parseLong(Config.getObject("CUSTOM_PAGE_SIZE"));
			long startLine = 0L;
			long endLine = customPageSize;
			do {
				List<Map<String, Object>> customGroupList = this.ciLoadCustomGroupFileJdao.getCustomGroupList(tabName,
						startLine, endLine, relatedColumn);
				for (Map<String, Object> custInfo : customGroupList) {

					for (String key : custInfo.keySet()) {
						if (String.valueOf(key).toUpperCase().equals(relatedColumn)) {
							rowkey = String.valueOf(key);
						}
						if (String.valueOf(key)
								.equalsIgnoreCase(Config.getObject("CUSTOM_INCREMENT_ATTR"))) {
							readFlag = custInfo.get(key).toString();
						}
					}
					if (readFlag.equals("1")) {
						this.redisUtil.addWithSet(redisKey, new String[] { custInfo.get(rowkey).toString() });
						increment = Integer.valueOf(increment.intValue() + 1);
						continue;
					}
					if (readFlag.equals("-1")) {
						this.redisUtil.srem(redisKey, new String[] { custInfo.get(rowkey).toString() });
						decrement = Integer.valueOf(decrement.intValue() + 1);
					}
				}
				log.info("客户群" + customGroupId + "刷新redis=====================================增量数据" + increment
						+ "条,减量数据" + decrement + "条");
				startLine = endLine;
				endLine += customPageSize;
			} while (custNum > startLine);
		}

		return true;
	}
}
