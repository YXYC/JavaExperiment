package net.udp;
//UdpFileClient.java
import java.io.*;
import java.net.*;
import java.util.*;


//UDP�ļ�����ͻ���
public class UdpFileClient {
	public static void main(String[] args) throws IOException {
		String filename; // ���͵��ļ���
		// ��������ַ
		InetAddress server = InetAddress.getByName("124.70.66.251");
		int port = 8000; // �������˿ں�
		int max_times = 3; // ���ʱ�ش�����
		int time_out = 5000; // ��ʱʱ��Ϊ500ms
		System.out.println("UDP�ļ�����ͻ���");
		System.out.printf("�ͻ��˲�������: ������IP=%s �˿ں�=%d ��ʱ�ش�����=%d ���ĳ�ʱʱ��=%d����\n",
				server.getHostAddress(), port, max_times, time_out);

		Scanner scanner = new Scanner(System.in);
		System.out.print("������Ҫ���͵��ļ���(����������·��):");
		// ��ȡ�ļ���
		filename = scanner.nextLine();
		// �����ļ�����
		File file = new File(filename);
		System.out.println("�����ļ�:" + file.getName() + ",��С��:" + file.length()
				/ 1024.f + "KB\n");
		DatagramSocket udpClient = new DatagramSocket();
		// ��UDP������������Ե��ͨ��
		udpClient.connect(server, port);
		// ���ó�ʱʱ��
		udpClient.setSoTimeout(time_out);
		// �����ļ���Ϣ����
		byte[] sendBuf = buildFileInfoPkt(file);
		// ���ͱ���,����ȡ������ȷ�ϱ���,ת��Ϊ����������
		DataInputStream dis = getResPktStream(udpClient, sendBuf, max_times);
		if (dis == null) {
			System.out.println("û�н��յ�ȷ�ϱ���,����������Ƿ����������������,�����˳�...");
			System.exit(0);
		}
		// ��ȡ�ļ�����Ķ˿ں�,���ĸ�ʽ:��������+�ļ�����˿ں�
		if (dis.readShort() != UdpUtils.PKT_ACK) {
			System.out.println("���Ϸ����ģ������˳�!");
			System.exit(0);
		}
		// ��ȡ�ļ�����˿ں�
		int filePort = (int) dis.readLong();
		System.out.printf("##���յ�������Ӧ��,�������ļ�����˿�Ϊ%d.\n", filePort);
		long readBytes = 0;
		// �ļ����뻺����
		byte[] buf = new byte[512];
		// �ļ����뻺����
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		// ��ʼ���ļ��������к�,�������
		Random rand = new Random();
		long seq = Math.abs(rand.nextLong());
		// ���ԭ������,���ļ�����˿ڽ����µ�ͨ��
		udpClient.disconnect();
		udpClient.connect(server, filePort);
		System.out.println("##��ʼ�����ļ�" + filename);
		// �����ļ�
		while (readBytes < file.length()) {
			int len = bis.read(buf);
			// �������кź����ݹ������ݱ���
			sendBuf = buildDataPacket(seq, buf);
			// ���ͱ���,��ȡ������ȷ�ϱ���,��ת��Ϊ����������
			dis = getResPktStream(udpClient, sendBuf, max_times);
			// ��ȡ��������,���к�
			short pktType = dis.readShort();
			long ackSeq = dis.readLong();
			// �ж�ȷ�����ͺ����к��Ƿ�Ϸ�
			if (pktType == UdpUtils.PKT_ACK && ackSeq == (seq + 1)) {
				readBytes += len;
			} else {
				System.out.printf("%d%dȷ�ϱ��ĵ����ͻ�����ų���,�����˳�!", pktType, ackSeq);
				System.exit(0);
			}
			seq++;
		}
		System.out.printf("�ļ�%s���ͳɹ�,��%.2fKB\n", filename,
				file.length() / 1024.f);
	}

	// �����ļ�������������ļ������ļ���С���ֽ�����
	// File file, �ļ�����
	public static byte[] buildFileInfoPkt(File file) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		// ��ȡ�ļ���С���ļ���
		long filesize = file.length();
		String name = file.getName();
		dos.writeShort(UdpUtils.PKT_FILE_INFO);
		dos.writeUTF(name);
		dos.writeLong(filesize);
		dos.flush();
		return bos.toByteArray();
	}

	// ����������ļ����ݺ����кŵ��ֽ�����
	// long seq, ���к�
	// byte[] data �ļ�����
	public static byte[] buildDataPacket(long seq, byte[] data)
			throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeShort(UdpUtils.PKT_FILE_DATA);
		dos.writeLong(seq);
		dos.write(data);
		dos.flush();
		return bos.toByteArray();
	}

	// ����UDP���Ĳ���ȡȷ��,����ȷ�ϱ��ĵ�����������
	// �����ʱû��ȷ��,������ش�.�ش�����������û�յ�ȷ��,����null
	// DatagramSocket ds, ����UDP���ĵ��׽���
	// byte[] buf, UDP���ĵ����ݲ���
	// int maxTimes, ����ش�����
	public static DataInputStream getResPktStream(DatagramSocket ds,
			byte[] buf, int maxTimes) throws IOException {
		// ����UDP���Ķ���
		DatagramPacket sendPkt = new DatagramPacket(buf, buf.length);
		int times = 0;// �ش�����
		while (times < maxTimes) {
			ds.send(sendPkt);
			try {
				// �ȴ�ȷ�ϱ���
				DatagramPacket recvPkt = UdpUtils.recvPkt(ds);
				// ����ȷ�ϱ��ĵ���������������
				return UdpUtils.getUdpPktStream(recvPkt);
			} catch (SocketTimeoutException e) {
				times++;
				System.out.println("���ĳ�ʱ,���е�" + times + "�ش�");
				continue;
			}
		}
		// �����ش�����,����null����
		return null;
	}
}
