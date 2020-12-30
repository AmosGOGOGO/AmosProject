package com.ai.rti.ic.grp.ci.service;


import com.ai.rti.ic.grp.ci.entity.CiPersonNotice;

public interface IMailNotification {
	boolean personNoticeMail(CiPersonNotice paramCiPersonNotice) throws Exception;

}
