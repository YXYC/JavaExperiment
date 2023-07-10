package net.tcp;
//ChatServer.java
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class ChatServer {
	ServerSocket server; // �������׽���
	String serverIP; // ������IP
	int port; // �˿ں�
	HashMap<String, Socket> userList; // �û��б������û����Ͷ�Ӧ��Socket
	boolean bStart = false; // �������Ƿ�����
    StringBuilder sb=null;
    ArrayList<String[]> UsersTbl=null;
	public static void main(String[] args) {
 	    ChatServer chatserver = new ChatServer();
	}
	public ChatServer() {
		userList = new HashMap<String, Socket>();
		sb=new StringBuilder(1000);
		UsersTbl=new ArrayList<String[]>();
		port=8000;
		Thread t = new Thread(new ServerThread());
		t.start();
	}
	public void addMsg(String msg) {
		Utils.addMsgRec(sb, msg);
		System.out.println(msg);
	}
	//���·������û��б�ı��ؼ�
	public void updateUsersTbl() {
		int seq = 1;
		UsersTbl.clear();
		//�����������
		for (String name : userList.keySet()) {
			Socket s = userList.get(name);
			// ��ȡԶ��IP��ַ�Ͷ˿ں�
			String remoteIP = s.getInetAddress().getHostAddress();
			String remotePort = String.valueOf(s.getPort());
			String[] newRow = { String.valueOf(seq), name, remoteIP,
					remotePort, Utils.getTimeStr() };
			// ����µ�һ�м�¼
			System.out.println(name+"\t"+remoteIP+"\t"+remotePort+"\t"+Utils.getTimeStr());
			UsersTbl.add(newRow);
			seq++;
		}
	}
	// �ڲ��࣬��������̨�߳�
	class ServerThread implements Runnable {
		public void start_server() {
			try {
				// ��IP��ַ�Ͷ˿ں�.Ĭ�������нӿ�����
				server = new ServerSocket(port);
				bStart = true;
			} catch (IOException e) {
				System.out.println("TCP����������ʧ��,����ö˿��Ƿ��Ѿ���ռ��.");
				bStart = false;
				return;
			}
			addMsg(Utils.getTimeStr() + " �����ҷ����������ɹ�,�ڶ˿�" + port + "����...");
		}

		// �߳�����
		public void run() {
			start_server();
			while (bStart) {
				try {
					// ���տͻ�������
					Socket s = server.accept();
					// Ϊÿ���ͻ��˷���һ���߳�
					Thread t = new Thread(new ClientHandler(s));
					t.start();
				} catch (IOException e) {
				}
			}
		}
	}

	// �ڲ��࣬���ںͿͻ��˽���
	class ClientHandler implements Runnable {
		Socket s; // �ͻ����׽���
		String username; // �ͻ��˶�Ӧ���û���
		// ���캯��
		public ClientHandler(Socket s) {
			this.s = s;
		}
		// ����ͻ��˵���Ϣ����
		public void handle_msg() throws IOException {
			while (bStart) {
				// ��ȡ�ͻ��˵ı���
				String msg = null;
				try {
					msg = Utils.recvMsg(s);
				} catch (IOException e) {
					// �ͻ����쳣�˳�
					addMsg(Utils.getTimeStr() + " �ͻ���"
							+ s.getRemoteSocketAddress() + "�Ѿ��˳�������");
					// ɾ���û�
					userList.remove(username);
					updateUsersTbl();
					// ת�����û��˳������ҵ���Ϣ�������û�
					broadcaseMsg(username, "LOGOUT#" + username);
				}
				// �ͻ��˹ر�����
				if (msg == null) {
					s.close();
					return;
				}
				// ��¼�ͻ��˷��͵���Ϣ
				addMsg(Utils.getTimeStr() + " ���յ����Կͻ���"
						+ s.getRemoteSocketAddress() + "����,����Ϊ:" + msg);

				// �������ĸ����ֶ�
				String[] parts = msg.split("#");
				switch (parts[0]) {
				case "LOGIN": // ��½����
					username = parts[1];
					handlLogin();
					break;
				case "TALKTO": // 1��1���챨��
					// ��ȡĿ���û���Ӧ���׽���
					String dstUser = parts[1].split(",")[0];
					Socket dstSocket = userList.get(dstUser);
					Utils.sendMsg(dstSocket, msg);
					break;
				case "TALKTO_ALL": // �㲥���챨��
					// �㲥���ĸ������û�
					broadcaseMsg(parts[1], msg);
					break;
				case "LOGOUT": // �˳�������
					username = parts[1];
					// ת����Ϣ�������û�
					broadcaseMsg(username, msg);
					// ���½���
					Socket socket = userList.get(username);
					// ɾ���û�
					userList.remove(username);
					updateUsersTbl();
					socket.close();
					break;
				default:
					break;
				}
			}
		}
		// �����Ե�ĳ���û�����Ϣת�����û��б��������û�
		public void broadcaseMsg(String srcUser, String msg) throws IOException {
			// �����û��б�
			for (String name : userList.keySet()) {
				// ���������Ϣ��Դ���û�����ת����Ϣ
				if (!name.equals(srcUser)) {
					// ��ȡ��Ӧ��socket
					Socket s = userList.get(name);
					Utils.sendMsg(s, msg);
				}
			}
		}

		// �����û���½����
		public void handlLogin() throws IOException {
			// ���������ͬ���û���
			if (userList.containsKey(username)) {
				// ����"FAIL"��½ʧ�ܱ���
				Utils.sendMsg(s, "FAIL");
			} else {
				// ��½�ɹ�
				Utils.sendMsg(s, "SUCCESS");
				// �����û��б���
				StringBuffer sb = new StringBuffer();
				sb.append("USERLIST#");
				for (String s : userList.keySet()) {
					sb.append(s + "#");
				}
				sb.deleteCharAt(sb.length() - 1); // ɾ������һ��","
				// �����û��б�����û�
				Utils.sendMsg(s, sb.toString());
				// ������û����û��б�
				userList.put(username, s);
				// �������û���Ϣ�������û�
				broadcaseMsg(username, "LOGIN#" + username);
				// ���±��
				updateUsersTbl();
			}
		}

		// ����ͻ����¼�
		public void run() {
			try {
				handle_msg();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
