package com.ai.rti.ic.grp.ci.utils;

import com.ai.rti.ic.grp.ci.dao.ICiDimSceneHDao;
import com.ai.rti.ic.grp.ci.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.ci.entity.CiNoticeModel;
import com.ai.rti.ic.grp.ci.entity.DimCity;
import com.ai.rti.ic.grp.ci.entity.DimCityThreadConfig;
import com.ai.rti.ic.grp.ci.entity.DimCustomCreateType;
import com.ai.rti.ic.grp.ci.entity.DimScene;
import com.ai.rti.ic.grp.ci.entity.Pager;
import com.ai.rti.ic.grp.ci.exception.CIServiceException;
import com.ai.rti.ic.grp.ci.service.ICustomersManagerService;
import com.ai.rti.ic.grp.ci.utils.adapter.DataBaseAdapter;
import com.ai.rti.ic.grp.constant.CommonConstants;
import com.ai.rti.ic.grp.constant.ServiceConstants;
import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.SpringContextUtil;
import com.ai.rti.ic.grp.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CacheBase {
	private Logger log = Logger.getLogger(CacheBase.class);
	private static DataBaseAdapter dataBaseAdapter = null;
	private Map<String, Map<Object, Object>> cacheKVContainer = new ConcurrentHashMap<>();

	private Map<String, CopyOnWriteArrayList<?>> cacheListContainer = new ConcurrentHashMap<>();

	private Map<String, CopyOnWriteArrayList<?>> cacheKeyArray = new ConcurrentHashMap<>();
	

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ICiDimSceneHDao iCiDimSceneHDao;

	@Autowired
	private ICustomersManagerService customersManagerService;


	private Map<String, CiNoticeModel> userNoticeContainer;

	private static Set<String> dimCitySet = new HashSet<>();


	public static Set<String> getDimCitySet() {
		return dimCitySet;
	}

	public static CacheBase getInstance() {
		if (dimCitySet == null || dimCitySet.size() == 0) {
			String dimIdsStr = Config.getObject("DIM_IDS");
			String[] dimIds = null;
			if (StringUtil.isNotEmpty(dimIdsStr)) {
				dimIds = dimIdsStr.split(",");
				for (String dimId : dimIds) {
					dimCitySet.add(dimId.toUpperCase());
				}
			}
		}
//		return instance;
		return (CacheBase) SpringContextUtil.getBean("cacheBase");
	}

	@PostConstruct
	public boolean init() throws Exception {
		this.cacheKVContainer = new ConcurrentHashMap<>();
		this.cacheListContainer = new ConcurrentHashMap<>();
		this.cacheKeyArray = new ConcurrentHashMap<>();
		this.userNoticeContainer = new ConcurrentHashMap<>();
		initDimCustomCreateType();
		initDimCity();
		initDimScene();
		initDimCityThreadConfig();
		initHotCustomGroupInfo();
		return true;
	}

	public String getNameByKey(String tableName, Object key) {
		return (((Map) this.cacheKVContainer.get(tableName)).get(key) == null) ? ""
				: ((Map) this.cacheKVContainer.get(tableName)).get(key).toString();
	}


	public CopyOnWriteArrayList<String> getNamesByKey(String tableName, List<?> keys) {
		CopyOnWriteArrayList<String> names = new CopyOnWriteArrayList<>();
		for (Object key : keys) {
			names.add(((Map) this.cacheKVContainer.get(tableName)).get(key).toString());
		}
		return names;
	}

	private void initDimCustomCreateType() {
		String sql = "select CREATE_TYPE_ID, CREATE_TYPE_NAME, CREATE_TYPE_DESC from DIM_CUSTOM_CREATE_TYPE";
		List<DimCustomCreateType> createTypeTemp = jdbcTemplate.query(sql,
				(RowMapper) BeanPropertyRowMapper.newInstance(DimCustomCreateType.class));
		CopyOnWriteArrayList<DimCustomCreateType> createTypeList = new CopyOnWriteArrayList<>(createTypeTemp);

		Map<Object, Object> createTypeMap = new ConcurrentHashMap<>();
		CopyOnWriteArrayList<Integer> createTypeKeys = new CopyOnWriteArrayList<>();
		String productMenu = Config.getObject("PRODUCT_MENU");
		String customerAnalysisMenu = Config.getObject("CUSTOMER_ANALYSIS");
		String labelAnalysisMenu = Config.getObject("LABEL_ANALYSIS");

		for (DimCustomCreateType createType : createTypeList) {

			if ("false".equals(customerAnalysisMenu) && (5 == createType.getCreateTypeId().intValue() || 6 == createType

					.getCreateTypeId().intValue() || 2 == createType.getCreateTypeId().intValue()
					|| 10 == createType.getCreateTypeId().intValue())) {
				createTypeList.remove(createType);

				continue;
			}
			if ("false".equals(labelAnalysisMenu)
					&& (3 == createType.getCreateTypeId().intValue() || 3 == createType.getCreateTypeId().intValue())) {
				createTypeList.remove(createType);

				continue;
			}
			createTypeMap.put(createType.getCreateTypeId(), createType.getCreateTypeName());
			createTypeKeys.add(createType.getCreateTypeId());
		}
		this.cacheKVContainer.put("DIM_CUSTOM_CREATE_TYPE", createTypeMap);
		this.cacheListContainer.put("DIM_CUSTOM_CREATE_TYPE", createTypeList);
		this.cacheKeyArray.put("DIM_CUSTOM_CREATE_TYPE", createTypeKeys);
	}


	private void initDimCityThreadConfig() {
		String sql = "select CITY_ID cityId, CITY_NAME cityName, THREAD_NUM threadNum from DIM_CITY_THREAD_CONFIG";
		List<DimCityThreadConfig> cityThreadConfigTemp = jdbcTemplate.query(sql, new BeanPropertyRowMapper<DimCityThreadConfig>(DimCityThreadConfig.class));
				
//				this.jdbcBaseDao.getSimpleJdbcTemplate().query(sql,
//				(RowMapper) BeanPropertyRowMapper.newInstance(DimCityThreadConfig.class));
		CopyOnWriteArrayList<DimCityThreadConfig> cityThreadConfigList = new CopyOnWriteArrayList<>(
				cityThreadConfigTemp);
		Map<Object, Object> cityThreadConfigMap = new ConcurrentHashMap<>();
		CopyOnWriteArrayList<String> cityThreadConfigIds = new CopyOnWriteArrayList<>();
		for (DimCityThreadConfig cityThreadConfig : cityThreadConfigList) {
			cityThreadConfigMap.put(cityThreadConfig.getCityId(), cityThreadConfig.getThreadNum());
			cityThreadConfigIds.add(cityThreadConfig.getCityId());
		}
		this.cacheKVContainer.put("DIM_CITY_THREAD_CONFIG", cityThreadConfigMap);
		this.cacheListContainer.put("DIM_CITY_THREAD_CONFIG", cityThreadConfigList);
		this.cacheKeyArray.put("DIM_CITY_THREAD_CONFIG", cityThreadConfigIds);
	}

	private void initDimCity() {
		String sql = "select CITY_ID cityId, CITY_NAME cityName, CITY_DESC cityDesc, PARENT_ID parentId, COLUMN_ID columnId, SORT_NUM sortNum from DIM_CITY order by SORT_NUM asc";

		List<DimCity> cityTemp = jdbcTemplate.query(sql, new BeanPropertyRowMapper<DimCity>(DimCity.class));
		CopyOnWriteArrayList<DimCity> cityList = new CopyOnWriteArrayList<>(cityTemp);
		Map<Object, Object> cityMap = new ConcurrentHashMap<>();
		CopyOnWriteArrayList<Integer> cityIds = new CopyOnWriteArrayList<>();

		String centerCity = Config.getObject("CENTER_CITYID");
		int centerCityId = Integer.parseInt(centerCity);
		CopyOnWriteArrayList<String> onlyCityIds = new CopyOnWriteArrayList<>();
		for (DimCity city : cityList) {
			cityMap.put(city.getCityId(), city.getCityName());
			cityIds.add(city.getCityId());
			if (city.getParentId().intValue() == centerCityId) {
				onlyCityIds.add(city.getCityId().toString());
			}
		}
		this.cacheKVContainer.put("DIM_CITY", cityMap);
		this.cacheListContainer.put("DIM_CITY", cityList);
		this.cacheKeyArray.put("DIM_CITY", cityIds);
		this.cacheKeyArray.put("CITY_IDS_LIST", onlyCityIds);
	}


	private void initDimScene() {
		String dimSceneSql = "select scene_id,scene_name,scene_desc,SORT_NUM  from DIM_SCENE order by SORT_NUM ";
//		List<DimScene> dimSceneTemp = this.jdbcBaseDao.getSimpleJdbcTemplate().query(dimSceneSql,
//				(RowMapper) BeanPropertyRowMapper.newInstance(DimScene.class));
		List<DimScene> dimSceneTemp = jdbcTemplate.query(dimSceneSql,
				(RowMapper) BeanPropertyRowMapper.newInstance(DimScene.class));
		CopyOnWriteArrayList<DimScene> dimSceneList = new CopyOnWriteArrayList<>(dimSceneTemp);

		Map<Object, Object> dimSceneMap = new ConcurrentHashMap<>();
		CopyOnWriteArrayList<String> dimSceneKeys = new CopyOnWriteArrayList<>();
		for (DimScene dimScene : dimSceneList) {
			dimSceneMap.put(dimScene.getSceneId(), dimScene.getSceneName());
			dimSceneKeys.add(dimScene.getSceneId());
		}
		this.cacheKVContainer.put("DIM_SCENE", dimSceneMap);
		this.cacheListContainer.put("DIM_SCENE", dimSceneList);
		this.cacheKeyArray.put("DIM_SCENE", dimSceneKeys);
	}

//	private void initDimAnnouncementType() {
//		String dimAnnouncementTypeSql = "SELECT TYPE_ID,TYPE_NAME,TYPE_DESC,HAS_SUCCESS_FAIL FROM DIM_ANNOUNCEMENT_TYPE";
//		List<DimAnnouncementType> dimAnnouncementTypeTemp = this.jdbcBaseDao.getSimpleJdbcTemplate().query(
//				dimAnnouncementTypeSql, (RowMapper) BeanPropertyRowMapper.newInstance(DimAnnouncementType.class));
//		CopyOnWriteArrayList<DimAnnouncementType> dimAnnouncementTypeList = new CopyOnWriteArrayList<>(
//				dimAnnouncementTypeTemp);
//
//		Map<Object, Object> dimAnnouncementTypeMap = new ConcurrentHashMap<>();
//		CopyOnWriteArrayList<Integer> dimAnnouncementTypeKeys = new CopyOnWriteArrayList<>();
//		for (DimAnnouncementType dimAnnouncementType : dimAnnouncementTypeList) {
//			dimAnnouncementTypeMap.put(dimAnnouncementType.getTypeId(), dimAnnouncementType.getTypeName());
//			dimAnnouncementTypeKeys.add(dimAnnouncementType.getTypeId());
//		}
//		this.cacheKVContainer.put("DIM_ANNOUNCEMENT_TYPE", dimAnnouncementTypeMap);
//		this.cacheListContainer.put("DIM_ANNOUNCEMENT_TYPE", dimAnnouncementTypeList);
//		this.cacheKeyArray.put("DIM_ANNOUNCEMENT_TYPE", dimAnnouncementTypeKeys);
//	}

//	private void initDimListTactics() {
//		String dimListTacticsSql = "SELECT TACTICS_ID,TACTICS_NAME,DESC_TXT FROM DIM_LIST_TACTICS";
//		List<DimListTactics> dimAnnouncementTypeTemp = this.jdbcBaseDao.getSimpleJdbcTemplate().query(dimListTacticsSql,
//				(RowMapper) BeanPropertyRowMapper.newInstance(DimListTactics.class));
//		CopyOnWriteArrayList<DimListTactics> dimListTacticsList = new CopyOnWriteArrayList<>(dimAnnouncementTypeTemp);
//
//		Map<Object, Object> dimListTacticsMap = new ConcurrentHashMap<>();
//		CopyOnWriteArrayList<String> dimListTacticsKeys = new CopyOnWriteArrayList<>();
//		for (DimListTactics dimListTactics : dimListTacticsList) {
//			dimListTacticsMap.put(dimListTactics.getTacticsId(), dimListTactics.getTacticsName());
//			dimListTacticsKeys.add(dimListTactics.getTacticsId());
//		}
//		this.cacheKVContainer.put("DIM_LIST_TACTICS", dimListTacticsMap);
//		this.cacheListContainer.put("DIM_LIST_TACTICS", dimListTacticsList);
//		this.cacheKeyArray.put("DIM_LIST_TACTICS", dimListTacticsKeys);
//	}

//	public void initMdaSysTable() {
//		String sql = "select TABLE_ID,TABLE_NAME,TABLE_CN_NAME,TABLE_DESC,TABLE_POSTFIX,TABLE_SCHEMA,TABLE_TYPE,UPDATE_CYCLE,TABLE_BELONG  from CI_MDA_SYS_TABLE";
//
//		List<CiMdaSysTable> sysTableList = this.jdbcBaseDao.getSimpleJdbcTemplate().query(sql,
//				(RowMapper) BeanPropertyRowMapper.newInstance(CiMdaSysTable.class));
//		Map<Object, Object> sysTableMap = new HashMap<>();
//		CopyOnWriteArrayList<Integer> sysTableKeys = new CopyOnWriteArrayList<>();
//		CopyOnWriteArrayList<CiMdaSysTable> copySysTableList = new CopyOnWriteArrayList<>(sysTableList);
//
//		for (CiMdaSysTable sysTable : sysTableList) {
//			sysTableMap.put(sysTable.getTableId(), sysTable.getTableName());
//			sysTableKeys.add(sysTable.getTableId());
//		}
//		this.cacheKVContainer.put("CI_SYS_TABLE_MAP", sysTableMap);
//		this.cacheListContainer.put("CI_SYS_TABLE_MAP", copySysTableList);
//		this.cacheKeyArray.put("CI_SYS_TABLE_MAP", sysTableKeys);
//	}

//	private void initDimLabelType() {
//		String dimLabelTypeSql = " select LABEL_TYPE_ID,LABEL_TYPE_NAME,LABEL_TYPE_DESC  from DIM_LABEL_TYPE ";
//		List<DimLabelType> dimLabelTypeTemp = this.jdbcBaseDao.getSimpleJdbcTemplate().query(dimLabelTypeSql,
//				(RowMapper) BeanPropertyRowMapper.newInstance(DimLabelType.class));
//		CopyOnWriteArrayList<DimLabelType> dimLabelTypeList = new CopyOnWriteArrayList<>(dimLabelTypeTemp);
//
//		Map<Object, Object> dimLabelTypeMap = new ConcurrentHashMap<>();
//		CopyOnWriteArrayList<Integer> dimLabelTypeKeys = new CopyOnWriteArrayList<>();
//		for (DimLabelType dimLabelType : dimLabelTypeList) {
//			dimLabelTypeMap.put(dimLabelType.getLabelTypeId(), dimLabelType.getLabelTypeName());
//			dimLabelTypeKeys.add(dimLabelType.getLabelTypeId());
//		}
//		this.cacheKVContainer.put("DIM_LABEL_TYPE", dimLabelTypeMap);
//		this.cacheListContainer.put("DIM_LABEL_TYPE", dimLabelTypeList);
//		this.cacheKeyArray.put("DIM_LABEL_TYPE", dimLabelTypeKeys);
//	}

//	public void initAllLabelColumnName() {
//		StringBuffer allLabelColumnNameSql = new StringBuffer();
//		allLabelColumnNameSql.append(
//				"SELECT L.LABEL_ID,C.COLUMN_NAME FROM CI_LABEL_INFO L,CI_LABEL_EXT_INFO E,CI_MDA_SYS_TABLE_COLUMN C ")
//				.append("WHERE L.LABEL_ID = E.LABEL_ID AND C.COLUMN_ID = E.COLUMN_ID");
//		List<Map<String, Object>> list = this.jdbcBaseDao.getSimpleJdbcTemplate()
//				.queryForList(allLabelColumnNameSql.toString());
//		Map<Object, Object> allLabelColumnNameMap = new ConcurrentHashMap<>();
//		for (Map<String, Object> map : list) {
//			allLabelColumnNameMap.put(map.get("LABEL_ID"), map.get("COLUMN_NAME"));
//		}
//		this.cacheKVContainer.put("TABEL_ALL_LABEL_COLUMN_NAME", allLabelColumnNameMap);
//	}



//	private CiLabelInfo getEffectiveLabelInner(String key) {
//		return (CiLabelInfo) ((Map) this.cacheKVContainer.get("ALL_EFFECTIVE_LABEL_MAP")).get("LABEL_" + key);
//	}

//	public CiLabelInfo getEffectiveLabelByUser(String key, String userId) {
//		CiLabelInfo labelInfo = (CiLabelInfo) ((Map) this.cacheKVContainer.get("ALL_EFFECTIVE_LABEL_MAP"))
//				.get("LABEL_" + key);
//
//		try {
//			String noCity = Configure.getInstance().getProperty("NO_CITY");
//			String centerCity = Configure.getInstance().getProperty("CENTER_CITYID");
//			if (labelInfo.getLabelTypeId() != null && labelInfo.getLabelTypeId().intValue() == 3 && dimCitySet != null
//					&& dimCitySet.size() > 0 && labelInfo.getCiLabelExtInfo().getCiMdaSysTableColumn() != null
//					&& StringUtil.isNotEmpty(labelInfo.getCiLabelExtInfo().getCiMdaSysTableColumn().getDimTransId())
//					&& dimCitySet.contains(
//							labelInfo.getCiLabelExtInfo().getCiMdaSysTableColumn().getDimTransId().toUpperCase())) {
//
//				List<String> cities = PrivilegeServiceUtil.getAllUserCityIds(userId);
//				if (!cities.contains(noCity) && !cities.contains(centerCity)
//						&& !cities.contains(labelInfo.getCiLabelExtInfo().getAttrVal())) {
//					CiLabelInfo newLabel = labelInfo.clone();
//					newLabel.getCiLabelExtInfo().setIsStatUserNum(Integer.valueOf(0));
//					return newLabel;
//				}
//
//			}
//		} catch (Exception e) {
//			this.log.error(e);
//			e.printStackTrace();
//		}
//		return labelInfo;
//	}
//
//	public CiLabelInfo getEffectiveLabel(String key) {
//		return (CiLabelInfo) ((Map) this.cacheKVContainer.get("ALL_EFFECTIVE_LABEL_MAP")).get("LABEL_" + key);
//	}
//
//
//	public CiLabelInfo getCityLabel(String key) {
//		return (CiLabelInfo) ((Map) this.cacheKVContainer.get("CITY_LABEL_MAP")).get("CITY_" + key);
//	}
//
	public Integer getCityThreadConfigNum(String cityId) {
		return (Integer) ((Map) this.cacheKVContainer.get("DIM_CITY_THREAD_CONFIG")).get(cityId);
	}
//
	//TODO
	public Map<Object, Object> getCityThreadConfigMap() {
		return this.cacheKVContainer.get("DIM_CITY_THREAD_CONFIG");
	}
	public String getDimScene(String key) {
		return (String) ((Map) this.cacheKVContainer.get("DIM_SCENE")).get(key);
	}
	private void initHotCustomGroupInfo() {
		try {
			Pager pager = new Pager();
			pager.setPageNum(1);
			pager.setPageSize(ServiceConstants.SHOW_RECOMMEND_CUSTOMS_NUM);
			pager.setOrder("desc");
			pager.setOrderBy("USECOUNT_HOT");
			CiCustomGroupInfo customGroupInfo = new CiCustomGroupInfo();
			customGroupInfo.setDateType("3");
			customGroupInfo.setIsPrivate(Integer.valueOf(0));
			List<CiCustomGroupInfo> hotCustomGroupInfos = getUserUseCustomsersList(pager, customGroupInfo,ServiceConstants.CUSTOM_GROUP_QUERY_TYPE_CALL_BY_BACK, "1");
			Map<Object, Object> hotCustomGroupInfoMap = new ConcurrentHashMap<>();
			for (CiCustomGroupInfo groupInfo : hotCustomGroupInfos) {
				hotCustomGroupInfoMap.put(groupInfo.getCustomGroupId(), groupInfo);
			}
			this.cacheKVContainer.put("HOT_CUSTOMS", hotCustomGroupInfoMap);
			this.log.info("initHotCustomGroupInfo success");
		} catch (Exception e) {
			this.log.error("initCustomerGroupInfo error", e);
		}
	}
	
	public List<CiCustomGroupInfo> getUserUseCustomsersList(Pager pager,
			CiCustomGroupInfo bean, String queryType, String userId) throws Exception {
	StringBuffer sql = new StringBuffer();
	String sqlPage;
	sql.append("SELECT CCGI.* ,CASE WHEN T3.SUMCC IS NULL THEN 0 ELSE T3.SUMCC END USE_TIMES");
	if(queryType == null || !ServiceConstants.CUSTOM_GROUP_QUERY_TYPE_CALL_BY_BACK.equals(queryType)){
		sql.append(", CASE WHEN T4.SUMCC IS NULL THEN 'false' ELSE 'true' END ISATTENTION ");
	}
	sql.append(" FROM CI_CUSTOM_GROUP_INFO CCGI LEFT JOIN (SELECT  T1.CUSTOM_ID,COUNT(T1.CUSTOM_ID) SUMCC FROM CI_USER_USE_CUSTOM T1 WHERE ");
	if(StringUtil.isNotEmpty(bean.getDateType())){
		Date date = new Date();
		String startTime = "";
		String endTime = "";
		
		if (ServiceConstants.CUSTOM_ONE_7D.equals(bean.getDateType())) {
			String today = DateUtil.date2String(date,"yyyy-MM-dd");
			String startDay=DateUtil.getFrontDay(6, today,"yyyy-MM-dd");
			startTime = this.getDataBaseAdapter().getTimeStamp(startDay,"00", "00", "00");
			endTime = this.getDataBaseAdapter().getTimeStamp(today, "23","59", "59");
			sql.append(" T1.USE_TIME >= "+ startTime+ " AND T1.USE_TIME<="+ endTime + " AND ");
		}
		
		if (ServiceConstants.CUSTOM_ONE_M.equals(bean.getDateType())) {
			String today = DateUtil.date2String(date,"yyyy-MM-dd");
			String startDay=DateUtil.getFrontDay(30, today,"yyyy-MM-dd");
			
			startTime = this.getDataBaseAdapter().getTimeStamp(startDay,"00", "00", "00");
			endTime = this.getDataBaseAdapter().getTimeStamp(today, "23","59", "59");
			sql.append(" T1.USE_TIME >= "+ startTime+ " AND T1.USE_TIME<="+ endTime + " AND ");
		}
		
		if (ServiceConstants.CUSTOM_THREE_M.equals(bean.getDateType())) {
			String today = DateUtil.date2String(date,"yyyy-MM-dd");
			String startDay=DateUtil.getFrontDay(90, today,"yyyy-MM-dd");
			
			startTime = this.getDataBaseAdapter().getTimeStamp(startDay,"00", "00", "00");
			endTime = this.getDataBaseAdapter().getTimeStamp(today, "23","59", "59");
			sql.append(" T1.USE_TIME >= "+ startTime+ " AND T1.USE_TIME<="+ endTime + " AND ");
		}
		
	}
	sql.append(" T1.IS_TEMPLATE = " + ServiceConstants.USE_COSTOMER + " GROUP BY T1.CUSTOM_ID) T3 ON CCGI.CUSTOM_GROUP_ID = T3.CUSTOM_ID");
	if(queryType == null || !ServiceConstants.CUSTOM_GROUP_QUERY_TYPE_CALL_BY_BACK.equals(queryType)){
		sql.append(" LEFT JOIN (SELECT  T11.USER_ID,T11.ATTENTION_TIME,T11.CUSTOM_GROUP_ID,COUNT(T11.CUSTOM_GROUP_ID) SUMCC FROM CI_USER_ATTENTION_CUSTOM T11 WHERE T11.USER_ID = '" + userId + "' GROUP BY T11.CUSTOM_GROUP_ID,T11.USER_ID,T11.ATTENTION_TIME) T4 ON CCGI.CUSTOM_GROUP_ID = T4.CUSTOM_GROUP_ID");
	}
	sql.append(" WHERE 1=1").append(" AND STATUS = ")
	.append(ServiceConstants.CICUSTOMGROUPINFO_STATUS_VALIDATE);
	//修改时间
	if(StringUtil.isNotEmpty(bean.getModifyStartDate())){
		sql.append(" AND NEW_MODIFY_TIME>= :modifyStartDate");
	}
	if(StringUtil.isNotEmpty(bean.getModifyEndDate())){
		sql.append(" AND NEW_MODIFY_TIME<= :modifyEndDate");
	}
	//客户群类型
	if(StringUtil.isNotEmpty(bean.getCustomGroupBelong())){
		sql.append(" AND  CUSTOM_GROUP_BELONG = :customGroupBelong");
	}
	// 查询客户群名称
	String dbType = Config.getObject("CI_DBTYPE");// 数据库类型
	if (StringUtil.isNotEmpty(bean.getCustomGroupName())) {
		if (DataBaseAdapter.DBMS_DB2.equalsIgnoreCase(dbType)) {
			sql.append(" AND (")
			.append(" (CUSTOM_GROUP_NAME LIKE '%'||")
			.append(":customGroupName")
			.append("||'%')")
			.append(" OR (CUSTOM_GROUP_DESC LIKE '%'||")
			.append(":customGroupName")
			.append("||'%')")
			.append(" OR (PROD_OPT_RULE_SHOW LIKE '%'||")
			.append(":customGroupName")
			.append("||'%')")
			.append(" OR (LABEL_OPT_RULE_SHOW LIKE '%'||")
			.append(":customGroupName")
			.append("||'%')")
			.append(" OR (CUSTOM_OPT_RULE_SHOW LIKE '%'||")
			.append(":customGroupName").append("||'%')")
			.append(" OR (KPI_DIFF_RULE LIKE '%'||")
			.append(":customGroupName").append("||'%')").append(" ) ");
		}else{
			sql.append(" AND (")
			.append(" (LOWER(CUSTOM_GROUP_NAME) LIKE LOWER('%'||")
			.append(":customGroupName")
			.append("||'%'))")
			.append(" OR (LOWER(CUSTOM_GROUP_DESC) LIKE LOWER('%'||")
			.append(":customGroupName")
			.append("||'%'))")
			.append(" OR (LOWER(PROD_OPT_RULE_SHOW) LIKE LOWER('%'||")
			.append(":customGroupName")
			.append("||'%'))")
			.append(" OR (LOWER(LABEL_OPT_RULE_SHOW) LIKE LOWER('%'||")
			.append(":customGroupName")
			.append("||'%'))")
			.append(" OR (LOWER(CUSTOM_OPT_RULE_SHOW) LIKE LOWER('%'||")
			.append(":customGroupName").append("||'%'))")
			.append(" OR (LOWER(KPI_DIFF_RULE) LIKE LOWER('%'||")
			.append(":customGroupName").append("||'%'))").append(" ) ");
		}
	}
	// 查询创建时间
	if (StringUtil.isNotEmpty(bean.getCreateTime())) {
		sql.append(" AND CREATE_TIME = :createTime");
	}
	if (bean.getDataStatus() != null) {
		sql.append(" AND DATA_STATUS = :dataStatus");
	}
	// 查询客户数规模
	if(StringUtil.isNotEmpty(bean.getMinCustomNum())){
		sql.append(" AND CUSTOM_NUM>= :minCustomNum");
	}
	if(StringUtil.isNotEmpty(bean.getMaxCustomNum())){
		sql.append(" AND CUSTOM_NUM<= :maxCustomNum");
	}
	//查询应用场景
	String sceneIds = bean.getSceneId();
	String sqlSceneIds = "";
	if(null != sceneIds && sceneIds.length()>=1){
		sceneIds = sceneIds.substring(0, sceneIds.length()-1);
		String[] sceneIdsArr = sceneIds.split(",");
		for (String sceneId : sceneIdsArr) {
			sqlSceneIds += " or senceT.scene_id='"+sceneId+"' ";
		}
	}
	if(StringUtil.isNotEmpty(bean.getSceneId())){
		sql.append(" AND CCGI.CUSTOM_GROUP_ID IN  ( select  senceT.custom_group_id from ci_custom_scene_rel " +
				"senceT  where senceT.status=1 ");
		sql.append(" and ( senceT.scene_id='");
		sql.append(ServiceConstants.ALL_SCENE_ID+"'");
		sql.append(sqlSceneIds);
		sql.append(" ))");
	}
	// 按生成周期
	if (StringUtil.isNotEmpty(bean.getUpdateCycle())) {
		sql.append(" AND update_cycle = :updateCycle");
	}
	//不含清单且含有下线标签的客户群 无操作权限 (客户群集市、客户群收藏页面)
	if(StringUtil.isEmpty(bean.getIsMyCustom()) || !bean.getIsMyCustom().equals("true")){
		sql.append(" AND (UPDATE_CYCLE != "+ServiceConstants.CUSTOM_CYCLE_TYPE_N+" or (IS_LABEL_OFFLINE is null or IS_LABEL_OFFLINE = "+ServiceConstants.CICUSTOMGROUPINFO_IS_ONLINE+") )");
	}
	//浙江版本客户群、模板融合在一起了,没有模板功能,按照共享客户群所有人都可见过滤； 其他客户群、模板分开的，按照共享客户群只有所在地市可见过滤；
	String templateMenu = Config.getObject("TEMPLATE_MENU");
	if(templateMenu.equals("false")){
		// 按创建人查询,共享客户群所有人都可见
		if (StringUtil.isNotEmpty(bean.getCreateUserId())) {
			sql.append(" AND (IS_PRIVATE = "+ServiceConstants.CICUSTOMGROUPINFO_IS_PUBLIC+"  or ( IS_PRIVATE = "
					+ServiceConstants.CICUSTOMGROUPINFO_IS_PRIVATE
					+"  AND  CREATE_USER_ID = :createUserId ) ) ");
		}else if(StringUtil.isNotEmpty(bean.getCreateCityId())){//用户为空,地市不为空,地市管理员
			sql.append(" AND (IS_PRIVATE = "+ServiceConstants.CICUSTOMGROUPINFO_IS_PUBLIC+"  or ( IS_PRIVATE = "
					+ServiceConstants.CICUSTOMGROUPINFO_IS_PRIVATE
					+"  AND  CREATE_CITY_ID = :createCityId ) ) ");
		}
		
	}else{
		// 按创建人查询自己创建的与所在地市下的所有共享客户群
		String root=Config.getObject("CENTER_CITYID");
		if (StringUtil.isNotEmpty(bean.getCreateUserId())) {
			sql.append(" AND ( CREATE_USER_ID = :createUserId or ( IS_PRIVATE = "
					+ServiceConstants.CICUSTOMGROUPINFO_IS_PUBLIC
					+"  AND  ( CREATE_CITY_ID = :createCityId or  CREATE_CITY_ID = "+root
					+" ) ) ) ");
		}else if(StringUtil.isNotEmpty(bean.getCreateCityId())){//用户为空,地市不为空,地市管理员
			sql.append(" AND ( CREATE_CITY_ID = :createCityId or ( IS_PRIVATE = "
					+ServiceConstants.CICUSTOMGROUPINFO_IS_PUBLIC
					+"  AND   CREATE_CITY_ID = "+root
					+"  ) ) ");
		}
		
	}
	//按创建地市
	/*if (StringUtil.isNotEmpty(bean.getCreateUserId())) {
		
		if(StringUtil.isNotEmpty(bean.getCreateCityId())){
			sql.append(" AND CREATE_CITY_ID = :createCityId");
		}
	}*/
	//按是否私有
	if(StringUtil.isNotEmpty(bean.getIsPrivate())){
		sql.append(" AND IS_PRIVATE = :isPrivate");
	}
	// 按创建日期范围
	if (StringUtil.isNotEmpty(bean.getStartDate()) || StringUtil.isNotEmpty(bean.getEndDate())) {
		SimpleDateFormat df = new SimpleDateFormat(CommonConstants.DATE_FORMAT_YYYY_MM_DD_HHMMSS);
		SimpleDateFormat format = new SimpleDateFormat(CommonConstants.DATE_FORMAT_YYYY_MM_DD);
		if (StringUtil.isNotEmpty(bean.getStartDate())) {
			String startStr = format.format(bean.getStartDate());
			Date startDate = df.parse(startStr + " 00:00:00");
			bean.setStartDate(startDate);
			sql.append(" AND CREATE_TIME >= :startDate");
		}
		if (StringUtil.isNotEmpty(bean.getEndDate())) {
			String endStr = format.format(bean.getEndDate());
			Date endDate = df.parse(endStr + " 23:59:59");
			bean.setEndDate(endDate);
			sql.append(" AND CREATE_TIME <= :endDate");
		}
	}
	// 查询创建类型
	if (bean.getCreateTypeId() != null && bean.getCreateTypeId().intValue() != 0) {
		sql.append(" AND CREATE_TYPE_ID = :createTypeId");
	}
	// 查询使用的标签
	if (StringUtil.isNotEmpty(bean.getLabelName())) {
		sql.append(" AND CCGI.CUSTOM_GROUP_ID IN ").append("( ").append(" SELECT R.CUSTOM_ID ")
		.append(" FROM CI_LABEL_INFO L,CI_LABEL_RULE R ").append(" WHERE R.ELEMENT_TYPE = 2 ")
		.append(" AND R.CALCU_ELEMENT = ").append(this.getDataBaseAdapter().getIntToChar("L.LABEL_ID"))
		.append(" AND L.LABEL_NAME LIKE '%").append(bean.getLabelName()).append("%')");
	}
	// 查询使用的模板
	if (StringUtil.isNotEmpty(bean.getTemplateName())) {
		sql.append(" AND TEMPLATE_ID IN ").append("( ").append(" SELECT TEMPLATE_ID")
		.append(" FROM CI_TEMPLATE_INFO").append(" WHERE TEMPLATE_NAME LIKE '%")
		.append(bean.getTemplateName()).append("%')");
	}
	
	if(StringUtils.isNotEmpty(queryType) && ServiceConstants.CUSTOM_GROUP_QUERY_TYPE_USER_ATTENTION.equals(queryType)){
		sql.append(" AND  T4.USER_ID = '").append(userId).append("' ");
		//sql.append(" ORDER BY T4.ATTENTION_TIME DESC");
	}

	//order by sql 拼接
	if (StringUtil.isEmpty(pager.getOrderBy())) {
		sql.append(" ORDER BY NEW_MODIFY_TIME DESC");
	} else {
		String[] orderByArr = pager.getOrderBy().split(",");
		String[] orderArr = pager.getOrder().split(",");
		StringBuffer sf = new StringBuffer("");
		if (orderByArr.length == orderArr.length) {
			for (int i = 0; i < orderByArr.length; i ++) {
				if (i == orderByArr.length - 1) {
					//按人气排序的时候 ,先排人气后排系统推荐
					if (orderByArr[i].equals("USECOUNT")) {
						String orderSql = "USE_TIMES";
						sf.append("IS_SYS_RECOM " + orderArr[i]);
						sf.append(","+orderSql + " " + orderArr[i]);
					} else if (orderByArr[i].equals("USECOUNT_HOT")) {//热客户群排行榜,只按人气排序
						String orderSql = "USE_TIMES";
						sf.append(orderSql + " " + orderArr[i]);
					} else {
						sf.append(orderByArr[i] + " " + orderArr[i]);
					}
				} else {
					//按人气排序的时候 ,先排人气后排系统推荐
					if (orderByArr[i].equals("USECOUNT")) {
						String orderSql = "USE_TIMES";
						sf.append("IS_SYS_RECOM " + orderArr[i] + ",");
						sf.append(orderSql + " " + orderArr[i] + ",");
					} if (orderByArr[i].equals("USECOUNT_HOT")) {//热客户群排行榜,只按人气排序
						String orderSql = "USE_TIMES";
						sf.append(orderSql + " " + orderArr[i] + ",");
					} else {
						sf.append(orderByArr[i] + " " + orderArr[i] + ",");
					}
				}
			}
		} else {
			throw new CIServiceException("排序字段和排序方式个数不匹配！");
		}
		sql.append(" ORDER BY " + sf.toString());
	}
	
	if (pager.getPageSize() != 0) {
		sqlPage = this.getDataBaseAdapter().getPagedSql(sql.toString(), pager.getPageNum(), pager.getPageSize());
	} else {
		sqlPage = sql.toString();
	}
	log.debug("sql=" + sqlPage);
	return this.getNamedParameterJdbcTemplate().query(sqlPage,
			new BeanPropertySqlParameterSource(bean),
			BeanPropertyRowMapper.newInstance(CiCustomGroupInfo.class));
}

	public DataBaseAdapter getDataBaseAdapter() {
		if (dataBaseAdapter == null) {
			dataBaseAdapter = getDataBaseAdapter(Config.getObject("CI_DBTYPE"));
		}
		return dataBaseAdapter;
	}
	public DataBaseAdapter getDataBaseAdapter(String dbType) {
		return new DataBaseAdapter(dbType);
	}
	
	private NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public CiCustomGroupInfo getHotCustomByKey(String name, Object key) {
		return (CiCustomGroupInfo) ((Map) this.cacheKVContainer.get(name)).get(key);
	}
	public Map<Object, Object> getCityMap() {
		return this.cacheKVContainer.get("DIM_CITY");
	}

	public <R> R get(String name, String key, Class<R> type) {
		Map<Object, Object> dataMap = this.cacheKVContainer.get(name);
		if (dataMap != null && key != null) {
			return (R) dataMap.get(key);
		}
		return null;
	}

	public synchronized void put(String name, String key, Object value) {
		Map<Object, Object> dataMap = this.cacheKVContainer.get(name);
		if (dataMap == null) {
			dataMap = new HashMap<>();
			this.cacheKVContainer.put(name, dataMap);
		}
		if (key != null) {
			if (value == null) {
				dataMap.remove(key);
			} else {
				dataMap.put(key, value);
			}
		}
	}
}
