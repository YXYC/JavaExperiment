package net.udp;
//UdpFileServer.java
import java.io.*;
import java.net.*;
import java.util.Random;
//UDP文件服务器
public class UdpFileServer {
	public static void main(String[] args) {
		int PORT = 8000; // 服务器监听端口
		DatagramSocket ds;// UDP服务器套接字
		System.out.println("UDP文件服务器");
		try {
			ds = new DatagramSocket(PORT);
			System.out.printf("UDP文件服务器启动成功,绑定端口:%d\n", PORT);
			while (true) {
				// 接收UDP报文
				DatagramPacket dp = UdpUtils.recvPkt(ds);
				// 转换为数据输入流，读取报文内容
				DataInputStream dis = UdpUtils.getUdpPktStream(dp);
				// 读取报文类型
				short packtType = dis.readShort();
				// 判断报文类型是否文件信息报文
				if (packtType == UdpUtils.PKT_FILE_INFO) {
					// 读取文件名、文件大小
					String filename = dis.readUTF();
					long filesize = dis.readLong();
					// 读取客户端IP地址和端口号
					InetAddress clientAddr = dp.getAddress();
					int clientPort = dp.getPort();
					System.out.printf(
							"\n接收到来自%s:%d客户端文件传输请求,文件名:%s,大小:%.2fKB.\n",
							clientAddr.getHostAddress(), clientPort, filename,
							filesize / 1024.f);
					// 创建新的UDP套接字,用于接收文件
					DatagramSocket fileDs = new DatagramSocket();
					int filePort = fileDs.getLocalPort();
					// 发回包含有文件传输端口的确认报文给客户端
					UdpUtils.sendAckPkt(ds, filePort, clientAddr, clientPort);
					System.out.printf("向客户端%s:%d发回ACK报文,文件传输端口号:%d.\n",
							clientAddr.getHostAddress(), clientPort, filePort);
					// 与客户端建立文件传输的通道
					fileDs.connect(clientAddr, clientPort);
					
					// 创建一个新的文件接收线程
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

// 文件接收线程类
class RecvFileThread1 implements Runnable {
	String filename; // 文件名
	long filesize; // 文件大小
	DatagramSocket fileDs; // 用于传输文件的UDP套接字
	public RecvFileThread1(String filename, long filesize, DatagramSocket fileDs) {
		this.filename = filename;
		this.filesize = filesize;
		this.fileDs = fileDs;
	}

	// 接收文件函数
	public void recvFile() throws IOException {
		System.out.printf("###开始接收文件%s.\n", filename);
		// 创建文件输出缓冲流
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(filename));
		long ackSeq = -1;// 确认序列号,初始化为-1
		int readBytes = 0;// 已经接收的字节数
		// 接收文件数据的缓冲区
		byte[] buf = new byte[1024 * 8];
		// 开始接收文件内容
		while (readBytes < filesize) {
			// 接收UDP报文
			DatagramPacket dp = UdpUtils.recvPkt(fileDs);
			DataInputStream dis = UdpUtils.getUdpPktStream(dp);
			// 读取报文字段,判断字段类型是否正确
			if (dis.readShort() != UdpUtils.PKT_FILE_DATA) {
				System.out.println("报文格式错误..");
				return;
			}
			// 读取序列号
			long seq = dis.readLong();
			// 判断序列号的正确性
			if (ackSeq == -1)
				ackSeq = seq + 1;
			else if (ackSeq != seq) {
				System.out.println("序列号错误，程序退出 ");
				return;
			}
			ackSeq = seq + 1;
			// 读取文件数据部分
			int len = dis.read(buf);
			dis.close();
			// 写入文件中
			bos.write(buf, 0, len);
			bos.flush();
			readBytes += len;
			// 发回ACK确认报文
			UdpUtils.sendAckPkt(fileDs, ackSeq, fileDs.getInetAddress(),
					fileDs.getPort());
		}
		bos.close();
		System.out.printf("文件%s接收成功,共%.2fKB.\n", filename, filesize / 1024.f);
	}
	// 线程主体
	public void run() {
		try {
			recvFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}