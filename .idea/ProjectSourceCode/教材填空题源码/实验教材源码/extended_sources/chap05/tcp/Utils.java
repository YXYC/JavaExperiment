package net.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

//公共类
public class Utils {
	//通过套接字s发送字符串
	// s 套接字       msg 字符串消息
	public static void sendMsg(Socket s, String msg) {
		try {
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//在套接字s上读取字符串,如果tcp连接关闭,返回null
	//s 套接字
	public static String recvMsg(Socket s) throws IOException {
		String msg = null;
		DataInputStream dis = new DataInputStream(s.getInputStream());
		msg = dis.readUTF();
		return msg;
	}
	// 获取格式化的当前时间字符串形式
	public static String getTimeStr() {
		// 时间格式化
		SimpleDateFormat fm = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
		return fm.format(new Date());
	}
	//添加消息到文本记录框JTextArea,并且滚动显示到最后一行
	public static void addMsgRec(StringBuilder sb, String msg){
		//添加消息到文本框
		sb.append(msg+"\n");
	}
	public static void addMsgRec(JTextArea textAreaRecord, String msg) {
		// TODO Auto-generated method stub
		textAreaRecord.append(msg+"\n");
		textAreaRecord.setCaretPosition(textAreaRecord.getText().length());
	}
}
