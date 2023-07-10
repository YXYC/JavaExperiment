/* 服务端程序： TimeServer.java */
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.util.*;
public class TimeServer {
   final static int TIME_PORT=4000;
   public static void main(String [] args) throws IOException{
   DatagramSocket skt=new DatagramSocket(TIME_PORT);
   while(true) {
      byte buffer[]=new byte[100];
      DatagramPacket p=new DatagramPacket(buffer,buffer.length);
      skt.receive(p);
      Date date=new Date();
      DateFormat timeformat=DateFormat.getTimeInstance();
      String datestr=timeformat.format(date);
      System.out.println(datestr);
      buffer=datestr.getBytes();
      InetAddress address=p.getAddress();
      int port=p.getPort();
      p=new DatagramPacket(buffer,buffer.length,address,port);
      skt.send(p);
   }
  }
}