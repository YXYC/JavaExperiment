package net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IterativeTcpClient {
	public static void main(String[] args) throws Exception {
		//创建Socket对象，连接TCP服务器
		Socket s = new Socket("127.0.0.1", 13);
		System.out.printf("TCP服务器%s连接成功...\n", s.getRemoteSocketAddress());
		//接收服务器的响应数据
		while (true) {
			InputStream is = s.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String recv_msg = br.readLine();
			//如果是服务器关闭连接的消息
			if (recv_msg == null) {
				System.out.printf("服务器%s已经断开连接...\n",s.getRemoteSocketAddress());
				s.close();
				break;
			}else {         
				//输出服务器的当前时间
				System.out.printf("###服务器当前时间为%s\n",recv_msg);
			}
		}
	}
}
