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
    	//����Socket��������TCP������
    	Socket s=new Socket("127.0.0.1",8888);
    	System.out.printf("TCP������%s���ӳɹ�...\n", s.getRemoteSocketAddress());
    	//�������ݲ����շ�������Ӧ
    	while(true) {
    		//��ȡ�������������
    		InputStream is=s.getInputStream();
    		OutputStream os=s.getOutputStream();
    		BufferedReader br=new BufferedReader(new InputStreamReader(is));
    		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
    		System.out.print("\n����Ҫ���͵�����:");
    		Scanner scanner=new Scanner(System.in);
    		String msg=scanner.nextLine();
    		bw.write(msg+"\r\n");
    		bw.flush();
    		System.out.printf("###������Ӧ��Ϊ��%s", br.readLine());
    	}
    }
}
