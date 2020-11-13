 package  com.ai.rti.ic.grp.utils;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.Serializable;
 import java.util.Properties;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 public final class Config
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private static final Logger LOGGER = LoggerFactory.getLogger(com.ai.rti.ic.grp.utils.Config.class);
   private static Properties conf = new Properties();
   
   static {
     try {
       conf.load(new FileInputStream("ic.properties"));
     } catch (FileNotFoundException e) {
       LOGGER.error("读取配置信息出错！", e);
     } catch (IOException e) {
       LOGGER.error("读取配置信息出错！", e);
     } 
   }
   
   public static String getObject(String prepKey) {
     prepKey = prepKey.trim();
     if (conf.containsKey(prepKey)) {
       return conf.getProperty(prepKey).toString();
     }
     return "";
   }
 
   
   public static Integer getInt(String prepKey) {
     if (conf.containsKey(prepKey)) {
       return Integer.valueOf(conf.getProperty(prepKey).toString());
     }
     return null;
   }
 }

