package net.udp;
//UdpUtils.java
import java.io.*;
import java.net.*;

//UDP���Ĺ�����
public class UdpUtils {
	// �����ļ��������õ��ı�������
	public final static short PKT_FILE_INFO = 1;// �ļ���Ϣ����
	public final static short PKT_FILE_DATA = 2;// ���ݱ���
	public final static short PKT_ACK = 3;// ȷ�ϱ���
	// ����UDP���ģ�����DatagramPacket���Ķ���
	// DatagramSocket ds, �������ݰ���UDP�׽���
	public static DatagramPacket recvPkt(DatagramSocket ds) throws IOException {
		byte[] buf = new byte[8192];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		ds.receive(dp);
		return dp;
	}

	// ��UDP���Ķ���ת��ΪDataInputStream��������������
	// DatagramPacket dp, UDP���Ķ���
	public static DataInputStream getUdpPktStream(DatagramPacket dp) {
		// ��װΪ�ֽ�����������
		ByteArrayInputStream bis = new ByteArrayInputStream(dp.getData(),
				dp.getOffset(), dp.getLength());
		System.out.println("server port="+dp.getPort());
		return new DataInputStream(bis);
	}

	// ��ȷ��ACK���ķ��͸�Զ������,�Խ��յ��ı��Ľ���ȷ��.ȷ�ϱ��ĸ�ʽ:��������+seq
	// DatagramSocket ds,����ACK���ĵ�UDP�׽���
	// long seq, ȷ�ϱ����е����к�
	// InetAddress remoteAddr, ȷ�ϱ��ķ��͵�Ŀ�ĵ�ַ
	// int remotePort, ȷ�ϱ��ķ��͵�Ŀ�Ķ˿�
	public static void sendAckPkt(DatagramSocket ds, long seq,
			InetAddress remoteAddr, int remotePort) throws IOException {
		// �����ֽ������
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		// д�뱨������
		dos.writeShort(UdpUtils.PKT_ACK);
		// д�����к�
		dos.writeLong(seq);
		dos.flush();
		// ת��Ϊ�ֽ�����
		byte[] buf = bos.toByteArray();
		DatagramPacket sendPkt = new DatagramPacket(buf, buf.length,
				remoteAddr, remotePort);
		ds.send(sendPkt);
	}
}
