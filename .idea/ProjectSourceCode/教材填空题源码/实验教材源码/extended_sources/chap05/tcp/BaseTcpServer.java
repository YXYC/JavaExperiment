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
    	System.out.println("��TCP����������\n");
    	Scanner scanner=new Scanner(System.in);
    	int PORT=8000;
    	ServerSocket tcpServer=new ServerSocket(PORT);
    	System.out.println("�����������ɹ����ڶ˿�"+PORT+"����������");
    	CLITools.pause();
    	System.out.println("��ʼ���տͻ������ӡ�����");
    	Socket s=tcpServer.accept();
    	System.out.printf("���������ڿͻ��ˣ�%s:%d�������ӣ���%s��%d<-->%s,%d�����ӽ����ɹ�\n",
    			s.getInetAddress().getHostAddress(),s.getPort(),
    			s.getInetAddress().getHostAddress(),s.getPort(),
    			s.getLocalAddress().getHostAddress(),s.getLocalPort());
    	while(true) {
    		//��ȡ�ͻ�����
    		InputStream is=s.getInputStream();
    		BufferedReader br=new BufferedReader(new InputStreamReader(is));
    		//��ȡ����
    		String msg=br.readLine();
    		System.out.println("���յ��ַ�����"+msg);
    		//�жϿͻ����Ƿ�ر�����
    		if(msg==null) {
    			CLITools.pause();
    			s.close();
    			System.out.println("�ͻ��˶Ͽ�tcp���ӣ������˳�������");
    			break;
    		}
    		//��ͻ��˷��ؽ��յ�������
    		OutputStream os=s.getOutputStream();
    		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
    		bw.write(msg+"\r\n");
    		bw.flush();
    	}
    }
}
