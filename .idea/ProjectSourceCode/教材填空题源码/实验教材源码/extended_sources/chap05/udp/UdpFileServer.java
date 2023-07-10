package net.udp;
//UdpFileServer.java
import java.io.*;
import java.net.*;
import java.util.Random;
//UDP�ļ�������
public class UdpFileServer {
	public static void main(String[] args) {
		int PORT = 8000; // �����������˿�
		DatagramSocket ds;// UDP�������׽���
		System.out.println("UDP�ļ�������");
		try {
			ds = new DatagramSocket(PORT);
			System.out.printf("UDP�ļ������������ɹ�,�󶨶˿�:%d\n", PORT);
			while (true) {
				// ����UDP����
				DatagramPacket dp = UdpUtils.recvPkt(ds);
				// ת��Ϊ��������������ȡ��������
				DataInputStream dis = UdpUtils.getUdpPktStream(dp);
				// ��ȡ��������
				short packtType = dis.readShort();
				// �жϱ��������Ƿ��ļ���Ϣ����
				if (packtType == UdpUtils.PKT_FILE_INFO) {
					// ��ȡ�ļ������ļ���С
					String filename = dis.readUTF();
					long filesize = dis.readLong();
					// ��ȡ�ͻ���IP��ַ�Ͷ˿ں�
					InetAddress clientAddr = dp.getAddress();
					int clientPort = dp.getPort();
					System.out.printf(
							"\n���յ�����%s:%d�ͻ����ļ���������,�ļ���:%s,��С:%.2fKB.\n",
							clientAddr.getHostAddress(), clientPort, filename,
							filesize / 1024.f);
					// �����µ�UDP�׽���,���ڽ����ļ�
					DatagramSocket fileDs = new DatagramSocket();
					int filePort = fileDs.getLocalPort();
					// ���ذ������ļ�����˿ڵ�ȷ�ϱ��ĸ��ͻ���
					UdpUtils.sendAckPkt(ds, filePort, clientAddr, clientPort);
					System.out.printf("��ͻ���%s:%d����ACK����,�ļ�����˿ں�:%d.\n",
							clientAddr.getHostAddress(), clientPort, filePort);
					// ��ͻ��˽����ļ������ͨ��
					fileDs.connect(clientAddr, clientPort);
					
					// ����һ���µ��ļ������߳�
					Thread myThread = new Thread(new RecvFileThread1(filename,
							filesize, fileDs));
					myThread.start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// �ļ������߳���
class RecvFileThread1 implements Runnable {
	String filename; // �ļ���
	long filesize; // �ļ���С
	DatagramSocket fileDs; // ���ڴ����ļ���UDP�׽���
	public RecvFileThread1(String filename, long filesize, DatagramSocket fileDs) {
		this.filename = filename;
		this.filesize = filesize;
		this.fileDs = fileDs;
	}

	// �����ļ�����
	public void recvFile() throws IOException {
		System.out.printf("###��ʼ�����ļ�%s.\n", filename);
		// �����ļ����������
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(filename));
		long ackSeq = -1;// ȷ�����к�,��ʼ��Ϊ-1
		int readBytes = 0;// �Ѿ����յ��ֽ���
		// �����ļ����ݵĻ�����
		byte[] buf = new byte[1024 * 8];
		// ��ʼ�����ļ�����
		while (readBytes < filesize) {
			// ����UDP����
			DatagramPacket dp = UdpUtils.recvPkt(fileDs);
			DataInputStream dis = UdpUtils.getUdpPktStream(dp);
			// ��ȡ�����ֶ�,�ж��ֶ������Ƿ���ȷ
			if (dis.readShort() != UdpUtils.PKT_FILE_DATA) {
				System.out.println("���ĸ�ʽ����..");
				return;
			}
			// ��ȡ���к�
			long seq = dis.readLong();
			// �ж����кŵ���ȷ��
			if (ackSeq == -1)
				ackSeq = seq + 1;
			else if (ackSeq != seq) {
				System.out.println("���кŴ��󣬳����˳� ");
				return;
			}
			ackSeq = seq + 1;
			// ��ȡ�ļ����ݲ���
			int len = dis.read(buf);
			dis.close();
			// д���ļ���
			bos.write(buf, 0, len);
			bos.flush();
			readBytes += len;
			// ����ACKȷ�ϱ���
			UdpUtils.sendAckPkt(fileDs, ackSeq, fileDs.getInetAddress(),
					fileDs.getPort());
		}
		bos.close();
		System.out.printf("�ļ�%s���ճɹ�,��%.2fKB.\n", filename, filesize / 1024.f);
	}
	// �߳�����
	public void run() {
		try {
			recvFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}