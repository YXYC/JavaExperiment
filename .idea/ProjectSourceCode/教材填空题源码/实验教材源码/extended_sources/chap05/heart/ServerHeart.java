package net.heart;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerHeart extends Thread {
	private ServerSocket server = null;
	private static Integer ordinal = 0;
	private static Map<Integer, Socket> map = new HashMap<>();
	Object obj = new Object();

	public ServerHeart() {
		try {
			server = new ServerSocket(9090);
			while (true) {
				Socket aclient = server.accept();
				aclient.setSoTimeout(10000);
				ordinal++;
				map.put(ordinal, aclient);
				if (ordinal == 1)
					this.start();
				synchronized (obj) {
					new Thread(new ServerBilocationHeart(ordinal, aclient)).start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				int clients = map.size();
				for (int i = 1; i <= clients; i++) {
					Socket soc = map.get(i);
					if (soc != null) {
						System.out.println("��" + i + "���ͻ������ţ��ͻ���ip��ַ=" + soc.getInetAddress());
						ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
						out.writeObject("Hello,�㻹���ţ�");// ���Ը����ŵĿͻ��������������˴���
					}
				}
				Thread.sleep(4000);
			} catch (Exception e) {
				e.printStackTrace();
				// break;
			}
		}
	}

	class ServerBilocationHeart implements Runnable {
		Integer no;
		Socket client;

		public ServerBilocationHeart(Integer no, Socket client) {
			this.no = no;
			this.client = client;
		}

		@Override
		public void run() {
			try {
				while (true) {
					ObjectInput in = new ObjectInputStream(client.getInputStream());
					Object heartbeat = in.readObject();// ģ������
					// System.out.println("�յ���" + no + "�ͻ�������������Ϣ:" + heartbeat);
				}
			} catch (Exception e) {
				// e.printStackTrace();
				synchronized (map) {
					map.put(no, null);
				}
				System.out.println("��" + no + "���ͻ�ʧȥ��ϵ��");
				try {
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("��ʼ���ͻ����Ƿ�����...");
		ServerHeart server = new ServerHeart();
	}
}