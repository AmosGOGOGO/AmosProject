package com.ai.rti.ic.grp.ci.service;

import java.util.List;
import java.util.Map;

public interface ICiLoadCustomGroupFileJDao {

	List<Map<String, Object>> getCustomGroupList(String tabName, long startLine, long endLine, String relatedColumn);

	List<Map<String, Object>> getCustomGroupList(String tabName, int readFlag, long startLine, long endLine);

	List<Map<String, Object>> getCustomGroupList(String tabNameB, String tabNameA, String column);


}
