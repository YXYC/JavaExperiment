package net.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BinaryPackingServer {
     public static void main(String[] args) throws IOException {
    	 int port=8999;
    	 ServerSocket server=null;
    	 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	 //启动服务器
    	 server=new ServerSocket(port);
    	 System.out.println("TCP 二进制传输服务器\n\n");
    	 System.out.printf("%s服务器启动成功，在端口%d侦听。。。\n", format.format(new Date()),port);
    	 //接收客户端连接
    	 while(true) {
    		 Socket s=null;
    		 s=server.accept();
    		 InetAddress clientAddr=s.getInetAddress();
    		 int clientPort=s.getPort();
    		 System.out.printf("%s客户端%s：%s连接成功\n", format.format(new Date()),clientAddr.getHostAddress(),clientPort);
    		 //循环处理客户端请求
    		 while(true) {
    			 try {
    				 //获取输入流
    				 InputStream is=s.getInputStream();
    				 DataInputStream dis=new DataInputStream(new BufferedInputStream(is));
    				 //读取数据
    				 short pktType=dis.readShort();
    				 int pktLen=dis.readInt();
    				 int seq=dis.readInt();
    				 long timeStamp=dis.readLong();
    				 //根据数据长度读取适当的字节数
    				 byte[] msgBuf=new byte[pktLen-4-4-8];
    				 dis.readFully(msgBuf);
    				 String msg=new String(msgBuf);
    				 System.out.println(format.format(new Date())+
    						 "\n接收到的数据内容如下：\n报文类型(short):"+pktType
    						 +"\n数据长度（int）："+pktLen
    						 +"\n序号（int）："+seq
    						 +"\n本主机时间戳（long）："+timeStamp
    						 +"\n消息内容（byte[]）："+msg+"\n");
    				 long localTimeStamp=new Date().getTime();
    				 int responseCode;
    				 if(timeStamp>localTimeStamp) {
    					 responseCode=400;
    				 }else {
    					 responseCode=200;
    				 }
    				 //响应客户端
    				 OutputStream os=s.getOutputStream();
    				 DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(os));
    				 dos.writeInt(responseCode);
    				 dos.flush();
    				 System.out.printf("%s 向客户端%s发回响应码：%d\n", format.format(new Date()),s.getInetAddress().getHostAddress(),responseCode);
    			 }catch(EOFException e) {
    				 System.out.printf("%s客户端%s:%s正常关闭连接\n", format.format(new Date()),clientAddr.getHostAddress(),clientPort);
    				 s.close();
    				 break;
    			 }catch(IOException e) {
    				 System.out.printf("%s客户端%s：%s连接异常断开\n", format.format(new Date()),clientAddr.getHostAddress(),clientPort);
    				 break;
    			 }
    		 }
    	 }
     }
}
