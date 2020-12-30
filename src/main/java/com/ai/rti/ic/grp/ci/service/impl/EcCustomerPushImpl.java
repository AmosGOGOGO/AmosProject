package com.ai.rti.ic.grp.ci.service.impl;

import com.ai.rti.ic.grp.ci.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.ci.entity.CiCustomListInfo;
import com.ai.rti.ic.grp.ci.entity.CiGroupAttrRel;
import com.ai.rti.ic.grp.ci.entity.CiSysInfo;
import com.ai.rti.ic.grp.ci.service.ICiCustomFileRelService;
import com.ai.rti.ic.grp.ci.service.ICiGroupAttrRelService;
import com.ai.rti.ic.grp.ci.service.ICustomerPush;
import com.ai.rti.ic.grp.ci.utils.RedisUtil;
import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.HttpClientUtil;
import com.alibaba.fastjson.JSONArray;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Scope("prototype")
public class EcCustomerPushImpl implements ICustomerPush {
	private static Logger log = Logger.getLogger(EcCustomerPushImpl.class);

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private ICiCustomFileRelService ciCustomFileRelService;
	@Autowired
	private ICiGroupAttrRelService ciGroupAttrRelService;

	public boolean push(CiCustomGroupInfo ciCustomGroupInfo, CiCustomListInfo ciCustomListInfo, CiSysInfo ciSysInfo) {
		return pushToEC(ciCustomGroupInfo, ciCustomListInfo, ciSysInfo);
	}

	private boolean pushToEC(CiCustomGroupInfo ciCustomGroupInfo, CiCustomListInfo ciCustomListInfo,
			CiSysInfo ciSysInfo) {
		boolean flag = false;
		String customGroupId = ciCustomGroupInfo.getCustomGroupId();
		log.info(String.format(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始推送客户群 [%s] 至[%s] [%s]",
				new Object[] { ciCustomGroupInfo.getCustomGroupId(), ciSysInfo.getSysName(), ciSysInfo.getSysId() }));
		String colum = Config.getObject("RELATED_COLUMN_" + ciCustomGroupInfo.getCustomGroupBelong());

		if (ciCustomGroupInfo.getCustomGroupDataType().intValue() == 1) {
			Long ret = this.redisUtil.addWithSet("CTT:CINFO:INCREMENT", new String[] { customGroupId });
			log.info("刷入增量客户群" + ret);
		}
		JSONArray activityInfoList = getCustomGroupActivity(customGroupId);
		if (activityInfoList != null && activityInfoList.size() > 0) {

			this.ciCustomFileRelService.refreshCustListToRedis(ciCustomGroupInfo.getCustomGroupDataType(),
					ciCustomListInfo.getListTableName(), ciCustomGroupInfo.getCustomGroupId(), colum, activityInfoList,
					ciCustomListInfo.getCustomNum().longValue());
			if (ciCustomGroupInfo.getCustomGroupDataType().intValue() == 0) {

				List<CiGroupAttrRel> ciGroupAttrRelList = this.ciGroupAttrRelService
						.queryNewestGroupAttrRelList(customGroupId);
				this.ciCustomFileRelService.attrPushRedis(ciGroupAttrRelList, ciCustomGroupInfo.getCustomGroupId());
			}
		}
		flag = true;
		return flag;
	}

	private JSONArray getCustomGroupActivity(String customGroupId) {
		String url = Config.getObject("MCD_CUSTOMGROUP_OPTIONS_URL") + "&custGroupId="
				+ customGroupId;
		String responseBody = HttpClientUtil.getMethod(url);
		JSONArray jsonArroy = JSONArray.parseArray(responseBody);
		return jsonArroy;
	}
}
