package net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class BaseTcpClient {
    public static void main(String[] args) throws IOException {
    	System.out.println("TCP客户端分析程序");
    	Scanner scanner = new Scanner(System.in);
    	String remoteIP="127.0.0.1";  //服务器IP地址
    	int remotePort=8000;
    	Socket client=new Socket(remoteIP,remotePort);
    	System.out.println("已经成功连接服务器");
    	CLITools.pause();
    	while(true) {
    		System.out.print("输入要发送字符串(输入'q'或者'Q'表示退出连接):");
    		String msg=scanner.nextLine();
    		if(msg.equalsIgnoreCase("q")) {
    			client.close();
    			System.out.println("客户端断开tcp连接，程序退出。。。");
    			break;
    		}
    		//获取输出流，向服务器发送数据
    		OutputStream os=client.getOutputStream();
    		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
    		bw.write(msg+"\r\n");
    		bw.flush();
    		//读取服务器响应
    		InputStream is=client.getInputStream();
    		BufferedReader br=new BufferedReader(new InputStreamReader(is));
    		String response=br.readLine();
    		System.out.println("服务器响应字符串为："+response);
    	}
    }
}
