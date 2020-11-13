 package  com.ai.rti.ic.grp.utils;
 
 import com.ai.rti.ic.grp.entity.TarGrpImportTask;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.ObjectInputStream;
 import java.io.OutputStream;
 import java.net.ServerSocket;
 import java.net.Socket;
 
 
 
 
 public class SocketService
 {
   public void service() throws IOException, ClassNotFoundException {
     ServerSocket socketServer = new ServerSocket(8888);
 
     
     while (true) {
       Socket socket = socketServer.accept();
       InputStream inputStream = socket.getInputStream();
       ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
       TarGrpImportTask tarGrpImportTask = (TarGrpImportTask)objectInputStream.readObject();
       System.out.println(tarGrpImportTask.getTarGrpId());
       OutputStream outputStream = socket.getOutputStream();
       outputStream.write(0);
       objectInputStream.close();
       objectInputStream.close();
       socket.close();
     } 
   }
 
 
 
 
   
   public static void main(String[] args) throws IOException, ClassNotFoundException {
     com.ai.rti.ic.grp.utils.SocketService socketService = new com.ai.rti.ic.grp.utils.SocketService();
     socketService.service();
   }
 }

