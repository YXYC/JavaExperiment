package net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class MultiThreadClient {
    public static void main(String[] args) throws Exception{
    	//创建Socket对象，连接TCP服务器
    	Socket s=new Socket("127.0.0.1",8888);
    	System.out.printf("TCP服务器%s连接成功...\n", s.getRemoteSocketAddress());
    	//发送数据并接收服务器响应
    	while(true) {
    		//获取输入流和输出流
    		InputStream is=s.getInputStream();
    		OutputStream os=s.getOutputStream();
    		BufferedReader br=new BufferedReader(new InputStreamReader(is));
    		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
    		System.out.print("\n输入要发送的数据:");
    		Scanner scanner=new Scanner(System.in);
    		String msg=scanner.nextLine();
    		bw.write(msg+"\r\n");
    		bw.flush();
    		System.out.printf("###服务器应答为：%s", br.readLine());
    	}
    }
}
