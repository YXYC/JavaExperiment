package net.udp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class UdpClient {
	public static void main(String[] args) throws Exception {
		new Thread(new RecvThread()).start();
		DatagramSocket udpClient = new DatagramSocket();
		InetAddress dstIp = InetAddress.getByName("127.0.0.1");
		String info="Java网络编程";
		byte[] buf=info.getBytes();
		DatagramPacket dp = new DatagramPacket(buf, buf.length, dstIp, 80);
//		while (true) {
//			udpClient.send(dp);
//
//			System.out.println("发送成功");
//			Thread.sleep(1);
//		}
	}
}
class RecvThread implements Runnable {
	@Override
	public void run() {
		try {
			DatagramSocket ds = new DatagramSocket(80);
			
			while (true) {
				byte[] buf= new byte[8192];
				DatagramPacket dp= new DatagramPacket(buf, buf.length);
				ds.receive(dp);
				System.out.println("接收到的报文长度为" + dp.getLength());
//				String  s=new String(dp.getData(), dp.getOffset(), dp.getLength());
//				System.out.println(s+dp.getOffset());
				ByteArrayInputStream bis = new ByteArrayInputStream(buf, dp.getOffset(), dp.getLength());
				DataInputStream dis = new DataInputStream(bis);
				System.out.println("序号为:" + dis.readInt());
				System.out.println("命令为:" + dis.readShort());
				
				System.out.println("文件名为:" + dis.readUTF());
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 发送数据报文,如果超时没有接收到确认，则进行重传，超过重传次数还没有接收到报文，则返回null对象
		public static DatagramPacket sendDataPacket(DatagramSocket ds,
				byte[] sendBuf, InetAddress remoteAddr, int remotePort,
				int retryTimes) throws IOException {
			// 构造发送报文
			DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length,
					remoteAddr, remotePort);
			// 构造接收ACK的报文
			byte[] recvBuf = new byte[8192];
			DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
			int times = 0;
			while (true) {
				// 发送UDP报文
				ds.send(sendPacket);
				// 等待服务器确认报文
				try {
					ds.receive(recvPacket);
					// 如果没有超时，跳出循环
					return recvPacket;
				} catch (SocketTimeoutException e) {
					times++;
					System.out.println("报文超时,进行第" + times + "重传");
					// 如果超过重传次数,程序退出
					if (times >= retryTimes) {
						return null;
					}
				}
			}
		}
}
