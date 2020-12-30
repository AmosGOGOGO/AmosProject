package com.ai.rti.ic.grp.ci.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.rti.ic.grp.ci.dao.ICiSysInfoHDao;
import com.ai.rti.ic.grp.ci.entity.CiSysInfo;
import com.ai.rti.ic.grp.ci.service.ICiSysInfoService;

@Service
@Transactional
public class CiSysInfoServiceImpl implements ICiSysInfoService {
	@Autowired
	private ICiSysInfoHDao ciSysInfoHDao;

	public CiSysInfo queryById(String sysId) {
		return this.ciSysInfoHDao.selectById(sysId);
	}
}
