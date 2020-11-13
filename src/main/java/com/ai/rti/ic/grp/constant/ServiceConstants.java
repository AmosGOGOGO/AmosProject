 package  com.ai.rti.ic.grp.constant;
 
 import java.util.HashMap;
 import java.util.Map;
 
 public class ServiceConstants
 {
   public static final int ELEMENT_TYPE_OPERATOR = 1;
   public static final int ELEMENT_TYPE_LABEL_ID = 2;
   public static final int ELEMENT_TYPE_BRACKET = 3;
   public static final int ELEMENT_TYPE_PRODUCT_ID = 4;
   public static final int ELEMENT_TYPE_LIST_ID = 5;
   public static final int ELEMENT_TYPE_CUSTOM_RULES = 6;
   public static final int LABEL_TYPE_SIGN = 1;
   public static final int LABEL_TYPE_SCORE = 2;
   public static final int LABEL_TYPE_ATTR = 3;
   public static final int LABEL_TYPE_KPI = 4;
   public static final int LABEL_TYPE_ENUM = 5;
   public static final int LABEL_TYPE_DATE = 6;
   public static final int LABEL_TYPE_TEXT = 7;
   public static final int LABEL_TYPE_VERT = 8;
   public static final int LABEL_TYPE_BIT = 9;
   public static final String LABEL_TYPE_SIGN_STR = "标识型";
   public static final String LABEL_TYPE_SCORE_STR = "得分型";
   public static final String LABEL_TYPE_ATTR_STR = "属性型";
   public static final String LABEL_TYPE_KPI_STR = "指标型";
   public static final String LABEL_TYPE_ENUM_STR = "枚举型";
   public static final String LABEL_TYPE_DATE_STR = "日期型";
   public static final String LABEL_TYPE_TEXT_STR = "文本型";
   public static final String LABEL_TYPE_VERT_STR = "组合型";
   public static final String LABEL_TYPE_BIT_STR = "按位与标签";
   public static final int LABEL_CYCLE_TYPE_D = 1;
   public static final int LABEL_CYCLE_TYPE_M = 2;
   public static final String DAY_CYCLE_LABEL = "日周期";
   public static final String MONTH_CYCLE_LABEL = "月周期";
   public static final int LABEL_BELONG_U = 0;
   public static final int LABEL_BELONG_C = 1;
   public static final int COLUMN_DATA_TYPE_NUM = 1;
   public static final int COLUMN_DATA_TYPE_VARCHAR = 2;
   public static final String COLUMN_TYPE_NUMBER = "number";
   public static final String COLUMN_TYPE_CHAR = "char";
   public static final int CUSTOMER_CREATE_TYPE_BY_LABEL = 1;
   public static final int CUSTOMER_CREATE_TYPE_BY_LABEL_DIFFERENTIAL = 2;
   public static final int CUSTOMER_CREATE_TYPE_BY_LABEL_ANALYSIS = 3;
   public static final int CUSTOMER_CREATE_TYPE_BY_CUSTOMER_CALCULATE = 4;
   public static final int CUSTOMER_CREATE_TYPE_BY_CUSTOMER_ANALYSIS = 5;
   public static final int CUSTOMER_CREATE_TYPE_BY_CUSTOMER_DIFFERENTIAL = 6;
   public static final int CUSTOMER_CREATE_TYPE_BY_FILE_IMPORT = 7;
   public static final int CUSTOMER_CREATE_TYPE_BY_PRODUCT = 8;
   public static final int CUSTOMER_CREATE_TYPE_BY_PRODUCT_TACTIC = 9;
   public static final int CUSTOMER_CREATE_TYPE_BY_SA = 10;
   public static final int CUSTOMER_CREATE_TYPE_BY_OTHER_SYS = 11;
   public static final int CUSTOMER_CREATE_TYPE_BY_TABLE_IMPORT = 12;
   public static final String COC_CUSTOMER_MANAGE_ADD = "COC_CUSTOMER_MANAGE_ADD";
   public static final String COC_CUSTOMER_MANAGE_ADD_LABEL = "COC_CUSTOMER_MANAGE_ADD_LABEL";
   public static final String COC_CUSTOMER_MANAGE_ADD_IMPORT = "COC_CUSTOMER_MANAGE_ADD_IMPORT";
   public static final String COC_CUSTOMER_MANAGE_ADD_PRODUCT = "COC_CUSTOMER_MANAGE_ADD_PRODUCT";
   public static final String COC_LABEL_ANALYSIS = "COC_LABEL_ANALYSIS";
   public static final String COC_LABEL_ANALYSIS_LINK = "COC_LABEL_ANALYSIS_LINK";
   public static final String COC_LABEL_CONTRAST_ANALYSIS = "COC_LABEL_CONTRAST_ANALYSIS";
   public static final String COC_LABEL_REL_ANALYSIS = "COC_LABEL_REL_ANALYSIS";
   public static final String COC_LABEL_DIFFERENTIAL = "COC_LABEL_DIFFERENTIAL";
   public static final String COC_CUSTOMER_ANALYSIS = "COC_CUSTOMER_ANALYSIS";
   public static final String COC_CUSTOMER_CONTRAST_ANALYSIS = "COC_CUSTOMER_CONTRAST_ANALYSIS";
   public static final String COC_CUSTOMER_REL_ANALYSIS = "COC_CUSTOMER_REL_ANALYSIS";
   public static final String COC_INDEX_DIFFERENTIAL = "COC_INDEX_DIFFERENTIAL";
   public static final String COC_CUSTOMER_LABEL_DIFFERENTIAL = "COC_CUSTOMER_LABEL_DIFFERENTIAL";
   public static final String COC_DATA_EXPLORE = "COC_DATA_EXPLORE";
   public static final String COC_INDEX_LABEL_DATA_EXPLORE = "COC_INDEX_LABEL_DATA_EXPLORE";
   public static final String COC_INDEX_PRODUCT_DATA_EXPLORE = "COC_INDEX_PRODUCT_DATA_EXPLORE";
   public static final String COC_LABEL_ANALYSIS_DATA_EXPLORE = "COC_LABEL_ANALYSIS_DATA_EXPLORE";
   public static final String COC_CUSTOMER_MATCH_ADD = "COC_CUSTOMER_MATCH_ADD";
   public static final String COC_CUSTOMER_MARKETING_ADD = "COC_CUSTOMER_MARKETING_ADD";
   public static final String COC_MARKETING_CAMPAIGN_LINK = "COC_MARKETING_CAMPAIGN_LINK";
   public static final String COC_LOGIN = "COC_LOGIN";
   public static final int LABEL_USE_TYPE_QUERY_LABELINFO = 1;
   public static final int LABEL_USE_TYPE_CREATE_CUSTOMER = 2;
   public static final int LABEL_USE_TYPE_MAIN_ANALYSIS = 3;
   public static final int LABEL_USE_TYPE_MAIN_DIFFERENTIAL = 4;
   public static final int LABEL_USE_TYPE_REL_ANALYSIS = 5;
   public static final int LABEL_USE_TYPE_CONTRAST_ANALYSIS = 6;
   public static final int LABEL_USE_TYPE_LABEL_DIFFERENTIAL = 7;
   public static final int CUSTOM_USE_TYPE_QUERY_LABELINFO = 1;
   public static final int CUSTOM_USE_TYPE_CREATE_CUSTOMER = 2;
   public static final int CUSTOM_USE_TYPE_MAIN_ANALYSIS = 3;
   public static final int CUSTOM_USE_TYPE_MAIN_DIFFERENTIAL = 4;
   public static final int LABEL_RULE_FROM_COSTOMER = 1;
   public static final int LABEL_RULE_FROM_TEMPLATE = 2;
   public static final int USE_TEMPLATE = 1;
   public static final int USE_COSTOMER = 0;
   public static final String COLUMN_POSTFIX = "_SCORE";
   public static final int LABEL_RULE_FLAG_NO = 0;
   public static final int LABEL_RULE_FLAG_YES = 1;
   public static final int LABEL_RULE_FLAG_NO_FOR_SCORE = 2;
   public static final int LABEL_RULE_FLAG_YES_FOR_SCORE = 3;
   public static final int CICUSTOMGROUPINFO_STATUS_DELETE = 0;
   public static final int CICUSTOMGROUPINFO_STATUS_VALIDATE = 1;
   public static final int CICUSTOMGROUPINFO_IS_PRIVATE = 1;
   public static final int CICUSTOMGROUPINFO_IS_PUBLIC = 0;
   public static final int CICUSTOMGROUPINFO_IS_ONLINE = 0;
   public static final int CICUSTOMGROUPINFO_IS_OVERLINE = 1;
   public static final int TEMPLATEINFO_STATUS_DELETE = 0;
   public static final int TEMPLATEINFO_STATUS_VALIDATE = 1;
   public static final int TEMPLATEINFO_STATUS_INVALIDATE = 2;
   public static final int TEMPLATEINFO_IS_PRIVATE = 1;
   public static final int TEMPLATEINFO_IS_PUBLIC = 0;
   public static final int CUSTOM_LIST_STATUS_FAILED = 0;
   public static final int CUSTOM_LIST_STATUS_WAIT = 1;
   public static final int CUSTOM_LIST_STATUS_CREATING = 2;
   public static final int CUSTOM_LIST_STATUS_SUCCESS = 3;
   public static final int CUSTOM_LIST_FILE_STATUS_WAIT = 1;
   public static final int CUSTOM_LIST_FILE_STATUS_CREATING = 2;
   public static final int CUSTOM_LIST_FILE_STATUS_SUCCESS = 3;
   public static final int CUSTOM_LIST_FILE_STATUS_FAILED = 4;
   public static final int CUSTOM_DATA_STATUS_FAILED = 0;
   public static final int CUSTOM_DATA_STATUS_WAIT = 1;
   public static final int CUSTOM_DATA_STATUS_CREATING = 2;
   public static final int CUSTOM_DATA_STATUS_SUCCESS = 3;
   public static final int CUSTOM_DATA_STATUS_ORDER = 4;
   public static final int CUSTOM_LABEL_JJ_ANALYSIS_RESULT = 1;
   public static final int CUSTOM_LABEL_CJ_ANALYSIS_RESULT = 2;
   public static final int LABEL_CUSTOM_CJ_ANALYSIS_RESULT = 3;
   public static final int CUSTOM_CALC_ELEMENT_TYPE_OPT = 0;
   public static final int CUSTOM_CALC_ELEMENT_TYPE_CUTOMERID = 1;
   public static final String CUSTOM_CALC_ELEMENT_TYPE_OPT_UNION = "OR";
   public static final String CUSTOM_CALC_ELEMENT_TYPE_OPT_INTERSECT = "AND";
   public static final String CUSTOM_CALC_ELEMENT_TYPE_OPT_EXCEPT = "NOT";
   public static final String CALCULATE_ELEMENT_TYPE_OPT_AND = "and";
   public static final String CALCULATE_ELEMENT_TYPE_OPT_OR = "or";
   public static final String CALCULATE_ELEMENT_TYPE_OPT_EXCEPT = "-";
   public static final String CUSTOM_CALC_ELEMENT_TYPE_OPT_REMOVE = "REMOVE";
   public static final int CUSTOM_CYCLE_TYPE_ONE = 1;
   public static final int CUSTOM_CYCLE_TYPE_M = 2;
   public static final int CUSTOM_CYCLE_TYPE_D = 3;
   public static final int CUSTOM_CYCLE_TYPE_N = 4;
   public static final String CUSTOM_CYCLE_ONE = "一次性";
   public static final String CUSTOM_CYCLE_M = "月周期";
   public static final String CUSTOM_CYCLE_D = "日周期";
   public static final int CUSTOM_DATA_TYPE_FULL = 0;
   public static final int CUSTOM_DATA_TYPE_INCREMENT = 1;
   public static final int LABEL_DATA_STATUS_ID_NOT_EFFECT = 1;
   public static final int LABEL_DATA_STATUS_ID_EFFECT = 2;
   public static final int LABEL_DATA_STATUS_ID_FAILURE = 3;
   public static final int LABEL_DATA_STATUS_ID_FREEZED = 4;
   public static final int LABEL_DATA_STATUS_ID_UNDER = 5;
   public static final int LABEL_DATA_STATUS_ID_DELETED = 6;
   public static final String CUSTOM_ASSOCIATION_ANALYSIS = "";
   public static final String COC_PRODUCT_ID = "COC";
   public static final String ALARM_THRESHOLD_TYPE_LABEL = "LabelAlarm";
   public static final String ALARM_THRESHOLD_TYPE_CUSTOMERS = "CustomersAlarm";
   public static final int ALARM_THRESHOLD_FLAG_ACTIVE = 0;
   public static final int ALARM_THRESHOLD_FLAG_INVALID = 1;
   public static final String ALARM_COLUMN_ID_CUSTOM_NUM = "0";
   public static final String ALARM_COLUMN_ID_RING_NUM = "1";
   public static final String ALARM_COLUMN_ID_PROPORTION = "2";
   public static final Map<String, String> alarmColumnIdToNameMap = new HashMap<>();
   static {
     alarmColumnIdToNameMap.put("0", "基础");
     alarmColumnIdToNameMap.put("1", "环比");
     alarmColumnIdToNameMap.put("2", "占比");
   }
 
 
 
   
   public static final int ALARM_RECORD_UNCONFIRM_FLAG = 0;
 
 
   
   public static final int ALARM_RECORD_CONFIRM_FLAG = 1;
 
 
   
   public static final int LABEL_PARENT_ID_NULL = -1;
 
 
   
   public static final String CUSTOM_ONE_D = "1";
 
 
   
   public static final String CUSTOM_ONE_M = "2";
 
   
   public static final String CUSTOM_THREE_M = "3";
 
   
   public static final String CUSTOM_ONE_7D = "4";
 
   
   public static final int STAT_USER_NUM_YES = 1;
 
   
   public static final int PRODUCT_CATEGORY_PARENT_ID_NULL = -1;
 
   
   public static final int PRODUCT_PARENT_ID_NULL = -1;
 
   
   public static final int SYS_ANNOUNCEMENT_STATUE_DELETE = 0;
 
   
   public static final int SYS_ANNOUNCEMENT_STATUE_VALID = 1;
 
   
   public static final int SYS_ANNOUNCEMENT_STATUE_INVALID = 2;
 
   
   public static final int USER_ANNOUNCEMENT_STATUE_DELETE = 0;
 
   
   public static final int USER_ANNOUNCEMENT_STATUE_READ = 1;
 
   
   public static final int USER_ANNOUNCEMENT_STATUE_NOT_READ = 2;
 
   
   public static final int PRODUCT_STATUS_NOT_EFFECT = 1;
 
   
   public static final int PRODUCT_STATUS_EFFECT = 2;
 
   
   public static final int PRODUCT_STATUS_FAILURE = 3;
 
   
   public static final int PRODUCT_STATUS_FREEZED = 4;
 
   
   public static final int PRODUCT_STATUS_UNDER = 5;
 
   
   public static final int PRODUCT_STATUS_DELETED = 6;
 
   
   public static String CUSTOM_PRODUCT_TACTICS_REL_DEL = "0";
   
   public static String CUSTOM_PRODUCT_TACTICS_REL_VAL = "1";
 
 
 
 
   
   public static short MARKET_TACTICS_STATUS_DEL = 0;
   
   public static short MARKET_TACTICS_STATUS_VAL = 1;
 
 
 
   
   public static final int PARENT_ID_NULL = -1;
 
 
 
   
   public static final int DB_ORDER_ASC = 1;
 
 
 
   
   public static final int DB_ORDER_DESC = 2;
 
 
   
   public static final int DB_ORDER_NO = 3;
 
 
   
   public static final int PUSH_CUSOMER_STATUS_WAIT = 1;
 
 
   
   public static final int PUSH_CUSOMER_STATUS_PUSHING = 2;
 
 
   
   public static final int PUSH_CUSOMER_STATUS_SUCCESS = 3;
 
 
   
   public static final int PUSH_CUSOMER_STATUS_FAILED = 0;
 
 
   
   public static short PUBLIC_STATUS_DEL = 0;
   
   public static short PUBLIC_STATUS_VAL = 1;
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static int CI_SYS_INTERVAL = 7;
 
 
   
   public static final int PERSON_NOTICE_READ_STATUS_YES = 2;
 
 
   
   public static final int PERSON_NOTICE_READ_STATUS_NO = 1;
 
 
   
   public static int PERSON_NOTICE_TYPE_LABEL_PUBLISH = 1;
   
   public static int PERSON_NOTICE_TYPE_PUBLISH_LABEL_ALARM = 2;
   
   public static int PERSON_NOTICE_TYPE_PUBLISH_CUSTOMERS_GENERATE = 3;
   
   public static int PERSON_NOTICE_TYPE_PUBLISH_CUSTOMERS_ALARM = 4;
   
   public static int PERSON_NOTICE_TYPE_PUBLISH_CUSTOMERS_MATCH_PRODUCT = 5;
   
   public static int PERSON_NOTICE_TYPE_PUBLISH_CUSTOMERS_PUBLISH = 6;
   
   public static int PERSON_NOTICE_TYPE_LABEL_APPROVE = 7;
   
   public static int PERSON_NOTICE_TYPE_CUSTOM_FILE_CREATE = 8;
   
   public static int PERSON_NOTICE_TYPE_OTHERSY_PUBLISH_CUSTOMERS_GENERATE = 9;
   
   public static int PERSON_NOTICE_TYPE_LIST_DOWNLOAD_APPROVE = 10;
   
   public static int PERSON_NOTICE_TYPE_UPLOAD_LABEL_FILE = 11;
   
   public static int PERSON_NOTICE_TYPE_FEEDBACK_ADD = 12;
   
   public static int PERSON_NOTICE_TYPE_FEEDBACK_REPLY = 13;
   
   public static int PERSON_NOTICE_TYPE_FEEDBACK_CANCLE = 14;
 
   
   public static String PERSON_NOTICE_TYPE_STRING_LABEL_PUBLISH = "标签发布通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_PUBLISH_LABEL_ALARM = "标签预警通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_PUBLISH_CUSTOMERS_GENERATE = "客户群生成";
   
   public static String PERSON_NOTICE_TYPE_STRING_PUBLISH_CUSTOMERS_ALARM = "客户群预警通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_PUBLISH_CUSTOMERS_MATCH_PRODUCT = "客户群策略匹配完成通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_CREATE_CUSTOMERS_FILE_GENERATE = "客户群文件生成通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_PUBLISH_CUSTOMERS_PUBLISH = "客户群推送通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_LABEL_APPROVE = "标签审批通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_OTHERSY_PUBLISH_CUSTOMERS_GENERATE = "外部系统推送通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_LIST_DOWNLOAD_APPROVE = "清单下载审批通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_UPLOAD_LABEL_FILE = "标签导入通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_FEEDBACK_ADD = "意见反馈新增通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_FEEDBACK_REPLY = "意见反馈回复通知";
   
   public static String PERSON_NOTICE_TYPE_STRING_FEEDBACK_CANCLE = "意见反馈取消通知";
 
 
   
   public static final int PERSON_NOTICE_SUCCESS = 1;
 
 
   
   public static final int PERSON_NOTICE_FAILURE = 0;
 
 
   
   public static final int NOTICE_SYS = 1;
 
 
   
   public static final int NOTICE_PERSON = 2;
 
 
   
   public static final int PERSON_NOTICE_STATUS_VALID = 1;
 
 
   
   public static final int PERSON_NOTICE_STATUS_INVALID = 2;
 
 
   
   public static final int REL_ANALYSIS_REDIUS_40 = 50;
 
 
   
   public static final int REL_ANALYSIS_REDIUS_70 = 50;
 
 
   
   public static final int REL_ANALYSIS_REDIUS_100 = 50;
 
 
   
   public static final double REL_ANALYSIS_DISTANCE_40 = 0.4D;
 
 
   
   public static final double REL_ANALYSIS_DISTANCE_70 = 0.6D;
 
 
   
   public static final double REL_ANALYSIS_DISTANCE_100 = 0.8D;
 
 
   
   public static final String LABEL_APPROVE_PROCESS = "1";
 
 
   
   public static final String NO_NEED_TO_JUDGE_DEPT = "0";
 
 
   
   public static final String NEED_TO_JUDGE_DEPT = "1";
 
 
   
   public static final int APPROVE_PASS = 1;
 
 
   
   public static final int APPROVE_NOT_PASS = 0;
 
 
   
   public static final int APPROVE_ROLE_TYPE_IN_PROCESS = 1;
 
 
   
   public static final int APPROVE_ROLE_TYPE_OUT_OF_PROCESS = 2;
 
 
   
   public static final int GENERAL_USER_ADD = 1;
 
 
   
   public static final int GENERAL_USER_SUBMINT = 2;
 
   
   public static final int WAIT_FINAL_PROCESSING = 3;
 
   
   public static final int THE_FINAL_APPROVE_STATUS = 4;
 
   
   public static final int WAITTINT_TO_BE_PROCESSED = 1;
 
   
   public static final int APPROVER_HAS_PROCESSED = 2;
 
   
   public static final int LABEL_OPERATE_TYPE_ID_ADD = 1;
 
   
   public static final int LABEL_OPERATE_TYPE_ID_MODIFY = 2;
 
   
   public static final int LABEL_OPERATE_TYPE_ID_DELETE = 3;
 
   
   public static final int LABEL_OPERATE_TYPE_ID_APPROVE = 4;
 
   
   public static final int LABEL_OPERATE_TYPE_ID_ADDAPPROVE = 5;
 
   
   public static final int LABEL_OPERATE_TYPE_ID_STOPUSE = 6;
 
   
   public static final int LABEL_OPERATE_TYPE_ID_OFFLINE = 7;
 
   
   public static final int LABEL_OPERATE_TYPE_ID_ONLINE = 8;
 
   
   public static final int LABEL_OPERATE_TYPE_ID_RELEASE = 9;
 
   
   public static final int VIP_LEVEL_ID_ROOT_VAL = -1;
 
   
   public static final int BRAND_ID_ROOT_VAL = -1;
 
   
   public static final int CITY_ID_ROOT_VAL = 801;
 
   
   public static final String RETURN_PAGE_MARKETINGSTRATEGY = "marketingStrategy";
 
   
   public static final String RETURN_PAGE_PRODUCTVIEW = "customProductView";
 
   
   public static final String SYS_MATCH = "sysMatch";
 
   
   public static final String CUSTOM_MATCH = "customMatch";
 
   
   public static final int NO_SYS_MATCH = 1;
 
   
   public static final int SYS_MATCH_ING = 2;
 
   
   public static final int FINISH_SYS_MATCH = 3;
 
   
   public static final String CUSTOM_IMPORT_SUCCESS = "CUSTOM_IMPORT_SUCCESS";
 
   
   public static final String CUSTOM_IMPORT_FAIL = "CUSTOM_IMPORT_FAIL";
 
   
   public static final String CUSTOM_IMPORT_TOTAL = "CUSTOM_IMPORT_TOTAL";
 
   
   public static final int ALARM_CHECKFLAG_NO = 0;
 
   
   public static final int ALARM_CHECKFLAG_YES = 1;
 
   
   public static final String LABEL_STAT_CYCLE_MM = "MM";
 
   
   public static final String LABEL_STAT_CYCLE_DM = "DM";
 
   
   public static final int LABEL_NEWEST_DATE_IS_STAT_YES = 1;
 
   
   public static final int LABEL_NEWEST_DATE_IS_STAT_NO = 0;
 
   
   public static final long LABEL_INIT_CYCLE_M = 1L;
 
   
   public static final long LABEL_INIT_CYCLE_D = 2L;
 
   
   public static final String UNKNOWM_PATH = "-1";
 
   
   public static final int MORE_CITY_TREND_MONTH = 12;
 
   
   public static final int MORE_CITY_TREND_DAY = 30;
 
   
   public static final String GROUP_ATTRIBUTE_COLUMN_NAME = "ATTR_COL_";
 
   
   public static final String CUSTOM_RESOURCE_ID_CI_IMPORT = "CI_IMPORT";
 
   
   public static final int ATTR_SOURCE_ID_BY_IMPORT = 1;
 
   
   public static final int ATTR_SOURCE_ID_BY_LABEL = 2;
 
   
   public static final int ATTR_SOURCE_ID_BY_CUSTOM = 3;
 
   
   public static final int TEMPLATE_LATEST_DATE = 15;
 
   
   public static final int CUSTOMERS_LATEST_DATE = 15;
 
   
   public static final int LABEL_LATEST_DATE = 15;
 
   
   public static String LABEL_INFO_CALCULATIONS_TYPEID = "1";
   
   public static String CUSTOM_GROUP_INFO_CALCULATIONS_TYPEID = "2";
   
   public static String TEMPLATE_INFO_CALCULATIONS_TYPEID = "3";
   
   public static String EDIT_CUSTOM_FLAG = "1";
   
   public static String SAVE_CUSTOM_FLAG = "0";
 
 
 
 
   
   public static int SHOW_HOT_TEMPLATE_NUM = 5;
   
   public static int SHOW_RECOMMEND_LABEL_NUM = 99;
   
   public static int SHOW_RECOMMEND_CUSTOMS_NUM = 99;
   
   public static int SHOW_RECOMMEND_TEMPLATE_NUM = 99;
   
   public static int SHOW_SYS_RECOMMEND_LABEL = 20;
   
   public static int SHOW_SYS_RECOMMEND_CUSTOM = 20;
 
 
 
   
   public static int IS_HOT_LIST = 1;
   public static int NO_HOT_LIST = 0;
 
 
 
 
   
   public static int QUERY_WAY_FOR_KPI_LABEL_RANGE = 1;
   
   public static int QUERY_WAY_FOR_KPI_LABEL_EXACT = 2;
   
   public static int QUERY_WAY_FOR_KPI_LABEL_NULL = 3;
 
   
   public static int QUERY_WAY_FOR_DATE_LABEL_RANGE = 1;
   
   public static int QUERY_WAY_FOR_DATE_LABEL_EXACT = 2;
   
   public static int QUERY_WAY_FOR_DATE_LABEL_RELATE = 3;
 
 
 
   
   public static String COOKIE_NAME_FOR_INDEX_PAGE = "INDEX_NUMS_COOKIE";
 
 
   
   public static String COOKIE_NAME_FOR_INDEX_LABEL_TIP = "INDEX_LABEL_TIP_NUMS_COOKIE";
 
 
   
   public static String COOKIE_NAME_FOR_CALCULATE_PAGE = "CALCULATE_NUMS_COOKIE";
 
 
   
   public static String COOKIE_NAME_FOR_IMPORT = "IMPORT_NUMS_COOKIE";
 
 
   
   public static String COOKIE_NAME_FOR_VERSION = "VERSION_FLAG_COOKIE";
 
 
   
   public static String COOKIE_NAME_FOR_SHOW_VERSION = "VERSION_SHOW_FLAG_COOKIE";
 
 
 
   
   public static int IS_SYS_RECOM = 1;
 
 
   
   public static int IS_NOT_SYS_RECOM = 0;
 
 
   
   public static String ALL_SCENE_ID = "0";
 
 
   
   public static final int SCENE_STATUS_VALIDATE = 1;
 
 
   
   public static final int SCENE_STATUS_DELETE = 0;
 
 
   
   public static final String CUSTOM_GROUP_QUERY_TYPE_USER_ATTENTION = "userAttention";
 
 
   
   public static final String CUSTOM_GROUP_QUERY_TYPE_CALL_BY_BACK = "callByBackStage";
 
 
   
   public static final int IS_CONTAIN_LOCAL_LIST = 1;
 
 
   
   public static final int IS_NOT_CONTAIN_LOCAL_LIST = 0;
 
 
   
   public static final int ATTENTION_LABEL_ID = 1;
 
 
   
   public static final int ATTENTION_CUSTOM_ID = 2;
 
 
   
   public static final String NOTICE_PERSON_SEND_SYS = "1";
 
 
   
   public static final String NOTICE_PERSON_SEND_SMS = "2";
 
 
   
   public static final String NOTICE_PERSON_SEND_MAIL = "3";
 
 
   
   public static final int FIRST_LABEL_LEVEL = 1;
 
 
   
   public static final int SECOND_LABEL_LEVEL = 2;
 
 
   
   public static final int THIRD_LABEL_LEVEL = 3;
 
 
   
   public static final int CUSTOM_IS_PUSHED = 1;
 
 
   
   public static final int CUSTOM_NOT_PUSHED = 0;
 
 
   
   public static final int SYS_NOTICE_READ_STATUS_YES = 1;
 
 
   
   public static final int SYS_NOTICE_READ_STATUS_NO = 0;
 
 
   
   public static final int COC_LABEL_FAIL_STATUS = 0;
 
 
   
   public static final int COC_LABEL_SUCCESS_STATUS = 1;
 
 
   
   public static final String LIST_TABLE_TACTICS_ID_ONE = "1";
 
 
   
   public static final String LIST_TABLE_TACTICS_ID_THREE = "2";
 
 
   
   public static final String VALIDATE_RESULT_WAIT = "1";
 
 
   
   public static final String VALIDATE_RESULT_GO = "2";
 
 
   
   public static final String VALIDATE_RESULT_NEW = "3";
 
 
   
   public static final String BACK_FOR_DAY = "1";
 
 
   
   public static final String BACK_FOR_MONTH = "2";
 
 
   
   public static final String BACK_FOR_ALL = "3";
 
 
   
   public static final String NO_BACK_FOR_ALL = "4";
 
 
   
   public static final int PERSON_NOTICE_FALSE = 0;
 
 
   
   public static final int PERSON_NOTICE_TRUE = 1;
 
 
   
   public static final int CUSTOM_GROUP_IS_FIRST_FAILED = 1;
 
   
   public static final int CUSTOM_GROUP_IS_FIRST_SUCCESS = 0;
 
   
   public static final int CUSTOM_GROUP_CUSTOM_LIST_CREATE_FAILED = 1;
 
   
   public static final int CUSTOM_GROUP_CUSTOM_LIST_CREATE_SUCCESS = 0;
 
   
   public static final int NOTICE_IS_SHOW_TIP = 1;
 
   
   public static final int NOTICE_NOT_SHOW_TIP = 0;
 
   
   public static final int LABEL_CUSTOM_ATTENTION_LIST_NUM = 5;
 
   
   public static final int MDA_SYS_TABLE_TYPE_USER = 1;
 
   
   public static final int MDA_SYS_TABLE_TYPE_PRODUCT = 2;
 
   
   public static final int MDA_SYS_TABLE_TYPE_VERTICAL = 3;
 
   
   public static final int LABEL_HAS_SEND_NOTICE_YES = 1;
 
   
   public static final int LABEL_HAS_SEND_NOTICE_NOT = 0;
 
   
   public static final int SYSANNOUNCEMENT_TYPE_LABEL_PUBLISH = 1;
 
   
   public static final int SYSANNOUNCEMENT_TYPE_NEW_FUNCTION = 2;
 
   
   public static final int SYSANNOUNCEMENT_TYPE_LABEL_OFFLINE = 3;
 
   
   public static final int SYSANNOUNCEMENT_TYPE_OTHER = 4;
 
   
   public static final int SYSANNOUNCEMENT_PRIORITY_HIGH = 0;
 
   
   public static final int SYSANNOUNCEMENT_PRIORITY_COMMON = 1;
 
   
   public static final int CUSTOM_LABEL_IS_VERTICAL_ATTR_YES = 1;
 
   
   public static final int CUSTOM_LABEL_IS_VERTICAL_ATTR_NO = 0;
 
   
   public static final int CUSTOM_IS_HAS_LIST = 1;
 
   
   public static final int CUSTOM_NO_HAS_LIST = 0;
 
   
   public static final int IS_STAT_USER_NUM = 1;
 
   
   public static final int IS_NOT_STAT_USER_NUM = 0;
 
   
   public static final int SERVER_IS_EXE_TASK_YES = 1;
 
   
   public static final int SERVER_IS_EXE_TASK_NO = 0;
 
   
   public static final int LABEL_EXACT_OR_ATTR_VALUE_LENGTH = 20;
 
   
   public static final int CI_GROUP_ATTR_REL_STATUS_VALID = 0;
 
   
   public static final int CI_GROUP_ATTR_REL_STATUS_INVALID = 1;
 
   
   public static final int CI_GROUP_ATTR_REL_STATUS_NO_ATTR = 2;
 
   
   public static int STATUS_DEL = 0;
   
   public static int STATUS_VAL = 1;
 
 
 
   
   public static int IS_NEED_OFFSET_YES = 1;
   
   public static int IS_NEED_OFFSET_NO = 0;
 
   
   public static final int CONFIG_LABEL = 1;
 
   
   public static final int IMPORT_LABEL = 2;
 
   
   public static final String IMP_STATUS_WAIT = "0";
 
   
   public static final String IMP_STATUS_CREATING = "1";
 
   
   public static final String IMP_STATUS_SUCCESS = "2";
 
   
   public static final String IMP_STATUS_FAILED = "-1";
 
   
   public static final int CUST_GROUP_NAME = 0;
 
   
   public static final int CUST_GROUP_TYPE = 1;
 
   
   public static final int CUST_GROUP_UPDATE_CYCLE = 2;
 
   
   public static final int CUST_GROUP_IS_PUBLIC = 3;
 
   
   public static final int CUST_GROUP_TABLE_ATTR_NAME = 4;
 
   
   public static final int CUST_GROUP_FILTER_CONDITION = 5;
 
   
   public static final int CUST_GROUP_CITY = 6;
 
   
   public static final int CUST_GROUP_FILE_PATH = 7;
 
   
   public static final int CUST_GROUP_FILE_SERVICE = 8;
 
   
   public static final int CUST_GROUP_SCENCE = 9;
 
   
   public static final int CUST_GROUP_FILE_CHARSET = 10;
 
   
   public static final int CUST_GROUP_DATA_TYPE = 11;
 
   
   public static final int CUST_GROUP = 1;
 
   
   public static final int USER_GROUP = 0;
 
   
   public static final int CUST_GROUP_PUBLIC = 0;
 
   
   public static final int CUST_GROUP_PRIVATE = 1;
   
   public static final String CUST_IMP_TASK_WAIT = "0";
   
   public static final String CUST_IMP_TASK_SUCCESS = "2";
   
   public static final String CUST_GROUP_STR = "客户群";
   
   public static final String USER_GROUP_STR = "用户群";
   
   public static final String CUST_GROUP_CYCLE_DAY_STR = "日";
   
   public static final String CUST_GROUP_CYCLE_MONTH_STR = "月";
   
   public static final String CUST_GROUP_CYCLE_ONECE_STR = "日";
   
   public static final String CUST_GROUP_PUBLIC_STR = "是";
   
   public static final String CUST_GROUP_PRIVATE_STR = "否";
   
   public static final String CUST_GROUP_FILE_FTP_STR = "FTP";
   
   public static final String CUST_GROUP_FILE_LOC_STR = "本地";
   
   public static final String CUST_GROUP_FILE_HDFS_STR = "HDFS";
   
   public static final int LABEL_NAME = 0;
   
   public static final int LABEL_TYPE = 1;
   
   public static final int UPDATE_CYCLE = 2;
   
   public static final int BUSINESS_CALIBER = 3;
   
   public static final int DEPEND_INDEX = 4;
   
   public static final int COUNT_RULES = 5;
   
   public static final int COUNT_RULES_DESC = 6;
   
   public static final int PARENT_NAME = 7;
   
   public static final int DIM_TRANS_ID = 8;
   
   public static final int TABLE_NAME = 9;
   
   public static final int DATA_TYPE = 10;
   
   public static final int NEED_AUTHORITY = 11;
   
   public static final int SCENE_IDS = 12;
   
   public static final int BELONG_ID = 13;
   
   public static final int SOURCE = 14;
   
   public static final String[] EXCEPTTABLE = new String[] { "TACTIC_USER_LABEL_TABLE_DAY", "TACTIC_USER_LABEL_TABLE_MONTH", "TACTIC_CUST_LABEL_TABLE_DAY", "TACTIC_CUST_LABEL_TABLE_MONTH" };
 
 
   
   public static final int IS_STAT_USER_NUM_YES = 1;
 
 
   
   public static final int IS_STAT_USER_NUM_NO = 0;
 
 
   
   public static final int IS_LEAF_YES = 1;
 
 
   
   public static final int IS_LEAF_NO = 0;
 
 
   
   public static final String LABEL_CODE_NEW = "1";
 
 
   
   public static final String LABEL_CODE_OLD = "2";
 
 
   
   public static final String IS_STR = "是";
 
 
   
   public static final String NOT_STR = "否";
 
 
   
   public static final int IS_NUM = 1;
 
 
   
   public static final int NOT_NUM = 0;
 
 
   
   public static final int IS_NEED_AUTHORITY = 1;
 
 
   
   public static final int NOT_NEED_AUTHORITY = 0;
 
 
   
   public static final String COUNT_RULES_CODE = "R_00001";
 
   
   public static final int CUSTOMER_GROUP_DATA_CYCLE_MONTH = 1;
 
   
   public static final int CUSTOMER_GROUP_DATA_CYCLE_DAY = 2;
 
   
   public static final int VERT_PARENT_NAME = 4;
 
   
   public static final int VERT_DIM_TRANS_ID = 5;
 
   
   public static final int VERT_TABLE_NAME = 6;
 
   
   public static final int VERT_DATA_TYPE = 7;
 
   
   public static final int VERT_NEED_AUTHORITY = 8;
 
   
   public static final int VERT_CLOUMN = 9;
 
   
   public static final int VERT_CLOUMN_NAME = 10;
 
   
   public static final int VERT_SCENE_IDS = 11;
 
   
   public static final int VERT_BELONG = 12;
 
   
   public static final int VERT_SOURCE = 13;
 
   
   public static final int LABEL_CLASS_NAME = 0;
 
   
   public static final int PARENT_CLASS_NAME = 1;
 
   
   public static final int LABEL_CLASS_FILE_COLUMN_LENGTH = 2;
 
   
   public static final int COOKIE_EFFECTIVE_ONE_YEAR = 31536000;
 
   
   public static final String SHOP_CART_RULE = "sessionModelList";
 
   
   public static final String SHOP_CART_RULE_NUM = "calcElementNum";
 
   
   public static final String LABEL_CREATE_TABLE_TYPE_NEW = "2";
 
   
   public static int FEEDBACK_DEAL_STATUS_ADD = 1;
   
   public static int FEEDBACK_DEAL_STATUS_REPLY = 2;
   
   public static int FEEDBACK_DEAL_STATUS_FINISH = 3;
   
   public static int FEEDBACK_DEAL_STATUS_CANCEL = 4;
   
   public static int FEEDBACK_DEAL_STATUS_CLOSE = 5;
 
 
   
   public static int FEEDBACK_SIGN_CREDATE = 1;
   
   public static int FEEDBACK_SIGN_DEAL = 2;
 
 
   
   public static int FEEDBACK_TYPE_LABEL = 1;
   
   public static int FEEDBACK_TYPE_CUSTOM = 2;
   
   public static int FEEDBACK_TYPE_SYSTEM = 3;
 
 
   
   public static int FEEDBACK_STATUS_DEL = 0;
   
   public static int FEEDBACK_STATUS_VAL = 1;
 
   
   public static int FEEDBACK_REPLY_TYPE_RAISE = 1;
   
   public static int FEEDBACK_REPLY_TYPE_REPLY = 2;
 
 
   
   public static int RECOM_SHOW_TYPE_LABEL = 1;
   
   public static int RECOM_SHOW_TYPE_CUSTOM = 2;
 
   
   public static int RECOM_STATUS_VALIDATE = 1;
   
   public static int RECOM_STATUS_INVALIDATE = 0;
 
 
   
   public static String FIXED_SEARCH_YES = "true";
   
   public static String FIXED_SEARCH_NO = "false";
 
   
   public static int SORT_NUM = 99;
   
   public static double AVG_SCORE = 0.0D;
 
 
   
   public static int SCENE_REL_STATUS_VALIDATE = 1;
   
   public static int SCENE_REL_STATUS_INVALIDATE = 0;
 
   
   public static String CHANGE_VERSION_SHOW_YES = "true";
   
   public static String CHANGE_VERSION_SHOW_NO = "false";
 
   
   public static String CHANGE_VERSION_YES = "true";
   
   public static String CHANGE_VERSION_NO = "false";
   public static final int OPERA_CUSTOM_GROUP_SUSSESS = 1;
   public static final int OPERA_CUSTOM_GROUP_FAILURE = 0;
   public static final String EXTERNAL_LABEL_SYMBOLS = "#";
   public static final String EXTERNAL_CUSTOMLIST_SYMBOLS = "$";
   public static final String EXTERNAL_LIKE_SYMBOLS = "like";
   public static final int CUSTOMER_DATADATE_LENGTH_MONTH = 7;
   public static final int CUSTOMER_DATADATE_LENGTH_DAY = 10;
   public static final String SYS_VGOP = "vgop";
   public static final String EXTERNAL_LABEL_CATEGORY_LIST = "_LABEL_CATEGORY_LIST";
   public static final String EXTERNAL_SYS_LABEL_REL_LIST = "_SYS_LABEL_REL_LIST";
   public static final String EXTERNAL_ALL_LABEL_LIST = "_ALL_LABEL_LIST";
   public static final String CASE_ONE_D = "1";
   public static final String CASE_ONE_M = "2";
   public static final String CASE_THREE_M = "3";
   public static final String CASE_ONE_7D = "4";
   public static final int CASE_LABEL_TYPE = 1;
   public static final int CASE_CUS_TYPE = 2;
   public static final String CRON_PUSH = "0";
   public static final String POST_PUSH = "1";
   public static final String POST_UPDATE = "2";
   public static final String CI_INPUT_TYPE = "1000";
   public static final String CI_ENUM_TYPE = "2000";
   public static final String CI_IDENTIFY_TYPE = "3000";
   public static final String CI_INDEX_TYPE = "4000";
   public static final String CI_DATE_TYPE = "5000";
   public static final String CI_INPUT_TYPE_STR = "输入型";
   public static final String CI_ENUM_TYPE_STR = "枚举型";
   public static final String CI_IDENTIFY_TYPE_STR = "标识型";
   public static final String CI_INDEX_TYPE_STR = "指标型";
   public static final String CI_DATE_TYPE_STR = "日期型";
   public static final String CI_LABEL_BASE = "1000";
   public static final String CI_LABEL_PORTRAIT = "2000";
   public static final String CI_LABEL_MARKET = "3000";
   public static final String SEPARATOR = "CAM-C-0028";
   public static final String LABEL_CUSTOM_DATA_TYPE = "PUB-C-0007";
   public static final String BATCH_IMPORT_ROLE = "CAM-C-0064";
   public static final String DISPATCH_TYPE_CODE = "CAM-C-0017";
   public static final int CUSTOM_GROUP_INFO_ID_NUM_CODE_LENGTH = 8;
   public static final int TAR_GRP_TASK_FAIL = -1;
   public static final int TAR_GRP_TASK_SUCCESS = 0;
   public static final int TAR_GRP_TASK_WAITING = 1;
   public static final int TAR_GRP_TASK_CREATING = 2;
   public static final String CUSTOM_GROUP_PUST = "CUSTOM_GROUP_PUST";
   public static final int IS_TACTIC_YES = 1;
   public static final int IS_TACTIC_NO = 0;
   public static final String CPT_ATTRS = "CPT_ATTRS";
   public static final String CPT_ATTRS_NAME = "CPT_ATTRS_NAME";
 }
