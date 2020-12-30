package com.ai.rti.ic.grp.ci.service;

import java.util.List;

import com.ai.rti.ic.grp.ci.entity.CiGroupAttrRel;

public interface ICiGroupAttrRelService {

	List<CiGroupAttrRel> queryNewestGroupAttrRelList(String customGroupId);
}
