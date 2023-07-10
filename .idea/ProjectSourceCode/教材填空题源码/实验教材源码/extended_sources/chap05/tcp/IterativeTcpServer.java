package net.tcp;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IterativeTcpServer {
	public static void main(String[] args) throws Exception {
		// �����ķ��������ڶ˿�13�����ͻ�������
		ServerSocket server = new ServerSocket(13);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.printf("%s TCP�����������ɹ����ڶ˿�%d����...\n",
				formatter.format(new Date()), server.getLocalPort());
		// ѭ��Ϊ�ͻ��˷���
		while (true) {
			// ���տͻ�������
			Socket client = server.accept();
			System.out.printf("%s �ͻ���%s���ӳɹ�...\n",
					formatter.format(new Date()),
					client.getRemoteSocketAddress());
			// ��ȡ��ǰʱ��
			String now = formatter.format(new Date());
			OutputStream os = client.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			bw.write(now + "\r\n");
			// ���͵�ǰʱ���ַ���
			bw.flush();
			System.out.printf("###��������ͻ���%s��������Ϊ:%s\n",
					client.getRemoteSocketAddress(), now);
			bw.close();
			// �Ͽ��ͻ�������
			client.close();
			System.out.printf("%s �ͻ���%s�����Ѿ��Ͽ�...\n",
					formatter.format(new Date()),
					client.getRemoteSocketAddress());
		}
	}
}
