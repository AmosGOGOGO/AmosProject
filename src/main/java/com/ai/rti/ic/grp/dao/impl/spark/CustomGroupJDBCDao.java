package  com.ai.rti.ic.grp.dao.impl.spark;
 
 import com.ai.rti.ic.grp.dao.ICustomGroupJDBCDao;
 
 
 public class CustomGroupJDBCDao
   implements ICustomGroupJDBCDao
 {
   public String getDateStr(String colName) {
     return colName;
   }
 
   
   public String getDateValStr(String val) {
     return "'" + val + "'";
   }
 
   
   public String getEmpty() {
     return "\"\"";
   }
 }

