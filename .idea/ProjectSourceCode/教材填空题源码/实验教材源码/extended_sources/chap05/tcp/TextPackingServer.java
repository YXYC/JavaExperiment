package net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextPackingServer {

	public static void main(String[] args) throws IOException{
		int port=9000;
		ServerSocket server=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//启动服务器
		server =new ServerSocket(port);
		System.out.println("TCP文本传输服务器\n\n");
		System.out.printf("%s服务器启动成功，在端口%d侦听。。。\n",format.format(new Date()),port);
        //接收客户端连接
		while(true) {
			Socket s=null;
			s=server.accept();
			InetAddress clientAddr=s.getInetAddress();
			int clientPort=s.getPort();
			System.out.printf("%s客户端%s:%s连接成功\n", format.format(new Date()),clientAddr.getHostAddress(),clientPort);
			//循环处理客户端请求
			while(true) {
				try {
					//获取输入流
					InputStream is=s.getInputStream();
					BufferedReader br=new BufferedReader(new InputStreamReader(is));
					//读取数据
					String line=br.readLine();
					if(line==null) {
						System.out.printf("%s 客户端 %s:%s 已经关闭连接\n",format.format(new Date()),clientAddr.getHostAddress(),clientPort);
						break;
					}
					System.out.printf("%s 接收到的数据内容为：%s\n", format.format(new Date()),line);
					//分割字符串获取各部分内容
					String[] parts=line.split("#");
					System.out.println("\n 解包后得到数据如下：\n报文类型："+parts[0]
							+"\n发送者用户名："+parts[1]+"\n接收者用户名："+parts[2]
							+"\n发送者主机IP："+parts[3]+"\n接收转者主机IP："+parts[4]+"\n消息内容："+parts[5]+"\n");
					//响应客户端
					OutputStream os=s.getOutputStream();
					BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
					String response="type_ok"+"\r\n";
					bw.write(response);
					bw.flush();
					System.out.printf("%s向客户端%s发回响应：%s\n", format.format(new Date()),s.getInetAddress().getHostAddress(),response);
					
				}catch(IOException e) {
					System.out.printf("%s客户端%s:%s连接断开\n", format.format(new Date()),clientAddr.getHostAddress(),clientPort);
					break;
				}
			}
		}
	}

}
