/* 客户端程序:  TimeClient.java */
import java.io.*;
import java.net.*;
public class TimeClient {
final static int TIME_PORT=4000;
public static void main(String args[]) throws IOException  {
     if(args.length==0){
        System.err.println("Not specify server name!");
        System.exit(-1);
     }
     String host=args[0];
     byte msg[]=new byte[100];
     InetAddress address=InetAddress.getByName(host);
     System.out.println("Sending service request to" + address);
     DatagramPacket p=new DatagramPacket(msg,msg.length,address,TIME_PORT);
     DatagramSocket skt=new DatagramSocket();
     skt.send(p);
     p=new DatagramPacket(msg,msg.length);
     skt.receive(p);
     String time=new String(p.getData());
     System.out.println("The time at "+host+" is: "+time.trim());
     executeCmd(time.trim());
     skt.close();
   }
   public static void executeCmd(String command) throws IOException {  
	    Runtime runtime = Runtime.getRuntime();  
                    String cmdstr="nircmdc elevate cmd /C time "+command; 
                    System.out.println(cmdstr);
	               Process process = runtime.exec(cmdstr);  
	}  
}