package net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IterativeTcpClient {
	public static void main(String[] args) throws Exception {
		//����Socket��������TCP������
		Socket s = new Socket("127.0.0.1", 13);
		System.out.printf("TCP������%s���ӳɹ�...\n", s.getRemoteSocketAddress());
		//���շ���������Ӧ����
		while (true) {
			InputStream is = s.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String recv_msg = br.readLine();
			//����Ƿ������ر����ӵ���Ϣ
			if (recv_msg == null) {
				System.out.printf("������%s�Ѿ��Ͽ�����...\n",s.getRemoteSocketAddress());
				s.close();
				break;
			}else {         
				//����������ĵ�ǰʱ��
				System.out.printf("###��������ǰʱ��Ϊ%s\n",recv_msg);
			}
		}
	}
}
