/*******************************************************************
  * * Copyright (C) ���ӿƼ���ѧ��ɽѧԺ�����ѧԺ
 * * All rights reserved.
 * *
 * �ļ���:labcore  SerializeTcpServer.java
 * ����:TODO
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
		System.out.println("TCP�������л����ݴ��������\n");
		int port=8888;
		ServerSocket server=new ServerSocket(port);
		
		System.out.printf("�����������ɹ����ڶ˿�%d����...\n", port);
		Socket socket = server.accept();
		ObjectInputStream ois = new 
				 ObjectInputStream(socket.getInputStream());
		User user = (User)ois.readObject();
		
		//�����������
		System.out.println("���յ�user��������Ϊ:" + user);
		socket.close();
		System.out.println("\n�ͻ��˹ر�����...");
		server.close();
	}
}
