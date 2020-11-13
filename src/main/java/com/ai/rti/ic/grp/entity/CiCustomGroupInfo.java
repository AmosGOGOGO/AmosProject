 package  com.ai.rti.ic.grp.entity;
 
 import java.io.Serializable;
 import java.util.Date;
 

 public class CiCustomGroupInfo
   implements Serializable, Cloneable
 {
   private static final long serialVersionUID = 1L;
   private static final int RULE_MAX_LENGTH = 220;
   private String dataSource;
   private int tarGrpId;
   private String customGroupId;
   private String customGroupName;
   private String customGroupDesc;
   private String parentCustomId;
   private Integer updateCycle;
   private Integer createTypeId;
   private String createUserId;
   private Date createTime;
   private String prodOptRuleShow;
   private String labelOptRuleShow;
   private String customOptRuleShow;
   private String kpiDiffRule;
   private Integer status;
   private Integer dataStatus;
   private Date startDate;
   private Date endDate;
   private Long customNum;
   private Date dataTime;
   private String dataDate;
   private String templateId;
   private Integer productAutoMacthFlag;
   private Integer isPrivate;
   private String createCityId;
   private Date newModifyTime;
   private Integer isLabelOffline;
   private String thumbnailData;
   private Integer isSysRecom;
   private Integer isContainLocalList;
   private String monthLabelDate;
   private String dayLabelDate;
   private String tacticsId;
   private Integer isFirstFailed;
   private Long duplicateNum;
   private Integer isHasList;
   private String listCreateTime;
   private Integer customListCreateFailed;
   private String serverId;
   private String offsetDate;
   private Integer listMaxNum;
   private String sysId;
   private String ruleExpress;
   private Integer customGroupBelong;
   private Integer customGroupDataType = Integer.valueOf(0);
   
   private Integer customGroupNetType;
   
   private Integer separatorId;
   
   private String sceneId;
   
   private String customSceneNames;
   
   private String createTimeView;
   
   private String labelName;
   
   private String templateName;
   private String dateType;
   private String createType;
   private String createUserName;
   private String updateCycleStr;
   private String dataStatusStr;
   private String year;
   private String month;
   private String day;
   private String listTableName;
   private Integer sysMatchStatus;
   private boolean isAlarm;
   private Date modifyTime;
   private String customMatch;
   private String productsStr;
   private String productDate;
   private String productsIdsStr;
   private boolean precisionMarketing;
   private boolean saveTemplate;
   private String returnPage;
   private int failedListCount;
   private String customerAnalysisMenu;
   private Integer oldIsHasList;
   private String isSysRecommendTime;
   private String simpleRule;
   private boolean isOverlength;
   private int isPush;
   private String isAttention = "false";
   
   private String startDateStr;
   
   private String endDateStr;
   
   private Date modifyStartDate;
   
   private Date modifyEndDate;
   private Long minCustomNum;
   private Long maxCustomNum;
   private String isHotCustom = "false";
   
   private String tabelName;
   
   private String whereSql;
   
   private int useTimes;
   
   private String cityId;
   private String createCityName;
   private String isMyCustom = "false";
   private String userId;
   private String isMcdNeed = "false";
   
   private Integer lastDataStatus;
   
   private String dayDataDate;
   
   private String dataDateStr;
   
   private Double avgScore;
   
   private String customGroupBelongName;
   
   private Integer taskId;
 
   
   public String getDataSource() {
     return this.dataSource;
   }
   
   public void setDataSource(String dataSource) {
     this.dataSource = dataSource;
   }
   
   public int getTarGrpId() {
     return this.tarGrpId;
   }
   
   public void setTarGrpId(int tarGrpId) {
     this.tarGrpId = tarGrpId;
   }
   
   public String getCustomGroupId() {
     return this.customGroupId;
   }
   
   public void setCustomGroupId(String customGroupId) {
     this.customGroupId = customGroupId;
   }
   
   public String getCustomGroupName() {
     return this.customGroupName;
   }
   
   public void setCustomGroupName(String customGroupName) {
     this.customGroupName = customGroupName;
   }
   
   public String getCustomGroupDesc() {
     return this.customGroupDesc;
   }
   
   public void setCustomGroupDesc(String customGroupDesc) {
     this.customGroupDesc = customGroupDesc;
   }
   
   public String getParentCustomId() {
     return this.parentCustomId;
   }
   
   public void setParentCustomId(String parentCustomId) {
     this.parentCustomId = parentCustomId;
   }
   
   public Integer getUpdateCycle() {
     return this.updateCycle;
   }
   
   public void setUpdateCycle(Integer updateCycle) {
     this.updateCycle = updateCycle;
   }
   
   public Integer getCreateTypeId() {
     return this.createTypeId;
   }
   
   public void setCreateTypeId(Integer createTypeId) {
     this.createTypeId = createTypeId;
   }
   
   public String getCreateUserId() {
     return this.createUserId;
   }
   
   public void setCreateUserId(String createUserId) {
     this.createUserId = createUserId;
   }
   
   public Date getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(Date createTime) {
     this.createTime = createTime;
   }
   
   public String getProdOptRuleShow() {
     return this.prodOptRuleShow;
   }
   
   public void setProdOptRuleShow(String prodOptRuleShow) {
     this.prodOptRuleShow = prodOptRuleShow;
   }
   
   public String getLabelOptRuleShow() {
     return this.labelOptRuleShow;
   }
   
   public void setLabelOptRuleShow(String labelOptRuleShow) {
     this.labelOptRuleShow = labelOptRuleShow;
   }
   
   public String getCustomOptRuleShow() {
     return this.customOptRuleShow;
   }
   
   public void setCustomOptRuleShow(String customOptRuleShow) {
     this.customOptRuleShow = customOptRuleShow;
   }
   
   public String getKpiDiffRule() {
     return this.kpiDiffRule;
   }
   
   public void setKpiDiffRule(String kpiDiffRule) {
     this.kpiDiffRule = kpiDiffRule;
   }
   
   public Integer getStatus() {
     return this.status;
   }
   
   public void setStatus(Integer status) {
     this.status = status;
   }
   
   public Integer getDataStatus() {
     return this.dataStatus;
   }
   
   public void setDataStatus(Integer dataStatus) {
     this.dataStatus = dataStatus;
   }
   
   public Date getStartDate() {
     return this.startDate;
   }
   
   public void setStartDate(Date startDate) {
     this.startDate = startDate;
   }
   
   public Date getEndDate() {
     return this.endDate;
   }
   
   public void setEndDate(Date endDate) {
     this.endDate = endDate;
   }
   
   public Long getCustomNum() {
     return this.customNum;
   }
   
   public void setCustomNum(Long customNum) {
     this.customNum = customNum;
   }
   
   public Date getDataTime() {
     return this.dataTime;
   }
   
   public void setDataTime(Date dataTime) {
     this.dataTime = dataTime;
   }
   
   public String getDataDate() {
     return this.dataDate;
   }
   
   public void setDataDate(String dataDate) {
     this.dataDate = dataDate;
   }
   
   public String getTemplateId() {
     return this.templateId;
   }
   
   public void setTemplateId(String templateId) {
     this.templateId = templateId;
   }
   
   public Integer getProductAutoMacthFlag() {
     return this.productAutoMacthFlag;
   }
   
   public void setProductAutoMacthFlag(Integer productAutoMacthFlag) {
     this.productAutoMacthFlag = productAutoMacthFlag;
   }
   
   public Integer getIsPrivate() {
     return this.isPrivate;
   }
   
   public void setIsPrivate(Integer isPrivate) {
     this.isPrivate = isPrivate;
   }
   
   public String getCreateCityId() {
     return this.createCityId;
   }
   
   public void setCreateCityId(String createCityId) {
     this.createCityId = createCityId;
   }
   
   public Date getNewModifyTime() {
     return this.newModifyTime;
   }
   
   public void setNewModifyTime(Date newModifyTime) {
     this.newModifyTime = newModifyTime;
   }
   
   public Integer getIsLabelOffline() {
     return this.isLabelOffline;
   }
   
   public void setIsLabelOffline(Integer isLabelOffline) {
     this.isLabelOffline = isLabelOffline;
   }
   
   public String getThumbnailData() {
     return this.thumbnailData;
   }
   
   public void setThumbnailData(String thumbnailData) {
     this.thumbnailData = thumbnailData;
   }
   
   public Integer getIsSysRecom() {
     return this.isSysRecom;
   }
   
   public void setIsSysRecom(Integer isSysRecom) {
     this.isSysRecom = isSysRecom;
   }
   
   public Integer getIsContainLocalList() {
     return this.isContainLocalList;
   }
   
   public void setIsContainLocalList(Integer isContainLocalList) {
     this.isContainLocalList = isContainLocalList;
   }
   
   public String getMonthLabelDate() {
     return this.monthLabelDate;
   }
   
   public void setMonthLabelDate(String monthLabelDate) {
     this.monthLabelDate = monthLabelDate;
   }
   
   public String getDayLabelDate() {
     return this.dayLabelDate;
   }
   
   public void setDayLabelDate(String dayLabelDate) {
     this.dayLabelDate = dayLabelDate;
   }
   
   public String getTacticsId() {
     return this.tacticsId;
   }
   
   public void setTacticsId(String tacticsId) {
     this.tacticsId = tacticsId;
   }
   
   public Integer getIsFirstFailed() {
     return this.isFirstFailed;
   }
   
   public void setIsFirstFailed(Integer isFirstFailed) {
     this.isFirstFailed = isFirstFailed;
   }
   
   public Long getDuplicateNum() {
     return this.duplicateNum;
   }
   
   public void setDuplicateNum(Long duplicateNum) {
     this.duplicateNum = duplicateNum;
   }
   
   public Integer getIsHasList() {
     return this.isHasList;
   }
   
   public void setIsHasList(Integer isHasList) {
     this.isHasList = isHasList;
   }
   
   public String getListCreateTime() {
     return this.listCreateTime;
   }
   
   public void setListCreateTime(String listCreateTime) {
     this.listCreateTime = listCreateTime;
   }
   
   public Integer getCustomListCreateFailed() {
     return this.customListCreateFailed;
   }
   
   public void setCustomListCreateFailed(Integer customListCreateFailed) {
     this.customListCreateFailed = customListCreateFailed;
   }
   
   public String getServerId() {
     return this.serverId;
   }
   
   public void setServerId(String serverId) {
     this.serverId = serverId;
   }
   
   public String getOffsetDate() {
     return this.offsetDate;
   }
   
   public void setOffsetDate(String offsetDate) {
     this.offsetDate = offsetDate;
   }
   
   public Integer getListMaxNum() {
     return this.listMaxNum;
   }
   
   public void setListMaxNum(Integer listMaxNum) {
     this.listMaxNum = listMaxNum;
   }
   
   public String getSysId() {
     return this.sysId;
   }
   
   public void setSysId(String sysId) {
     this.sysId = sysId;
   }
   
   public String getRuleExpress() {
     return this.ruleExpress;
   }
   
   public void setRuleExpress(String ruleExpress) {
     this.ruleExpress = ruleExpress;
   }
   
   public Integer getCustomGroupBelong() {
     return this.customGroupBelong;
   }
   
   public void setCustomGroupBelong(Integer customGroupBelong) {
     this.customGroupBelong = customGroupBelong;
   }
   
   public Integer getCustomGroupDataType() {
     return this.customGroupDataType;
   }
   
   public void setCustomGroupDataType(Integer customGroupDataType) {
     this.customGroupDataType = customGroupDataType;
   }
   
   public String getSceneId() {
     return this.sceneId;
   }
   
   public void setSceneId(String sceneId) {
     this.sceneId = sceneId;
   }
   
   public String getCustomSceneNames() {
     return this.customSceneNames;
   }
   
   public void setCustomSceneNames(String customSceneNames) {
     this.customSceneNames = customSceneNames;
   }
   
   public String getCreateTimeView() {
     return this.createTimeView;
   }
   
   public void setCreateTimeView(String createTimeView) {
     this.createTimeView = createTimeView;
   }
   
   public String getLabelName() {
     return this.labelName;
   }
   
   public void setLabelName(String labelName) {
     this.labelName = labelName;
   }
   
   public String getTemplateName() {
     return this.templateName;
   }
   
   public void setTemplateName(String templateName) {
     this.templateName = templateName;
   }
   
   public String getDateType() {
     return this.dateType;
   }
   
   public void setDateType(String dateType) {
     this.dateType = dateType;
   }
   
   public String getCreateType() {
     return this.createType;
   }
   
   public void setCreateType(String createType) {
     this.createType = createType;
   }
   
   public String getCreateUserName() {
     return this.createUserName;
   }
   
   public void setCreateUserName(String createUserName) {
     this.createUserName = createUserName;
   }
   
   public String getUpdateCycleStr() {
     return this.updateCycleStr;
   }
   
   public void setUpdateCycleStr(String updateCycleStr) {
     this.updateCycleStr = updateCycleStr;
   }
   
   public String getDataStatusStr() {
     return this.dataStatusStr;
   }
   
   public void setDataStatusStr(String dataStatusStr) {
     this.dataStatusStr = dataStatusStr;
   }
   
   public String getYear() {
     return this.year;
   }
   
   public void setYear(String year) {
     this.year = year;
   }
   
   public String getMonth() {
     return this.month;
   }
   
   public void setMonth(String month) {
     this.month = month;
   }
   
   public String getDay() {
     return this.day;
   }
   
   public void setDay(String day) {
     this.day = day;
   }
   
   public String getListTableName() {
     return this.listTableName;
   }
   
   public void setListTableName(String listTableName) {
     this.listTableName = listTableName;
   }
   
   public Integer getSysMatchStatus() {
     return this.sysMatchStatus;
   }
   
   public void setSysMatchStatus(Integer sysMatchStatus) {
     this.sysMatchStatus = sysMatchStatus;
   }
   
   public boolean isAlarm() {
     return this.isAlarm;
   }
   
   public void setAlarm(boolean isAlarm) {
     this.isAlarm = isAlarm;
   }
   
   public Date getModifyTime() {
     return this.modifyTime;
   }
   
   public void setModifyTime(Date modifyTime) {
     this.modifyTime = modifyTime;
   }
   
   public String getCustomMatch() {
     return this.customMatch;
   }
   
   public void setCustomMatch(String customMatch) {
     this.customMatch = customMatch;
   }
   
   public String getProductsStr() {
     return this.productsStr;
   }
   
   public void setProductsStr(String productsStr) {
     this.productsStr = productsStr;
   }
   
   public String getProductDate() {
     return this.productDate;
   }
   
   public void setProductDate(String productDate) {
     this.productDate = productDate;
   }
   
   public String getProductsIdsStr() {
     return this.productsIdsStr;
   }
   
   public void setProductsIdsStr(String productsIdsStr) {
     this.productsIdsStr = productsIdsStr;
   }
   
   public boolean isPrecisionMarketing() {
     return this.precisionMarketing;
   }
   
   public void setPrecisionMarketing(boolean precisionMarketing) {
     this.precisionMarketing = precisionMarketing;
   }
   
   public boolean isSaveTemplate() {
     return this.saveTemplate;
   }
   
   public void setSaveTemplate(boolean saveTemplate) {
     this.saveTemplate = saveTemplate;
   }
   
   public String getReturnPage() {
     return this.returnPage;
   }
   
   public void setReturnPage(String returnPage) {
     this.returnPage = returnPage;
   }
   
   public int getFailedListCount() {
     return this.failedListCount;
   }
   
   public void setFailedListCount(int failedListCount) {
     this.failedListCount = failedListCount;
   }
   
   public String getCustomerAnalysisMenu() {
     return this.customerAnalysisMenu;
   }
   
   public void setCustomerAnalysisMenu(String customerAnalysisMenu) {
     this.customerAnalysisMenu = customerAnalysisMenu;
   }
   
   public Integer getOldIsHasList() {
     return this.oldIsHasList;
   }
   
   public void setOldIsHasList(Integer oldIsHasList) {
     this.oldIsHasList = oldIsHasList;
   }
   
   public String getIsSysRecommendTime() {
     return this.isSysRecommendTime;
   }
   
   public void setIsSysRecommendTime(String isSysRecommendTime) {
     this.isSysRecommendTime = isSysRecommendTime;
   }
   
   public String getSimpleRule() {
     return this.simpleRule;
   }
   
   public void setSimpleRule(String simpleRule) {
     this.simpleRule = simpleRule;
   }
   
   public boolean isOverlength() {
     return this.isOverlength;
   }
   
   public void setOverlength(boolean isOverlength) {
     this.isOverlength = isOverlength;
   }
   
   public int getIsPush() {
     return this.isPush;
   }
   
   public void setIsPush(int isPush) {
     this.isPush = isPush;
   }
   
   public String getIsAttention() {
     return this.isAttention;
   }
   
   public void setIsAttention(String isAttention) {
     this.isAttention = isAttention;
   }
   
   public String getStartDateStr() {
     return this.startDateStr;
   }
   
   public void setStartDateStr(String startDateStr) {
     this.startDateStr = startDateStr;
   }
   
   public String getEndDateStr() {
     return this.endDateStr;
   }
   
   public void setEndDateStr(String endDateStr) {
     this.endDateStr = endDateStr;
   }
   
   public Date getModifyStartDate() {
     return this.modifyStartDate;
   }
   
   public void setModifyStartDate(Date modifyStartDate) {
     this.modifyStartDate = modifyStartDate;
   }
   
   public Date getModifyEndDate() {
     return this.modifyEndDate;
   }
   
   public void setModifyEndDate(Date modifyEndDate) {
     this.modifyEndDate = modifyEndDate;
   }
   
   public Long getMinCustomNum() {
     return this.minCustomNum;
   }
   
   public void setMinCustomNum(Long minCustomNum) {
     this.minCustomNum = minCustomNum;
   }
   
   public Long getMaxCustomNum() {
     return this.maxCustomNum;
   }
   
   public void setMaxCustomNum(Long maxCustomNum) {
     this.maxCustomNum = maxCustomNum;
   }
   
   public String getIsHotCustom() {
     return this.isHotCustom;
   }
   
   public void setIsHotCustom(String isHotCustom) {
     this.isHotCustom = isHotCustom;
   }
   
   public String getTabelName() {
     return this.tabelName;
   }
   
   public void setTabelName(String tabelName) {
     this.tabelName = tabelName;
   }
   
   public String getWhereSql() {
     return this.whereSql;
   }
   
   public void setWhereSql(String whereSql) {
     this.whereSql = whereSql;
   }
   
   public int getUseTimes() {
     return this.useTimes;
   }
   
   public void setUseTimes(int useTimes) {
     this.useTimes = useTimes;
   }
   
   public String getCityId() {
     return this.cityId;
   }
   
   public void setCityId(String cityId) {
     this.cityId = cityId;
   }
   
   public String getCreateCityName() {
     return this.createCityName;
   }
   
   public void setCreateCityName(String createCityName) {
     this.createCityName = createCityName;
   }
   
   public String getIsMyCustom() {
     return this.isMyCustom;
   }
   
   public void setIsMyCustom(String isMyCustom) {
     this.isMyCustom = isMyCustom;
   }
   
   public String getUserId() {
     return this.userId;
   }
   
   public void setUserId(String userId) {
     this.userId = userId;
   }
   
   public String getIsMcdNeed() {
     return this.isMcdNeed;
   }
   
   public void setIsMcdNeed(String isMcdNeed) {
     this.isMcdNeed = isMcdNeed;
   }
   
   public Integer getLastDataStatus() {
     return this.lastDataStatus;
   }
   
   public void setLastDataStatus(Integer lastDataStatus) {
     this.lastDataStatus = lastDataStatus;
   }
   
   public String getDayDataDate() {
     return this.dayDataDate;
   }
   
   public void setDayDataDate(String dayDataDate) {
     this.dayDataDate = dayDataDate;
   }
   
   public String getDataDateStr() {
     return this.dataDateStr;
   }
   
   public void setDataDateStr(String dataDateStr) {
     this.dataDateStr = dataDateStr;
   }
   
   public Double getAvgScore() {
     return this.avgScore;
   }
   
   public void setAvgScore(Double avgScore) {
     this.avgScore = avgScore;
   }
   
   public String getCustomGroupBelongName() {
     return this.customGroupBelongName;
   }
   
   public void setCustomGroupBelongName(String customGroupBelongName) {
     this.customGroupBelongName = customGroupBelongName;
   }
   
   public Integer getTaskId() {
     return this.taskId;
   }
   
   public void setTaskId(Integer taskId) {
     this.taskId = taskId;
   }
   
   public static int getRuleMaxLength() {
     return 220;
   }
   
   public Integer getCustomGroupNetType() {
     return this.customGroupNetType;
   }
   
   public void setCustomGroupNetType(Integer customGroupNetType) {
     this.customGroupNetType = customGroupNetType;
   }
   
   public Integer getSeparatorId() {
     return this.separatorId;
   }
   
   public void setSeparatorId(Integer separatorId) {
     this.separatorId = separatorId;
   }
 }

