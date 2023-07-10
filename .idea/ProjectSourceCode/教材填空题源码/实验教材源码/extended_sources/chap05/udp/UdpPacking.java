package net.udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

public class UdpPacking {
//	public byte[] packing(int seq, short command, int ip, String filename){
//		
//	}
	public static void main(String[] args) throws Exception {
		int seq=100303;
		short command=1;
		String filename="film.dat";
		DatagramSocket  udpClient=new DatagramSocket(8000);
		ByteArrayOutputStream os = new ByteArrayOutputStream(8192);
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeInt(seq);
		dos.writeShort(command);
		dos.writeUTF(filename);
		dos.flush();
		byte[] sendBuf= os.toByteArray();  //转换为字节数组
		while(true){
		
		DatagramPacket dp = new DatagramPacket(sendBuf, sendBuf.length,
				InetAddress.getByName("127.0.0.1"), 80);
		udpClient.send(dp);
		Thread.sleep(1000);
		}
	}
}
