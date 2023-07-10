package net.udp;
//UdpFileClient.java
import java.io.*;
import java.net.*;
import java.util.*;


//UDP文件传输客户端
public class UdpFileClient {
	public static void main(String[] args) throws IOException {
		String filename; // 发送的文件名
		// 服务器地址
		InetAddress server = InetAddress.getByName("124.70.66.251");
		int port = 8000; // 服务器端口号
		int max_times = 3; // 最大超时重传次数
		int time_out = 5000; // 超时时间为500ms
		System.out.println("UDP文件传输客户端");
		System.out.printf("客户端参数设置: 服务器IP=%s 端口号=%d 超时重传次数=%d 报文超时时间=%d毫秒\n",
				server.getHostAddress(), port, max_times, time_out);

		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入要发送的文件名(包含完整的路径):");
		// 读取文件名
		filename = scanner.nextLine();
		// 构造文件对象
		File file = new File(filename);
		System.out.println("发送文件:" + file.getName() + ",大小共:" + file.length()
				/ 1024.f + "KB\n");
		DatagramSocket udpClient = new DatagramSocket();
		// 与UDP服务器建立点对点的通道
		udpClient.connect(server, port);
		// 设置超时时间
		udpClient.setSoTimeout(time_out);
		// 构造文件信息报文
		byte[] sendBuf = buildFileInfoPkt(file);
		// 发送报文,并读取服务器确认报文,转换为数据输入流
		DataInputStream dis = getResPktStream(udpClient, sendBuf, max_times);
		if (dis == null) {
			System.out.println("没有接收到确认报文,请检查服务器是否启动或者网络故障,程序退出...");
			System.exit(0);
		}
		// 读取文件传输的端口号,报文格式:报文类型+文件传输端口号
		if (dis.readShort() != UdpUtils.PKT_ACK) {
			System.out.println("不合法报文，程序退出!");
			System.exit(0);
		}
		// 读取文件传输端口号
		int filePort = (int) dis.readLong();
		System.out.printf("##接收到服务器应答,服务器文件传输端口为%d.\n", filePort);
		long readBytes = 0;
		// 文件输入缓冲区
		byte[] buf = new byte[512];
		// 文件输入缓冲流
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		// 初始化文件报文序列号,随机生成
		Random rand = new Random();
		long seq = Math.abs(rand.nextLong());
		// 解除原来连接,与文件传输端口建立新的通道
		udpClient.disconnect();
		udpClient.connect(server, filePort);
		System.out.println("##开始传输文件" + filename);
		// 传输文件
		while (readBytes < file.length()) {
			int len = bis.read(buf);
			// 根据序列号和数据构造数据报文
			sendBuf = buildDataPacket(seq, buf);
			// 发送报文,读取服务器确认报文,并转换为数据输入流
			dis = getResPktStream(udpClient, sendBuf, max_times);
			// 读取报文类型,序列号
			short pktType = dis.readShort();
			long ackSeq = dis.readLong();
			// 判断确认类型和序列号是否合法
			if (pktType == UdpUtils.PKT_ACK && ackSeq == (seq + 1)) {
				readBytes += len;
			} else {
				System.out.printf("%d%d确认报文的类型或者序号出错,程序退出!", pktType, ackSeq);
				System.exit(0);
			}
			seq++;
		}
		System.out.printf("文件%s发送成功,共%.2fKB\n", filename,
				file.length() / 1024.f);
	}

	// 根据文件对象构造包含有文件名、文件大小的字节数组
	// File file, 文件对象
	public static byte[] buildFileInfoPkt(File file) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		// 获取文件大小与文件名
		long filesize = file.length();
		String name = file.getName();
		dos.writeShort(UdpUtils.PKT_FILE_INFO);
		dos.writeUTF(name);
		dos.writeLong(filesize);
		dos.flush();
		return bos.toByteArray();
	}

	// 构造包含有文件数据和序列号的字节数组
	// long seq, 序列号
	// byte[] data 文件数据
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

	// 发送UDP报文并读取确认,返回确认报文的数据输入流
	// 如果超时没有确认,则进行重传.重传超过次数后还没收到确认,返回null
	// DatagramSocket ds, 发送UDP报文的套接字
	// byte[] buf, UDP报文的数据部分
	// int maxTimes, 最大重传次数
	public static DataInputStream getResPktStream(DatagramSocket ds,
			byte[] buf, int maxTimes) throws IOException {
		// 构造UDP报文对象
		DatagramPacket sendPkt = new DatagramPacket(buf, buf.length);
		int times = 0;// 重传次数
		while (times < maxTimes) {
			ds.send(sendPkt);
			try {
				// 等待确认报文
				DatagramPacket recvPkt = UdpUtils.recvPkt(ds);
				// 返回确认报文的数据输入流对象
				return UdpUtils.getUdpPktStream(recvPkt);
			} catch (SocketTimeoutException e) {
				times++;
				System.out.println("报文超时,进行第" + times + "重传");
				continue;
			}
		}
		// 超过重传次数,返回null对象
		return null;
	}
}
