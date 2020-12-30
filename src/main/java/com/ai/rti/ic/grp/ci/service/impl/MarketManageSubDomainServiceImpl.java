package com.ai.rti.ic.grp.ci.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.rti.ic.grp.ci.dao.IMarketManageSubDomainJDao;
import com.ai.rti.ic.grp.ci.service.IMarketManageSubDomainService;
import com.ai.rti.ic.grp.ci.entity.MarketManageSubDomain;
@Service
public class MarketManageSubDomainServiceImpl implements IMarketManageSubDomainService {
	@Autowired
	private IMarketManageSubDomainJDao marketManageSubDomainJDao;

	public List<MarketManageSubDomain> queryObjList(MarketManageSubDomain marketManageSubDomain) {
		return this.marketManageSubDomainJDao.queryObjList(marketManageSubDomain);
	}

}
