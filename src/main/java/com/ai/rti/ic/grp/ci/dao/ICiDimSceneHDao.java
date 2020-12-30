package com.ai.rti.ic.grp.ci.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ai.rti.ic.grp.ci.entity.DimScene;


@Mapper
public interface ICiDimSceneHDao {

	List<DimScene> getDimSceneList();

}
