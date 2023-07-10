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
    	String msg="��ã����ϵط���";
    	//��������ַ��Ϣ
    	String servIp="127.0.0.1";
    	int servPort=9000;
    	Socket tcpClient=null;
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	System.out.println("TCP�ı����ͻ���\n\n");
    	//�����׽��֣����ӷ�����
    	try {
    		tcpClient=new Socket("127.0.0.1",servPort);
    		System.out.printf("%s��������%s:%d���ӳɹ�������\n",
    				format.format(new Date()),servIp,servPort);
    	}catch(UnknownHostException e) {
    		e.printStackTrace();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	System.out.println("\n��Ҫ���͵��������£�\n��������:"+packtType+"\n�������û���:"+sender+"\n������������"+receiver+"\n����������IP��"+senderIp
    			+"\n����������IP:"+receiverIp+"\n��Ϣ���ݣ�"+msg+"\n");
    	//��װ���͵��ַ���
    	StringBuilder sb=new StringBuilder();
    	sb.append(packtType).append("#").append(sender).append("#").append(receiver).append("#").append(senderIp).append("#")
    	.append(receiverIp).append("#").append(msg).append("\r\n");
    	OutputStream os;
    	try {
    		//��ȡ�����
    		os=tcpClient.getOutputStream();
    		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
    		bw.write(sb.toString());
    		bw.flush();
    		System.out.printf("%s������ͱ������ݣ�%s", format.format(new Date()),sb.toString());
    		//��ʼ���շ�����
    		InputStream is=tcpClient.getInputStream();
    		BufferedReader br=new BufferedReader(new InputStreamReader(is));
    		//��ȡ����
    		String response=br.readLine();
    		System.out.printf("%s������%s:%d��Ӧ����Ϊ��%s\n",format.format(new Date()),servIp,servPort,response);
            tcpClient.close();
            System.out.println(format.format(new Date())+"�ͻ��˹ر�TCP���ӡ�����");
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
}
