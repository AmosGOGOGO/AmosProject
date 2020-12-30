package com.ai.rti.ic.grp.ci.service;


import com.ai.rti.ic.grp.ci.entity.CiPersonNotice;

public interface ISmsNotification {
	boolean personNoticeSms(CiPersonNotice paramCiPersonNotice) throws Exception;

}
