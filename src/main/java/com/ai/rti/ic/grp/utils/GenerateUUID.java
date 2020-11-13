 package  com.ai.rti.ic.grp.utils;
 
 import java.util.UUID;
 
 public class GenerateUUID
 {
   private static final char[] CHARMAP = new char[64]; static { int i;
     for (i = 0; i < 10; i++) {
       CHARMAP[i] = (char)(48 + i);
     }
     for (i = 10; i < 36; i++) {
       CHARMAP[i] = (char)(97 + i - 10);
     }
     for (i = 36; i < 62; i++) {
       CHARMAP[i] = (char)(65 + i - 36);
     }
     CHARMAP[62] = '_';
     CHARMAP[63] = '-'; }
 
 
 
   
   public static String getUUID() {
     String uuid = UUID.randomUUID().toString();
     uuid = uuid.replaceAll("-", "").toUpperCase();
     uuid = "0" + uuid;
     uuid = hexTo64(uuid);
     
     return uuid;
   }
 
 
 
   
   private static String hexTo64(String hex) {
     StringBuffer r = new StringBuffer();
     int index = 0;
     int[] buff = new int[3];
     int l = hex.length();
     for (int i = 0; i < l; i++) {
       index = i % 3;
       buff[index] = Integer.parseInt("" + hex.charAt(i), 16);
       if (index == 2) {
         r.append(CHARMAP[buff[0] << 2 | buff[1] >>> 2]);
         r.append(CHARMAP[(buff[1] & 0x3) << 4 | buff[2]]);
       } 
     } 
     return r.toString();
   }
 }

