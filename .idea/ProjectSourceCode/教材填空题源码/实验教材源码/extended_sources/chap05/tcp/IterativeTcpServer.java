package net.tcp;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IterativeTcpServer {
	public static void main(String[] args) throws Exception {
		// 启动的服务器，在端口13监听客户端连接
		ServerSocket server = new ServerSocket(13);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.printf("%s TCP服务器启动成功，在端口%d侦听...\n",
				formatter.format(new Date()), server.getLocalPort());
		// 循环为客户端服务
		while (true) {
			// 接收客户端连接
			Socket client = server.accept();
			System.out.printf("%s 客户端%s连接成功...\n",
					formatter.format(new Date()),
					client.getRemoteSocketAddress());
			// 获取当前时间
			String now = formatter.format(new Date());
			OutputStream os = client.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			bw.write(now + "\r\n");
			// 发送当前时间字符串
			bw.flush();
			System.out.printf("###服务器向客户端%s发回数据为:%s\n",
					client.getRemoteSocketAddress(), now);
			bw.close();
			// 断开客户端连接
			client.close();
			System.out.printf("%s 客户端%s连接已经断开...\n",
					formatter.format(new Date()),
					client.getRemoteSocketAddress());
		}
	}
}
