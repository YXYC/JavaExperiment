/*******************************************************************
  * * Copyright (C) 电子科技大学中山学院计算机学院
 * * All rights reserved.
 * *
 * 文件名:labcore  SerializeTcpServer.java
 * 描述:TODO
 *
 * @Author:Hevean
 * @Date:2015-5-13
 * @Version:1.0.0
 * *
 * *******************************************************************/
package net.tcp;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SerializableTcpServer {
	public static void main(String[] args) throws Exception {
		System.out.println("TCP对象序列化数据传输服务器\n");
		int port=8888;
		ServerSocket server=new ServerSocket(port);
		
		System.out.printf("服务器启动成功，在端口%d侦听...\n", port);
		Socket socket = server.accept();
		ObjectInputStream ois = new 
				 ObjectInputStream(socket.getInputStream());
		User user = (User)ois.readObject();
		
		//输出对象内容
		System.out.println("接收到user对象内容为:" + user);
		socket.close();
		System.out.println("\n客户端关闭连接...");
		server.close();
	}
}
