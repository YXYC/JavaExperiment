package net.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

//������
public class Utils {
	//ͨ���׽���s�����ַ���
	// s �׽���       msg �ַ�����Ϣ
	public static void sendMsg(Socket s, String msg) {
		try {
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//���׽���s�϶�ȡ�ַ���,���tcp���ӹر�,����null
	//s �׽���
	public static String recvMsg(Socket s) throws IOException {
		String msg = null;
		DataInputStream dis = new DataInputStream(s.getInputStream());
		msg = dis.readUTF();
		return msg;
	}
	// ��ȡ��ʽ���ĵ�ǰʱ���ַ�����ʽ
	public static String getTimeStr() {
		// ʱ���ʽ��
		SimpleDateFormat fm = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
		return fm.format(new Date());
	}
	//�����Ϣ���ı���¼��JTextArea,���ҹ�����ʾ�����һ��
	public static void addMsgRec(StringBuilder sb, String msg){
		//�����Ϣ���ı���
		sb.append(msg+"\n");
	}
	public static void addMsgRec(JTextArea textAreaRecord, String msg) {
		// TODO Auto-generated method stub
		textAreaRecord.append(msg+"\n");
		textAreaRecord.setCaretPosition(textAreaRecord.getText().length());
	}
}
