/*******************************************************************
 * * Copyright (C) ���ӿƼ���ѧ��ɽѧԺ�����ѧԺ
 * * All rights reserved.
 * *
 * �ļ���:labcore  SerializeTcpClient.java
 * ����:TODO
 *
 * @Author:Hevean
 * @Date:2015-5-13
 * @Version:1.0.0
 * *
 * *******************************************************************/
package net.tcp;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SerializableTcpClient {
	public static void main(String[] args) throws Exception{
		System.out.println("TCP�������л����ݴ���ͻ���\n");
		Socket client = new Socket("127.0.0.1", 8888);
		OutputStream os=client.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(os));
		String s="1992-03-13";
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date birthday=format.parse(s);
		String username="���";
		String password="lis@szc.cn";
		int age=22;
		float weight=72.5f;
		float height=175.5f;
		String cellphone="13549995050";
		String email="TomLi@gmail.com";
		//�������Ķ���
		User user = new User(username,password,age,weight,height,birthday,
				cellphone,email);
		oos.writeObject(user);
		oos.flush();
		System.out.println("���͵�user��������Ϊ:" + user);
		client.close();
		System.out.println("\n�ͻ��˹ر�...");
	}
}
