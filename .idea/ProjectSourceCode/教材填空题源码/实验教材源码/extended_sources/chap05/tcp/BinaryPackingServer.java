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
    	 //����������
    	 server=new ServerSocket(port);
    	 System.out.println("TCP �����ƴ��������\n\n");
    	 System.out.printf("%s�����������ɹ����ڶ˿�%d����������\n", format.format(new Date()),port);
    	 //���տͻ�������
    	 while(true) {
    		 Socket s=null;
    		 s=server.accept();
    		 InetAddress clientAddr=s.getInetAddress();
    		 int clientPort=s.getPort();
    		 System.out.printf("%s�ͻ���%s��%s���ӳɹ�\n", format.format(new Date()),clientAddr.getHostAddress(),clientPort);
    		 //ѭ������ͻ�������
    		 while(true) {
    			 try {
    				 //��ȡ������
    				 InputStream is=s.getInputStream();
    				 DataInputStream dis=new DataInputStream(new BufferedInputStream(is));
    				 //��ȡ����
    				 short pktType=dis.readShort();
    				 int pktLen=dis.readInt();
    				 int seq=dis.readInt();
    				 long timeStamp=dis.readLong();
    				 //�������ݳ��ȶ�ȡ�ʵ����ֽ���
    				 byte[] msgBuf=new byte[pktLen-4-4-8];
    				 dis.readFully(msgBuf);
    				 String msg=new String(msgBuf);
    				 System.out.println(format.format(new Date())+
    						 "\n���յ��������������£�\n��������(short):"+pktType
    						 +"\n���ݳ��ȣ�int����"+pktLen
    						 +"\n��ţ�int����"+seq
    						 +"\n������ʱ�����long����"+timeStamp
    						 +"\n��Ϣ���ݣ�byte[]����"+msg+"\n");
    				 long localTimeStamp=new Date().getTime();
    				 int responseCode;
    				 if(timeStamp>localTimeStamp) {
    					 responseCode=400;
    				 }else {
    					 responseCode=200;
    				 }
    				 //��Ӧ�ͻ���
    				 OutputStream os=s.getOutputStream();
    				 DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(os));
    				 dos.writeInt(responseCode);
    				 dos.flush();
    				 System.out.printf("%s ��ͻ���%s������Ӧ�룺%d\n", format.format(new Date()),s.getInetAddress().getHostAddress(),responseCode);
    			 }catch(EOFException e) {
    				 System.out.printf("%s�ͻ���%s:%s�����ر�����\n", format.format(new Date()),clientAddr.getHostAddress(),clientPort);
    				 s.close();
    				 break;
    			 }catch(IOException e) {
    				 System.out.printf("%s�ͻ���%s��%s�����쳣�Ͽ�\n", format.format(new Date()),clientAddr.getHostAddress(),clientPort);
    				 break;
    			 }
    		 }
    	 }
     }
}
