 package com.ai.rti.ic.grp.utils;

 import java.math.BigDecimal;
 import java.security.MessageDigest;
 import java.text.NumberFormat;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import java.util.Vector;
 
 public class StringUtil {
   private static final String[] DEFAULT_INVALID_STR = new String[] { "script", "and ", "or ", "union ", "between ", "\"", "\\", "\\t", "insert|values", "select|from", "update|set", "delete|from", "drop", "where", "alter" };
   
   private static final String DEFAULT_FILTERED_CHAR = "`~\\:;\"'<,>./";
 
   
   public static boolean isEmpty(Object str) {
     return (str == null || String.valueOf(str).trim().length() < 1);
   }
   
   public static boolean isNotEmpty(Object str) {
     return !isEmpty(str);
   }
   
   public static boolean isEmpty(String str) {
     return (str == null || str.trim().length() < 1);
   }
   
   public static boolean isNotEmpty(String str) {
     return !isEmpty(str);
   }
   
   public static String obj2Str(Object obj) {
     return (obj == null) ? "" : obj.toString().trim();
   }
   
   public static String obj2Str(Object obj, String defaultValue) {
     return (obj == null) ? defaultValue : obj.toString().trim();
   }
   
   public static Integer string2Integer(String str) {
     if (isNotEmpty(str)) {
       try {
         return new Integer(str.trim());
       } catch (NumberFormatException var2) {
         var2.printStackTrace();
       } 
     }
     
     return null;
   }
   
   public static Integer string2Integer(String str, Integer defaultValue) {
     if (isNotEmpty(str)) {
       try {
         return new Integer(str.trim());
       } catch (NumberFormatException var3) {
         var3.printStackTrace();
         return defaultValue;
       } 
     }
     return defaultValue;
   }
 
   
   public static Long string2Long(String str) {
     if (isNotEmpty(str)) {
       try {
         return new Long(str.trim());
       } catch (NumberFormatException var2) {
         var2.printStackTrace();
       } 
     }
     
     return null;
   }
   
   public static Long string2Long(String str, Long defaultValue) {
     if (isNotEmpty(str)) {
       try {
         return new Long(str.trim());
       } catch (NumberFormatException var3) {
         var3.printStackTrace();
         return defaultValue;
       } 
     }
     return defaultValue;
   }
 
   
   public static Double stringToDouble(String str) {
     if (isNotEmpty(str)) {
       try {
         return new Double(str.trim());
       } catch (NumberFormatException var2) {
         var2.printStackTrace();
       } 
     }
     
     return null;
   }
   
   public static Double stringToDouble(String str, Double defaultValue) {
     if (isNotEmpty(str)) {
       try {
         return new Double(str.trim());
       } catch (NumberFormatException var3) {
         var3.printStackTrace();
         return defaultValue;
       } 
     }
     return defaultValue;
   }
 
   
   public static BigDecimal string2BigDecimal(String str) {
     if (isNotEmpty(str)) {
       try {
         return new BigDecimal(str);
       } catch (NumberFormatException var2) {
         var2.printStackTrace();
       } 
     }
     
     return null;
   }
   
   public static BigDecimal string2BigDecimal(String str, BigDecimal defaultValue) {
     if (isNotEmpty(str)) {
       try {
         return new BigDecimal(str);
       } catch (NumberFormatException var3) {
         var3.printStackTrace();
         return defaultValue;
       } 
     }
     return defaultValue;
   }
 
   
   public static boolean isDecimal(String str) {
     boolean res = true;
     if (isEmpty(str)) {
       return false;
     }
     try {
       new BigDecimal(str);
     } catch (NumberFormatException var3) {
       res = false;
     } 
     
     return res;
   }
 
   
   public static String numberFormat(String value) {
     if (!isEmpty(value)) {
       NumberFormat format = NumberFormat.getInstance();
       return format.format(Double.parseDouble(value));
     } 
     return null;
   }
 
   
   public static boolean isChinese(char c) {
     Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
     return (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
   }
   
   public static boolean isChinese(String str) {
     if (isEmpty(str)) {
       return false;
     }
     char[] ch = str.toCharArray();
     
     for (int i = 0; i < ch.length; i++) {
       char c = ch[i];
       if (isChinese(c)) {
         return true;
       }
     } 
     
     return false;
   }
 
   
   public static boolean stringEquals(String str1, String str2) {
     if (isEmpty(str1) && isEmpty(str2)) {
       return true;
     }
     return isNotEmpty(str1) ? str1.equals(str2) : false;
   }
 
   
   public static String array2Str(Object[] strArray) {
     String str = "";
     
     for (int i = 0; i < strArray.length; i++) {
       str = str + strArray[i].toString() + ",";
     }
     
     if (str.length() > 0) {
       str = str.substring(0, str.length() - 1);
     }
     
     return str;
   }
   
   public static String[] str2Array(String str) {
     Vector<String> vect = getVector(str, ",");
     int num = vect.size();
     String[] strArray = new String[num];
     
     for (int i = 0; i < num; i++) {
       strArray[i] = vect.elementAt(i);
     }
     
     return strArray;
   }
   
   public static Set commaString2Set(String commaString, String split) {
     Set<String> s = new HashSet();
     if (isNotEmpty(commaString)) {
       String[] arr = commaString.split(split);
       
       for (int i = 0; i < arr.length; i++) {
         s.add(arr[i].trim());
       }
     } 
     
     return s;
   }
   
   public static Set commaString2Set(String commaString) {
     return commaString2Set(commaString, ",");
   }
   
   public static List<String> string2List(String str, String splitStr, boolean removeComma) {
     List<String> list = new ArrayList<>();
     int pos = str.indexOf(splitStr);
     boolean hasSplit = false;
     if (pos >= 0) {
       hasSplit = true;
     }
     
     while (pos >= 0) {
       String obj = str.substring(0, pos);
       if (removeComma) {
         obj = obj.substring(1, obj.length() - 1);
       }
       
       list.add(obj);
       str = str.substring(pos + 1, str.length());
       pos = str.indexOf(splitStr);
     } 
     
     if (hasSplit) {
       if (removeComma) {
         str = str.substring(1, str.length() - 1);
       }
       
       list.add(str);
     } 
     
     return list;
   }
   
   public static boolean contains(String str, String target, int pos, char begin, char end) throws Exception {
     int b = str.indexOf(begin);
     int e = str.indexOf(end);
     if (b >= 0 && e >= 0) {
       int len = target.length();
       System.out.println(str.length() + ":" + pos + ":" + len);
       String s = str.substring(pos, pos + len);
       if (!s.equalsIgnoreCase(target)) {
         throw new Exception("string['" + target + "]location:[" + pos + "]Unspecified error");
       }
       String frontStr = str.substring(0, pos);
       String backStr = str.substring(pos + len + 1);
       int endCount = 0;
       int beginCount = 0;
       boolean existBegin = false;
       
       for (int i = 0; i < frontStr.length(); i++) {
         char c = frontStr.charAt(i);
         if (c == begin) {
           beginCount++;
         }
         
         if (c == end) {
           endCount++;
         }
       } 
       
       if (beginCount - endCount > 0) {
         existBegin = true;
       }
       
       endCount = 0;
       beginCount = 0;
       boolean existEnd = false;
       
       for (int j = 0; j < backStr.length(); j++) {
         char c = backStr.charAt(j);
         if (c == begin) {
           beginCount++;
         }
         
         if (c == end) {
           endCount++;
         }
       } 
       
       if (endCount - beginCount > 0) {
         existEnd = true;
       }
       
       return (existBegin && existEnd);
     } 
     
     return false;
   }
 
   
   public static int getPosNotIn(String str, String target, char begin, char end) throws Exception {
     String opStr = str;
     int pos = 0;
     int modify = 0;
     boolean inIf = false;
     
     while (opStr.toLowerCase().indexOf(target.toLowerCase()) >= 0) {
       System.out.println("opStr=[" + opStr + "]");
       pos = opStr.toLowerCase().indexOf(target.toLowerCase());
       if (!contains(str, target, pos + modify, begin, end)) {
         inIf = true;
         
         break;
       } 
       opStr = opStr.substring(pos + target.length());
       modify += pos + target.length();
       System.out.println("modify=" + modify);
     } 
     
     if (!inIf) {
       return -1;
     }
     pos += modify;
     System.out.println(str.substring(pos, pos + target.length()) + " found,pos=[" + pos + "],modify=[" + modify + "]");
     return pos;
   }
 
   
   public static String replaceAllBlank(String s) {
     return isEmpty(s) ? "" : s.replaceAll("\\s", "");
   }
   
   public static String replaceAll(String source, String oldString, String newString) {
     if (source != null && !source.equals("")) {
       StringBuffer output = new StringBuffer();
       int lengthOfSource = source.length();
       int lengthOfOld = oldString.length();
       
       int posStart, pos;
       
       for (posStart = 0; (pos = source.indexOf(oldString, posStart)) >= 0; posStart = pos + lengthOfOld) {
         output.append(source.substring(posStart, pos));
         output.append(newString);
       } 
       
       if (posStart < lengthOfSource) {
         output.append(source.substring(posStart));
       }
       
       return output.toString();
     } 
     return "";
   }
 
   
   public static String arr2CommaString(String[] s) {
     if (s != null && s.length >= 1) {
       String result = s[0];
       if (s.length > 1) {
         for (int i = 1; i < s.length; i++) {
           result = result + "," + s[i];
         }
       }
       
       return result;
     } 
     return "";
   }
 
   
   public static String list2StringWithSplit(List list, String splitStr) {
     if (list != null && list.size() >= 1) {
       StringBuffer buf = new StringBuffer();
       Iterator<String> iter = list.iterator();
       
       while (iter.hasNext()) {
         buf.append(splitStr);
         buf.append(iter.next());
       } 
       
       return buf.toString().substring(1);
     } 
     return null;
   }
 
   
   public static String list2StringWithComma(Collection list, String splitStr) {
     if (list != null && list.size() >= 1) {
       StringBuffer buf = new StringBuffer();
       Iterator<String> iter = list.iterator();
       
       while (iter.hasNext()) {
         buf.append(splitStr);
         buf.append("'");
         buf.append(iter.next());
         buf.append("'");
       } 
       
       return buf.toString().substring(1);
     } 
     return null;
   }
 
   
   public static String filterStr(String src, String filterChar) {
     if (isEmpty(src)) {
       return "";
     }
     src = src.trim();
     if (filterChar == null || filterChar.length() < 0) {
       filterChar = "`~\\:;\"'<,>./";
     }
     
     int len = filterChar.length();
     
     for (int i = 0; i < len; i++) {
       src = src.replaceAll("\\" + String.valueOf(filterChar.charAt(i)), "");
     }
     
     return src;
   }
 
   
   public static boolean isContainInvalidStr(String src, String invalidStr) {
     boolean res = false;
     if (isEmpty(src)) {
       return res;
     }
     if (invalidStr != null && invalidStr.length() > 0) {
       res = (src.indexOf(invalidStr) >= 0);
     } else {
       int len = DEFAULT_INVALID_STR.length;
       src = src.toLowerCase();
       
       for (int i = 0; i < len; i++) {
         String tmpInvalidStr = DEFAULT_INVALID_STR[i];
         if (tmpInvalidStr.indexOf("|") < 0) {
           if (src.indexOf(tmpInvalidStr) >= 0) {
             System.out.println("invalid str [" + tmpInvalidStr + "] exist, quit");
             res = true;
             break;
           } 
         } else {
           boolean tmpBol = false;
           String[] tmpArr = tmpInvalidStr.split("[|]");
           
           for (int j = 0; j < tmpArr.length; j++) {
             if (src.indexOf(tmpArr[j]) < 0) {
               tmpBol = false;
               
               break;
             } 
             System.out.println("invalid str [" + tmpArr[j] + "] exist,");
             tmpBol = true;
           } 
           
           if (tmpBol) {
             res = true;
             
             break;
           } 
         } 
       } 
     } 
     return res;
   }
 
   
   public static String convertComma2Db(String str) {
     int pos = str.indexOf("'");
     
     for (int pos1 = 0; pos != -1; pos = str.substring(pos1, str.length()).indexOf("'")) {
       str = str.substring(0, pos1 + pos) + "'" + str.substring(pos + pos1, str.length());
       pos1 = pos1 + pos + 2;
     } 
     
     return str;
   }
   
   public static String addInvertedComma(String source) {
     if (isEmpty(source)) {
       return null;
     }
     source = "'" + source.trim();
     int pos = source.indexOf(",");
     
     for (int len = source.length(); pos != -1; pos += source.substring(pos).indexOf(",")) {
       source = source.substring(0, pos) + "','" + source.substring(pos + 1, len);
       len += 2;
       pos += 2;
       if (source.substring(pos).indexOf(",") == -1) {
         break;
       }
     } 
     
     source = source + "'";
     return source;
   }
 
   
   public static Vector<Vector<String>> getVectorBySegment(String str, String beginStr, String endStr, String splitStr) {
     Vector<Vector<String>> vect = new Vector<>();
     String strTemp = "";
     int bpos = str.indexOf(beginStr);
     int epos;
     for (epos = str.indexOf(endStr); bpos >= 0 && epos > 0; epos = str.indexOf(endStr)) {
       strTemp = str.substring(bpos + 1, epos);
       Vector<String> subvect = getVector(strTemp, splitStr);
       vect.addElement(subvect);
       str = str.substring(epos + 1, str.length());
       bpos = str.indexOf(beginStr);
     } 
     
     return vect;
   }
   
   public static Vector<String> getVector(String str, String splitStr) {
     Vector<String> vect = new Vector<>();
     int pos = str.indexOf(splitStr);
     
     for (int len = splitStr.length(); pos >= 0; pos = str.indexOf(splitStr)) {
       vect.addElement(str.substring(0, pos));
       str = str.substring(pos + len, str.length());
     } 
     
     vect.addElement(str.substring(0, str.length()));
     return vect;
   }
   
   public static String addChar4Len(String str, String strspace, int strlen) {
     if (str != null && str.length() >= 1 && strspace != null && strspace.length() >= 1) {
       while (str.length() < strlen) {
         if (str.length() + strspace.length() > strlen) {
           return str;
         }
         
         str = strspace + str;
       } 
       
       return str;
     } 
     return str;
   }
 
   
   public static String list2String(Collection collection, String separator, boolean addComma) {
     String str = "";
     if (null != collection && collection.size() >= 1) {
       if (null == separator || separator.length() < 1) {
         separator = ",";
       }
       
       for (Iterator it = collection.iterator(); it.hasNext(); str = str + (addComma ? "'" : "") + obj2Str(it.next(), "") + (addComma ? "'" : "") + separator);
 
 
       
       if (str.length() > 0) {
         str = str.substring(0, str.length() - 1);
       }
       
       return str;
     } 
     return str;
   }
 
   
   public static List<Object> getDistinctList(List[] lists) {
     List<Object> retList = new ArrayList();
     Map<Object, Object> map = new HashMap<>();
     
     for (int i = 0; i < lists.length; i++) {
       List<Object> list = lists[i];
       if (list != null) {
         for (int j = 0; j < list.size(); j++) {
           if (list.get(j) != null) {
             map.put(list.get(j), list.get(j));
           }
         } 
       }
     } 
     
     Iterator it = map.keySet().iterator();
     
     while (it.hasNext()) {
       retList.add(it.next());
     }
     
     return retList;
   }
   
   public static String getGender(String iDCard) {
     int gender = 3;
     if (iDCard.length() == 15) {
       gender = (new Integer(iDCard.substring(14, 15))).intValue() % 2;
     } else if (iDCard.length() == 18) {
       int number17 = (new Integer(iDCard.substring(16, 17))).intValue();
       gender = number17 % 2;
     } 
     
     if (gender == 1) {
       return "1";
     }
     return (gender == 0) ? "2" : "";
   }
 
   
   public static boolean checkEmail(String emailStr) {
     return isEmpty(emailStr) ? false : emailStr.matches("[-_.a-zA-Z0-9]+@[-_a-zA-Z0-9]+.[a-zA-Z]+");
   }
   
   public static boolean checkStr(String v) {
     return isEmpty(v) ? false : v.matches("[a-zA-Z0-9_]*");
   }
   
   public static String encryptMD5(String value) throws Exception {
     String result = "";
     if (isEmpty(value)) {
       return "";
     }
     try {
       MessageDigest messageDigest = MessageDigest.getInstance("MD5");
       messageDigest.update(value.getBytes());
       result = byte2hex(messageDigest.digest());
       return result;
     } catch (Exception var3) {
       var3.printStackTrace();
       throw new Exception(var3.getMessage());
     } 
   }
 
   
   private static String byte2hex(byte[] bytes) {
     String result = "";
     String stmp = "";
     
     for (int n = 0; n < bytes.length; n++) {
       stmp = Integer.toHexString(bytes[n] & 0xFF);
       if (stmp.length() == 1) {
         result = result + "0" + stmp;
       } else {
         result = result + stmp;
       } 
     } 
     
     return result.toUpperCase();
   }
 
   
   public static String format4InvertedComma(String sourceString) {
     return sourceString.replaceAll("'", "''");
   }
   
   public static void main(String[] args) {
     String testStr = "aa;bb;cc|dd;ee;ff";
     Vector<String> v = getVector(testStr, "|");
     
     for (int i = 0; i < v.size(); i++) {
       System.out.println(v.get(i));
     }
     
     String strPath = "file:/crmtest/product/suite400/webapp/WEB-INF/lib/aibi-waterMark-1.2.0-SNAPSHOT.jar!/META-INF/MANIFEST.MF";
     strPath = strPath.substring(5, strPath.indexOf("!"));
     System.out.println(strPath);
     strPath = strPath.substring(0, strPath.lastIndexOf("/") + 1);
     System.out.println(strPath);
   }
 }

