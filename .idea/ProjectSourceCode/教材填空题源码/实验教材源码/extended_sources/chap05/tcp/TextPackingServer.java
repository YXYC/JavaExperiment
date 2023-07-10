package net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextPackingServer {

	public static void main(String[] args) throws IOException{
		int port=9000;
		ServerSocket server=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//����������
		server =new ServerSocket(port);
		System.out.println("TCP�ı����������\n\n");
		System.out.printf("%s�����������ɹ����ڶ˿�%d����������\n",format.format(new Date()),port);
        //���տͻ�������
		while(true) {
			Socket s=null;
			s=server.accept();
			InetAddress clientAddr=s.getInetAddress();
			int clientPort=s.getPort();
			System.out.printf("%s�ͻ���%s:%s���ӳɹ�\n", format.format(new Date()),clientAddr.getHostAddress(),clientPort);
			//ѭ������ͻ�������
			while(true) {
				try {
					//��ȡ������
					InputStream is=s.getInputStream();
					BufferedReader br=new BufferedReader(new InputStreamReader(is));
					//��ȡ����
					String line=br.readLine();
					if(line==null) {
						System.out.printf("%s �ͻ��� %s:%s �Ѿ��ر�����\n",format.format(new Date()),clientAddr.getHostAddress(),clientPort);
						break;
					}
					System.out.printf("%s ���յ�����������Ϊ��%s\n", format.format(new Date()),line);
					//�ָ��ַ�����ȡ����������
					String[] parts=line.split("#");
					System.out.println("\n �����õ��������£�\n�������ͣ�"+parts[0]
							+"\n�������û�����"+parts[1]+"\n�������û�����"+parts[2]
							+"\n����������IP��"+parts[3]+"\n����ת������IP��"+parts[4]+"\n��Ϣ���ݣ�"+parts[5]+"\n");
					//��Ӧ�ͻ���
					OutputStream os=s.getOutputStream();
					BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
					String response="type_ok"+"\r\n";
					bw.write(response);
					bw.flush();
					System.out.printf("%s��ͻ���%s������Ӧ��%s\n", format.format(new Date()),s.getInetAddress().getHostAddress(),response);
					
				}catch(IOException e) {
					System.out.printf("%s�ͻ���%s:%s���ӶϿ�\n", format.format(new Date()),clientAddr.getHostAddress(),clientPort);
					break;
				}
			}
		}
	}

}
