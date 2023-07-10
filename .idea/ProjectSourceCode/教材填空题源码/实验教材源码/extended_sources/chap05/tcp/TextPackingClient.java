package net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextPackingClient {
    public static void main(String[] args) {
    	String packtType="type_msg";
    	String sender="Jack";
    	String receiver="Jhon";
    	String senderIp="210.22.111.222";
    	String receiverIp="10.2.1.200";
    	String msg="你好，晚饭老地方见";
    	//服务器地址信息
    	String servIp="127.0.0.1";
    	int servPort=9000;
    	Socket tcpClient=null;
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	System.out.println("TCP文本传客户端\n\n");
    	//创建套接字，连接服务器
    	try {
    		tcpClient=new Socket("127.0.0.1",servPort);
    		System.out.printf("%s服务器连%s:%d连接成功。。。\n",
    				format.format(new Date()),servIp,servPort);
    	}catch(UnknownHostException e) {
    		e.printStackTrace();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	System.out.println("\n需要发送的数据如下：\n报文类型:"+packtType+"\n发送者用户名:"+sender+"\n接收者姓名："+receiver+"\n发送者主机IP："+senderIp
    			+"\n接收者主机IP:"+receiverIp+"\n消息内容："+msg+"\n");
    	//组装发送的字符串
    	StringBuilder sb=new StringBuilder();
    	sb.append(packtType).append("#").append(sender).append("#").append(receiver).append("#").append(senderIp).append("#")
    	.append(receiverIp).append("#").append(msg).append("\r\n");
    	OutputStream os;
    	try {
    		//获取输出流
    		os=tcpClient.getOutputStream();
    		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
    		bw.write(sb.toString());
    		bw.flush();
    		System.out.printf("%s打包后发送报文内容：%s", format.format(new Date()),sb.toString());
    		//开始接收服务器
    		InputStream is=tcpClient.getInputStream();
    		BufferedReader br=new BufferedReader(new InputStreamReader(is));
    		//读取数据
    		String response=br.readLine();
    		System.out.printf("%s服务器%s:%d响应报文为：%s\n",format.format(new Date()),servIp,servPort,response);
            tcpClient.close();
            System.out.println(format.format(new Date())+"客户端关闭TCP连接。。。");
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
}
