package com.ai.rti.ic.grp.ci.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ai.rti.ic.grp.ci.service.ICiCustomListInfoJDao;
import com.ai.rti.ic.grp.dao.base.BackDataSourceFactory;

@Repository
public class CiCustomListInfoJDaoImpl implements ICiCustomListInfoJDao {
	private Logger log = Logger.getLogger(CiCustomListInfoJDaoImpl.class);

	public void executeInBackDataBase(String sql) {
		BackDataSourceFactory.getBackSimpleJdbcTemplate().execute(sql);
	}

}
