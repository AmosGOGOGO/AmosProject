package com.ai.rti.ic.grp.ci.entity;

//import com.ai.rti.ic.grp.ci.service.ICiLabelInfoService;
//import com.ai.rti.ic.grp.ci.service.impl.CiLabelInfoServiceImpl;
import com.ai.rti.ic.grp.ci.utils.CacheBase;
//import com.ai.rti.ic.grp.utils.SpringContextUtil;
import com.ai.rti.ic.grp.utils.StringUtil;

public class IdToName {
	public static String getName(String cacheKey, Object id) {
		String name = "";
		CacheBase cb = CacheBase.getInstance();
		if (cb != null) {
			name = cb.getNameByKey(cacheKey, id);
			if (StringUtil.isEmpty(name)) {
				name = String.valueOf(id);
			}
		}
		return name;
	}

//	public static String getLabelName(String cacheKey, Object id) {
//		String name = "";
//		CacheBase cb = CacheBase.getInstance();
//		if (cb != null) {
//			name = cb.getLabelNameByKey(cacheKey, id);
//			if (StringUtil.isEmpty(name)) {
//				name = String.valueOf(id);
//			}
//		}
//		return name;
//	}

//	public static String getLabelName(Object id) {
//		CiLabelInfoServiceImpl ciLabelInfoServiceImpl=null;
//		String name = "";
//		ICiLabelInfoService ciLabelInfoService = null;
//		try {
//			ciLabelInfoServiceImpl = (CiLabelInfoServiceImpl) SpringContextUtil.getBean("ciLabelInfoServiceImpl");
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		if (id != null) {
//			name = ciLabelInfoServiceImpl.queryCiLabelInfoById(Integer.valueOf(Integer.parseInt((String) id)))
//					.getLabelName();
//			if (StringUtil.isEmpty(name)) {
//				name = String.valueOf(id);
//			}
//		}
//		return name;
//	}
}
