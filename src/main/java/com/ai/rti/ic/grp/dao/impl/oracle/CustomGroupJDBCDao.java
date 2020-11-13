 package  com.ai.rti.ic.grp.dao.impl.oracle;
 
 import com.ai.rti.ic.grp.dao.ICustomGroupJDBCDao;
 
 
 public class CustomGroupJDBCDao
   implements ICustomGroupJDBCDao
 {
   public String getDateStr(String colName) {
     return "to_char(" + colName + ",'yyyymmddhh24miss')";
   }
 
 
 
   
   public String getDateValStr(String val) {
     val = val.replaceAll("-", "");
     val = val.replaceAll(":", "");
     val = val.replaceAll(" ", "");
     return val;
   }
 
   
   public String getEmpty() {
     return "''";
   }
 }

