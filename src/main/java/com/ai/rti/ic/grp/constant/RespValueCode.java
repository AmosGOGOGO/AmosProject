 package com.ai.rti.ic.grp.constant;
 
 
 
 
 
 public enum RespValueCode
 {
   RESULT_SUCCESS("0", "处理成功"),
   RESULT_FAIL("1", "处理失败"),
   ERROR_PARMS_OBJ("10011", "请求参数格式有误！"),
   ERROR_DATA_SOURCE("10012", "连接数据库操作异常！"),
   ERROR_DATA_SOURCE_OPERATE("10013", "数据库操作异常！"),
    NULL_OBJ("10001", "参数对象为空"),
    NULL_SERVICE_PARMS("10002", "缺少业务参数！"),
    NULL_NECESSARY_PARMS("10003", "缺少必要业务参数！"),
    ERROR_PAGEINFO_PARMS("10004", "分页信息参数出错！"),
    ERROR_OPERATE_PARMS("10006", "操作参数异常!"),
    ERROR_OPERATE_SERVICE("10005", "服务操作异常"),
    UNKNOWN_ERROR("10100", "系统繁忙，请稍后再试....");
   
   private String code;
   private String desc;
   
   RespValueCode(String code, String desc) {

     setCode(code);
      setDesc(desc);
   }
   
   public String getCode() {
      return this.code;
   }
   
   public void setCode(String code) {
      this.code = code;
   }
   
   public String getDesc() {
      return this.desc;
   }
   
   public void setDesc(String desc) {
      this.desc = desc;
   }
 
   
   public String toString() {
      return "[" + this.code + "]" + this.desc;
   }
 }


