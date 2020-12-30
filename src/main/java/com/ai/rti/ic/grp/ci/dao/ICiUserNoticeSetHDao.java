package com.ai.rti.ic.grp.ci.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ai.rti.ic.grp.ci.entity.CiUserNoticeSet;
import com.ai.rti.ic.grp.ci.entity.DimAnnouncementType;
import com.ai.rti.ic.grp.ci.entity.DimNoticeSendMode;
import com.ai.rti.ic.grp.ci.entity.DimNoticeType;
@Mapper
public interface ICiUserNoticeSetHDao {

	List<CiUserNoticeSet> selectUserNoticeOpenClose(@Param("userId") String userId, @Param("noticeId") String noticeId, @Param("valueOf") Integer valueOf, @Param("valueOf2") Integer valueOf2,
			@Param("sendModeId") String sendModeId);

	void insertUserNoticeSet(List<CiUserNoticeSet> noticeSets);

	List<DimNoticeSendMode> selectdimNoticeSendMode();

	List<CiUserNoticeSet> selectUserNoticeSetInfo(@Param("userId") String userId, @Param("noticeType") String noticeType, @Param("noticeTypeId") Integer noticeTypeId);

	List<DimAnnouncementType> selectDimAnnouncementType();

	List<DimNoticeType> selectDimNoticeType();

	List<CiUserNoticeSet> selectUserNoticeSet(String userId);


}
