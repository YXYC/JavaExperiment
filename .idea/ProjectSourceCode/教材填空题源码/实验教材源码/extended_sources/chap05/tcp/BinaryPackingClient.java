package net.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BinaryPackingClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        final short TYPE_REQUEST=1;
        int pktLen;
        int seq;
        long timeStamp;
        //消息内容
        String msg="你好，测试TCP通信";
        String servIp="127.0.0.1";
        int servPort=8999;
        Socket tcpClient=null;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("TCP二进制数据传输客户端\n");
        //创建套接字
        try {
        	tcpClient=new Socket("127.0.0.1",servPort);
        	System.out.printf(" %s 服务器连 %s:%d 接成功。。。\n",
        			format.format(new Date()),servIp,servPort);
        }catch(UnknownHostException e) {
        	e.printStackTrace();
        	System.exit(0);
        }catch(IOException e) {
        	e.printStackTrace();
        	System.exit(1);
        }
        seq=(int)Math.random()%Integer.MAX_VALUE;
        timeStamp=new Date().getTime();
        pktLen=4+4+8+msg.getBytes().length; //计算报文字节长度
        System.out.println("\n需要发送的数据如下：\n报文类型（short）："+TYPE_REQUEST
        		+"\n数据长度(int):"+pktLen
        		+"\n序号（int）："+seq
        		+"\n本主机时间戳（long）："+timeStamp
        		+"\n消息内容（byte[]）："+msg+"\n");
        OutputStream os;
        try {
        	//获取输出流
        	os=tcpClient.getOutputStream();
        	DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(os));
        	dos.writeShort(TYPE_REQUEST);
        	dos.writeInt(pktLen);
        	dos.writeInt(seq);
        	dos.writeLong(timeStamp);
        	dos.write(msg.getBytes());
        	dos.flush();
        	System.out.printf("%s 数据发送成功，共%d字节。", format.format(new Date()),dos.size());
        	//开始接收服务器
        	InputStream is=tcpClient.getInputStream();
        	DataInputStream dis=new DataInputStream(new BufferedInputStream(is));
        	//读取数据
        	int response=dis.readInt();
        	System.out.printf("\n%s 服务器%s:%d响应报文为：%s\n", format.format(new Date()),servIp,servPort,response);
        	if(response==400) {
        		System.out.println("本主机时间比服务器要快。。。");
        	}else if(response==200) {
        		System.out.println("本主机时间比服务器要慢。。。");
        	}
        	tcpClient.close();
        	System.out.println(format.format(new Date())+"客户端关闭TCP连接...");
        }catch(IOException e) {
        	e.printStackTrace();
        }
	}

}
