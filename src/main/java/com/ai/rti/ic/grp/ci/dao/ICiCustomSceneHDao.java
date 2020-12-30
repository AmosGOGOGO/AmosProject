package com.ai.rti.ic.grp.ci.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ai.rti.ic.grp.ci.entity.CiCustomSceneRel;

@Mapper
public interface ICiCustomSceneHDao {

	List<CiCustomSceneRel> getCustomScenesByCustomId(@Param("customGroupId")String customGroupId);

}
