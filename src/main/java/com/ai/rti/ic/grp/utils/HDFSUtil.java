 package  com.ai.rti.ic.grp.utils;
 
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.List;
 import org.apache.hadoop.conf.Configuration;
 import org.apache.hadoop.fs.FSDataInputStream;
 import org.apache.hadoop.fs.FSDataOutputStream;
 import org.apache.hadoop.fs.FileStatus;
 import org.apache.hadoop.fs.FileSystem;
 import org.apache.hadoop.fs.Path;
 import org.apache.hadoop.io.IOUtils;
 import org.apache.log4j.Logger;
 
 
 
 public final class HDFSUtil
 {
   private static Configuration conf = new Configuration();
   private static FileSystem fs;
   private static final Logger log = Logger.getLogger(com.ai.rti.ic.grp.utils.HDFSUtil.class);
   static {
     try {
       InputStream coreSiteStream = com.ai.rti.ic.grp.utils.HDFSUtil.class.getResourceAsStream("/conf/core-site.xml");
       InputStream hdfsSiteStream = com.ai.rti.ic.grp.utils.HDFSUtil.class.getResourceAsStream("/conf/hdfs-site.xml");
       conf.addResource(coreSiteStream);
       conf.addResource(hdfsSiteStream);
       conf.setBoolean("fs.hdfs.impl.disable.cache", true);
       conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
       fs = FileSystem.get(conf);
     } catch (IOException e) {
       e.printStackTrace();
     } 
   }
 
 
 
 
   
   public static boolean makeDir(String path) throws IOException {
     return fs.mkdirs(new Path(path));
   }
 
 
 
   
   public static boolean exists(String path) throws IOException {
     return fs.exists(new Path(path));
   }
 
 
 
   
   public static void writeFile(String path, List<String> data) throws IOException {
     FileSystem fs = FileSystem.get(conf);
     OutputStreamWriter osw = new OutputStreamWriter((OutputStream)fs.create(new Path(path)), "UTF-8");
     PrintWriter writer = new PrintWriter(osw);
     for (String row : data) {
       writer.println(row);
     }
     writer.flush();
     writer.close();
     fs.close();
   }
 
 
 
 
 
   
   public static void uploadFileToHDFS(String filePath, String dst) throws Exception {
     log.info("开始上传hdfs文件：filePath：" + filePath + " dst: " + dst);
     FileSystem fs = FileSystem.get(conf);
     Path srcPath = new Path(filePath);
     Path dstPath = new Path(dst);
     Long start = Long.valueOf(System.currentTimeMillis());
     fs.copyFromLocalFile(false, srcPath, dstPath);
     System.out.println("Time:" + (System.currentTimeMillis() - start.longValue()));
     System.out.println("________________________Upload to " + conf.get("fs.default.name") + "________________________");
     fs.close();
     log.info("hdfs上传成功：filePath：" + filePath + " dst: " + dst);
   }
   
   public static void copyFromServerFolder(String sourceFolder, String dstFolder) throws Exception {
     log.info("开始复制hdfs文件夹：sourceFolder：" + sourceFolder + " dstFolder: " + dstFolder);
     Long start = Long.valueOf(System.currentTimeMillis());
     
     FileSystem fs = FileSystem.get(conf);
     Path srcPath = new Path(sourceFolder);
     FileStatus[] listStatus = fs.listStatus(srcPath);
     for (FileStatus fileStatus : listStatus) {
       Path hadPath = fileStatus.getPath();
       FSDataOutputStream destOutputStream = fs.create(new Path(dstFolder + hadPath.getName()));
       FSDataInputStream sourceInputStream = fs.open(hadPath);
       byte[] buf = new byte[640000];
       int readBytes;
       while ((readBytes = sourceInputStream.read(buf)) > 0) {
         destOutputStream.write(buf, 0, readBytes);
       }
       destOutputStream.close();
       destOutputStream.close();
     } 
     log.info("Time:" + (System.currentTimeMillis() - start.longValue()));
     log.info("________________________Upload to " + conf.get("fs.default.name") + "________________________");
     fs.close();
     log.info("复制hdfs文件夹成功：sourceFolder：" + sourceFolder + " dstFolder: " + dstFolder);
   }
 
 
 
 
 
   
   public static void downLoadFileFromHDFS(String src) throws Exception {
     FileSystem fs = FileSystem.get(conf);
     Path srcPath = new Path(src);
     FSDataInputStream fSDataInputStream = fs.open(srcPath);
     
     try {
       IOUtils.copyBytes((InputStream)fSDataInputStream, System.out, 4096, false);
     } finally {
       IOUtils.closeStream((Closeable)fSDataInputStream);
       fs.close();
     } 
   }
   
   public static void main(String[] args) throws Exception {
     String filePath = "J:/lihd.txt";
     String dst = "hdfs://jsdxcluster/user/coc/customerFiles/";
     uploadFileToHDFS(filePath, dst);
   }
 }
