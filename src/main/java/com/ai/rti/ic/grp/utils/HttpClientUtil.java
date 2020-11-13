 package  com.ai.rti.ic.grp.utils;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
 
 
 public final class HttpClientUtil
 {
   private static final Logger logger = Logger.getLogger(com.ai.rti.ic.grp.utils.HttpClientUtil.class);
 
   public static String postMethod(String url, Map<String, String> params) {
     String response = "";
     HttpClient client = new HttpClient();
     PostMethod method = new PostMethod(url);
     method.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler(3, false));
     method.getParams().setParameter("http.protocol.content-charset", "utf-8");
     try {
       List<NameValuePair> list = new ArrayList<>();
       Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
       while (it.hasNext()) {
         Map.Entry<String, String> entry = it.next();
         list.add(new NameValuePair(entry.getKey(), entry.getValue()));
       } 
       method.setRequestBody(list.<NameValuePair>toArray(new NameValuePair[list.size()]));
       
       int statusCode = client.executeMethod((HttpMethod)method);
       
       if (statusCode != 200) {
         logger.error("Method failed: " + method.getStatusLine());
       } else {
         response = method.getResponseBodyAsString();
       } 
     } catch (HttpException e) {
       logger.error("Fatal protocol violation: " + e.getMessage(), (Throwable)e);
     } catch (IOException e) {
       logger.error("Fatal transport error:" + e.getMessage(), e);
     } finally {
       method.releaseConnection();
     } 
     return response;
   }
 
 
 
 
 
 
 
   
   public static String getMethod(String url) {
     String responseBody = "";
     HttpClient client = new HttpClient();
     GetMethod method = new GetMethod(url);
     method.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler(3, false));
     method.getParams().setParameter("http.protocol.content-charset", "utf-8");
     try {
       int statusCode = client.executeMethod((HttpMethod)method);
       if (statusCode != 200) {
         logger.error("Method failed: " + method.getStatusLine());
       } else {
         responseBody = method.getResponseBodyAsString();
         logger.debug("getMethod response:" + responseBody);
       } 
     } catch (HttpException e) {
       logger.error("Fatal protocol violation: " + e.getMessage());
     } catch (IOException e) {
       logger.error("Fatal transport error: " + e.getMessage());
     } finally {
       method.releaseConnection();
     } 
     return responseBody;
   }
 

   
   public static String getMethod(String url, HttpServletRequest request) {
     String responseBody = "";
     HttpClient client = new HttpClient();
     GetMethod method = new GetMethod(url);
     javax.servlet.http.Cookie[] cookies = request.getCookies();
     StringBuffer tmpcookies = new StringBuffer();
     if (cookies != null) {
       Cookie[] httpClientcookie = new Cookie[cookies.length];
       int i = 0;
       for (javax.servlet.http.Cookie cookie : cookies) {
         String domain = cookie.getDomain();
         String name = cookie.getName();
         String value = cookie.getValue();
         String path = cookie.getPath();
         boolean secure = cookie.getSecure();
         int maxAge = cookie.getMaxAge();
         org.apache.commons.httpclient.Cookie hCookie = new org.apache.commons.httpclient.Cookie(domain, name, value, path, maxAge, secure);
         
         httpClientcookie[i] = hCookie;
         tmpcookies.append(hCookie.toString() + ";");
         System.out.println(hCookie.getName() + "   -----  " + hCookie.getValue());
         i++;
       } 
       client.getState().addCookies(httpClientcookie);
       method.setRequestHeader("Cookie", tmpcookies.toString());
     } 
     method.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler(3, false));
     method.getParams().setParameter("http.protocol.content-charset", "utf-8");
     method.getParams().setCookiePolicy("compatibility");
     try {
       int statusCode = client.executeMethod((HttpMethod)method);
       if (statusCode != 200) {
         logger.error("Method failed: " + method.getStatusLine());
       } else {
         responseBody = method.getResponseBodyAsString();
         logger.debug("getMethod response:" + responseBody);
       } 
     } catch (HttpException e) {
       logger.error("Fatal protocol violation: " + e.getMessage());
     } catch (IOException e) {
       logger.error("Fatal transport error: " + e.getMessage());
     } finally {
       method.releaseConnection();
     } 
     return responseBody;
   }
 
 
 
 
 
 
   
   public static String doGet(String requestUrl) {
     String resultStr = "";
     if ("".equals(requestUrl))
     {
       throw new RuntimeException("doGet method: an not blank requestUrl is needed!");
     }
     try {
       URL url = new URL(requestUrl);
       HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
       httpConn.setRequestMethod("GET");
       httpConn.connect();
       BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
       
       StringBuffer buffer = new StringBuffer(); String line;
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       resultStr = buffer.toString();
       reader.close();
       httpConn.disconnect();
     } catch (IOException e) {
       e.printStackTrace();
     } 
     return resultStr;
   }
 }

