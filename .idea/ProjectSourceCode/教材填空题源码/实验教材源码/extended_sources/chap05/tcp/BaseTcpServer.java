package net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BaseTcpServer {
    public static void main(String[] args) throws IOException {
    	System.out.println("简单TCP服务器程序\n");
    	Scanner scanner=new Scanner(System.in);
    	int PORT=8000;
    	ServerSocket tcpServer=new ServerSocket(PORT);
    	System.out.println("服务器启动成功，在端口"+PORT+"侦听。。。");
    	CLITools.pause();
    	System.out.println("开始接收客户端连接。。。");
    	Socket s=tcpServer.accept();
    	System.out.printf("接收来自于客户端（%s:%d）的连接，（%s：%d<-->%s,%d）连接建立成功\n",
    			s.getInetAddress().getHostAddress(),s.getPort(),
    			s.getInetAddress().getHostAddress(),s.getPort(),
    			s.getLocalAddress().getHostAddress(),s.getLocalPort());
    	while(true) {
    		//读取客户报文
    		InputStream is=s.getInputStream();
    		BufferedReader br=new BufferedReader(new InputStreamReader(is));
    		//读取数据
    		String msg=br.readLine();
    		System.out.println("接收到字符串："+msg);
    		//判断客户端是否关闭连接
    		if(msg==null) {
    			CLITools.pause();
    			s.close();
    			System.out.println("客户端断开tcp连接，程序退出。。。");
    			break;
    		}
    		//向客户端返回接收到的内容
    		OutputStream os=s.getOutputStream();
    		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
    		bw.write(msg+"\r\n");
    		bw.flush();
    	}
    }
}
