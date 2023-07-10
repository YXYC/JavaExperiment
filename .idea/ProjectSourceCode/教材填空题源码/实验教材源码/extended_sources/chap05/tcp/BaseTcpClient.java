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
    	System.out.println("TCP�ͻ��˷�������");
    	Scanner scanner = new Scanner(System.in);
    	String remoteIP="127.0.0.1";  //������IP��ַ
    	int remotePort=8000;
    	Socket client=new Socket(remoteIP,remotePort);
    	System.out.println("�Ѿ��ɹ����ӷ�����");
    	CLITools.pause();
    	while(true) {
    		System.out.print("����Ҫ�����ַ���(����'q'����'Q'��ʾ�˳�����):");
    		String msg=scanner.nextLine();
    		if(msg.equalsIgnoreCase("q")) {
    			client.close();
    			System.out.println("�ͻ��˶Ͽ�tcp���ӣ������˳�������");
    			break;
    		}
    		//��ȡ����������������������
    		OutputStream os=client.getOutputStream();
    		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
    		bw.write(msg+"\r\n");
    		bw.flush();
    		//��ȡ��������Ӧ
    		InputStream is=client.getInputStream();
    		BufferedReader br=new BufferedReader(new InputStreamReader(is));
    		String response=br.readLine();
    		System.out.println("��������Ӧ�ַ���Ϊ��"+response);
    	}
    }
}
