package net.udp;
//UdpUtils.java
import java.io.*;
import java.net.*;

//UDP报文工具类
public class UdpUtils {
	// 定义文件传输中用到的报文类型
	public final static short PKT_FILE_INFO = 1;// 文件信息报文
	public final static short PKT_FILE_DATA = 2;// 数据报文
	public final static short PKT_ACK = 3;// 确认报文
	// 接收UDP报文，返回DatagramPacket报文对象
	// DatagramSocket ds, 接收数据包的UDP套接字
	public static DatagramPacket recvPkt(DatagramSocket ds) throws IOException {
		byte[] buf = new byte[8192];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		ds.receive(dp);
		return dp;
	}

	// 将UDP报文对象转换为DataInputStream数据输入流对象
	// DatagramPacket dp, UDP报文对象
	public static DataInputStream getUdpPktStream(DatagramPacket dp) {
		// 封装为字节数组输入流
		ByteArrayInputStream bis = new ByteArrayInputStream(dp.getData(),
				dp.getOffset(), dp.getLength());
		System.out.println("server port="+dp.getPort());
		return new DataInputStream(bis);
	}

	// 将确认ACK报文发送给远程主机,对接收到的报文进行确认.确认报文格式:报文类型+seq
	// DatagramSocket ds,发送ACK报文的UDP套接字
	// long seq, 确认报文中的序列号
	// InetAddress remoteAddr, 确认报文发送的目的地址
	// int remotePort, 确认报文发送的目的端口
	public static void sendAckPkt(DatagramSocket ds, long seq,
			InetAddress remoteAddr, int remotePort) throws IOException {
		// 构造字节输出流
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		// 写入报文类型
		dos.writeShort(UdpUtils.PKT_ACK);
		// 写入序列号
		dos.writeLong(seq);
		dos.flush();
		// 转换为字节数组
		byte[] buf = bos.toByteArray();
		DatagramPacket sendPkt = new DatagramPacket(buf, buf.length,
				remoteAddr, remotePort);
		ds.send(sendPkt);
	}
}
