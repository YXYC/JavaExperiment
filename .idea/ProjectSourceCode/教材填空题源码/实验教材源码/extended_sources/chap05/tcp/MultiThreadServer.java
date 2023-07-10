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
import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiThreadServer {
    public static void main(String[] args) throws Exception {
    	//�������������ڶ˿�8888�����ͻ�������
    	ServerSocket server=new ServerSocket(8888);
    	SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	System.out.printf("%s TCP�����������ɹ�,�ڶ˿�%d����������\n", 
    			fm.format(new Date()),server.getLocalPort());
    	//���տͻ�������
    	while(true) {
    		Socket client=server.accept();
    		//�����̴߳���һ���µ�����
    		Thread t=new Thread(new HandlerClient(client));
    		t.start();
    		System.out.printf("%s �ͻ��� %s �����ӳɹ��������µ��̴߳����߳�IDΪ��%d\n",
    				fm.format(new Date()),
    				client.getRemoteSocketAddress(),
    				t.getId());
    	}
    }
}

class HandlerClient implements Runnable {
	Socket client;
	public HandlerClient(Socket client) {
		this.client=client;
	}
	public void echo() {
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		while(true) {
			try {
				//��ȡ�ͻ��˷��͵�����
				InputStream is=client.getInputStream();
				OutputStream os=client.getOutputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(is));
				BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
				String msg=br.readLine();
				System.out.printf("###���յ�������%s�����ݣ�%s����%d�ֽڡ�\n",
						client.getRemoteSocketAddress(),msg,msg.getBytes().length);
				//���ʹӿͻ����յ�������
				bw.write(msg+"\r\n");
				bw.flush();
				System.out.printf("###���͸��ͻ���%s�����ݣ�%s����%d�ֽڡ�\n", client.getRemoteSocketAddress(),msg,msg.getBytes().length);
				System.out.printf("%s �ͻ��� %s �Ͽ����ӡ�����\n", fm.format(new Date()),client.getRemoteSocketAddress());
			}catch(IOException e) {
				System.out.printf("%s �ͻ��� %s�Ͽ�����...\n", fm.format(new Date()),client.getRemoteSocketAddress());
				break;
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		echo();
	}
}