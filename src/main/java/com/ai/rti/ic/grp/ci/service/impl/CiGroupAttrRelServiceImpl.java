package com.ai.rti.ic.grp.ci.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.rti.ic.grp.ci.dao.ICiGroupAttrRelJDao;
import com.ai.rti.ic.grp.ci.entity.CiGroupAttrRel;
import com.ai.rti.ic.grp.ci.exception.CIServiceException;
import com.ai.rti.ic.grp.ci.service.ICiGroupAttrRelService;

@Service
@Transactional
public class CiGroupAttrRelServiceImpl implements ICiGroupAttrRelService {
	private Logger log = Logger.getLogger(CiGroupAttrRelServiceImpl.class);


	@Autowired
	private ICiGroupAttrRelJDao ciGroupAttrRelJDao;

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<CiGroupAttrRel> queryGroupAttrRelList(String groupInfoId, Date listCreateTime)
			throws CIServiceException {
		List<CiGroupAttrRel> attrRelList = new ArrayList<>();
		try {
			attrRelList = this.ciGroupAttrRelJDao.selectCiGroupAttrRelList(groupInfoId, listCreateTime);
			for (CiGroupAttrRel attrRel : attrRelList) {
				if (attrRel.getAttrColName() != null && attrRel.getAttrColName().contains("\"")) {
					attrRel.setAttrColName(attrRel.getAttrColName().replace("\"", "\\\""));
				}
			}
		} catch (Exception e) {
			this.log.error("", e);
			throw new CIServiceException("查询客户群属性列集合失败");
		}
		return attrRelList;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<CiGroupAttrRel> queryNewestGroupAttrRelList(String groupInfoId) throws CIServiceException {
		List<CiGroupAttrRel> attrRelList = null;
		try {
			attrRelList = this.ciGroupAttrRelJDao.selectNewestCiGroupAttrRelList(groupInfoId);
			for (CiGroupAttrRel attrRel : attrRelList) {
				if (attrRel.getAttrColName().contains("\"")) {
					attrRel.setAttrColName(attrRel.getAttrColName().replace("\"", "\\\""));
				}
			}
		} catch (Exception e) {
			this.log.error(e);
			throw new CIServiceException("查询最新的客户群属性列集合失败");
		}
		return attrRelList;
	}

}
