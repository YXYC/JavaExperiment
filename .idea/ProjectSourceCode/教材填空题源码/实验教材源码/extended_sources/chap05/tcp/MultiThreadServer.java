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
import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiThreadServer {
    public static void main(String[] args) throws Exception {
    	//启动服务器，在端口8888监听客户端连接
    	ServerSocket server=new ServerSocket(8888);
    	SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	System.out.printf("%s TCP服务器启动成功,在端口%d侦听。。。\n", 
    			fm.format(new Date()),server.getLocalPort());
    	//接收客户端连接
    	while(true) {
    		Socket client=server.accept();
    		//创建线程处理一个新的连接
    		Thread t=new Thread(new HandlerClient(client));
    		t.start();
    		System.out.printf("%s 客户端 %s 是连接成功，创建新的线程处理，线程ID为：%d\n",
    				fm.format(new Date()),
    				client.getRemoteSocketAddress(),
    				t.getId());
    	}
    }
}

class HandlerClient implements Runnable {
	Socket client;
	public HandlerClient(Socket client) {
		this.client=client;
	}
	public void echo() {
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		while(true) {
			try {
				//读取客户端发送的数据
				InputStream is=client.getInputStream();
				OutputStream os=client.getOutputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(is));
				BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
				String msg=br.readLine();
				System.out.printf("###接收到来自于%s的数据：%s，共%d字节。\n",
						client.getRemoteSocketAddress(),msg,msg.getBytes().length);
				//回送从客户端收到的数据
				bw.write(msg+"\r\n");
				bw.flush();
				System.out.printf("###回送给客户端%s的数据：%s，共%d字节。\n", client.getRemoteSocketAddress(),msg,msg.getBytes().length);
				System.out.printf("%s 客户端 %s 断开连接。。。\n", fm.format(new Date()),client.getRemoteSocketAddress());
			}catch(IOException e) {
				System.out.printf("%s 客户端 %s断开连接...\n", fm.format(new Date()),client.getRemoteSocketAddress());
				break;
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		echo();
	}
}