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
		String info="Java������";
		byte[] buf=info.getBytes();
		DatagramPacket dp = new DatagramPacket(buf, buf.length, dstIp, 80);
//		while (true) {
//			udpClient.send(dp);
//
//			System.out.println("���ͳɹ�");
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
				System.out.println("���յ��ı��ĳ���Ϊ" + dp.getLength());
//				String  s=new String(dp.getData(), dp.getOffset(), dp.getLength());
//				System.out.println(s+dp.getOffset());
				ByteArrayInputStream bis = new ByteArrayInputStream(buf, dp.getOffset(), dp.getLength());
				DataInputStream dis = new DataInputStream(bis);
				System.out.println("���Ϊ:" + dis.readInt());
				System.out.println("����Ϊ:" + dis.readShort());
				
				System.out.println("�ļ���Ϊ:" + dis.readUTF());
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// �������ݱ���,�����ʱû�н��յ�ȷ�ϣ�������ش��������ش�������û�н��յ����ģ��򷵻�null����
		public static DatagramPacket sendDataPacket(DatagramSocket ds,
				byte[] sendBuf, InetAddress remoteAddr, int remotePort,
				int retryTimes) throws IOException {
			// ���췢�ͱ���
			DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length,
					remoteAddr, remotePort);
			// �������ACK�ı���
			byte[] recvBuf = new byte[8192];
			DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
			int times = 0;
			while (true) {
				// ����UDP����
				ds.send(sendPacket);
				// �ȴ�������ȷ�ϱ���
				try {
					ds.receive(recvPacket);
					// ���û�г�ʱ������ѭ��
					return recvPacket;
				} catch (SocketTimeoutException e) {
					times++;
					System.out.println("���ĳ�ʱ,���е�" + times + "�ش�");
					// ��������ش�����,�����˳�
					if (times >= retryTimes) {
						return null;
					}
				}
			}
		}
}
