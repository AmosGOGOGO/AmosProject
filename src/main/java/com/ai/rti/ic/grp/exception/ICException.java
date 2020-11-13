 package  com.ai.rti.ic.grp.exception;
 
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 public class ICException
   extends Exception
 {
   private static final long serialVersionUID = -1875900649515401660L;
   private static final Logger logger = LoggerFactory.getLogger(com.ai.rti.ic.grp.exception.ICException.class);
   
   private String code;
   
   public ICException(String code, String msg) {
     super(msg);
     this.code = code;
   }
 
   
   public ICException(String code, Exception ex) {
     super(ex);
     this.code = code;
   }
 
   
   public String getErrorCode() {
     return this.code;
   }
 }
