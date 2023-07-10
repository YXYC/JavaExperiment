/*******************************************************************
 * * Copyright (C) 电子科技大学中山学院计算机学院
 * * All rights reserved.
 * *
 * 文件名:labcore  SerializeTcpClient.java
 * 描述:TODO
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
		System.out.println("TCP对象序列化数据传输客户端\n");
		Socket client = new Socket("127.0.0.1", 8888);
		OutputStream os=client.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(os));
		String s="1992-03-13";
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date birthday=format.parse(s);
		String username="李剑锋";
		String password="lis@szc.cn";
		int age=22;
		float weight=72.5f;
		float height=175.5f;
		String cellphone="13549995050";
		String email="TomLi@gmail.com";
		//创建报文对象
		User user = new User(username,password,age,weight,height,birthday,
				cellphone,email);
		oos.writeObject(user);
		oos.flush();
		System.out.println("发送的user对象内容为:" + user);
		client.close();
		System.out.println("\n客户端关闭...");
	}
}
