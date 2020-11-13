 package  com.ai.rti.ic.grp.utils;
 
 import com.ai.rti.ic.grp.entity.TarGrpImportTask;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.ObjectOutputStream;
 import java.io.OutputStream;
 import java.net.Socket;
 
 
 public class SocketClient
 {
   private static final String HOST = "127.0.0.1";
   private static final int POST = 8888;
   private static String host = "";
 
 
   
   public void client(Socket socket, TarGrpImportTask tarGrpImportTask) throws IOException {
     OutputStream outputStream = socket.getOutputStream();
     ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
     objectOutputStream.writeObject(tarGrpImportTask);
     
     InputStream inputStream = socket.getInputStream();
     int result = inputStream.read();
     
     if (result == 0) {
       System.out.println("这个服务器接收到消息了!");
     }
     outputStream.close();
     objectOutputStream.close();
     inputStream.close();
     socket.close();
   }
 
 
   
   public static void main(String[] args) throws IOException {
     TarGrpImportTask tarGrpImportTask = new TarGrpImportTask();
     tarGrpImportTask.setTarGrpId("1111111");
     com.ai.rti.ic.grp.utils.SocketClient socketClient = new com.ai.rti.ic.grp.utils.SocketClient();
     socketClient.client(socketClient.getSocket(), tarGrpImportTask);
   }
 
   
   public Socket getSocket() throws IOException {
     synchronized (host) {
       return new Socket("127.0.0.1", 8888);
     } 
   }
 }
