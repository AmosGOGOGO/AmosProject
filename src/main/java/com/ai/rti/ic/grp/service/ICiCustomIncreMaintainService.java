package  com.ai.rti.ic.grp.service;

import com.ai.rti.ic.grp.entity.CiCustomGroupSubGroupRule;
import java.util.List;
import java.util.Map;

public interface ICiCustomIncreMaintainService {
  boolean subCustomGroupMaintain(String paramString1, String paramString2, String paramString3, Integer paramInteger, String paramString4);
  
  boolean singleSubCustomGroupMaintain(CiCustomGroupSubGroupRule paramCiCustomGroupSubGroupRule, String paramString1, String paramString2, Integer paramInteger);
  
  List<Map<String, Object>> subCustomGroupCount(CiCustomGroupSubGroupRule paramCiCustomGroupSubGroupRule, String paramString);
}
