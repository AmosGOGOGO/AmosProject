package com.ai.rti.ic.grp.ci.service;

import com.ai.rti.ic.grp.ci.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.ci.entity.CiCustomListInfo;
import com.ai.rti.ic.grp.ci.entity.CiSysInfo;

public interface ICustomerPush {
	boolean push(CiCustomGroupInfo paramCiCustomGroupInfo, CiCustomListInfo paramCiCustomListInfo,
			CiSysInfo paramCiSysInfo);
}
