package net.tcp;
//ChatServer.java
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class ChatServer {
	ServerSocket server; // 服务器套接字
	String serverIP; // 服务器IP
	int port; // 端口号
	HashMap<String, Socket> userList; // 用户列表，保存用户名和对应的Socket
	boolean bStart = false; // 服务器是否启动
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
	//更新服务器用户列表的表格控件
	public void updateUsersTbl() {
		int seq = 1;
		UsersTbl.clear();
		//重新添加数据
		for (String name : userList.keySet()) {
			Socket s = userList.get(name);
			// 获取远程IP地址和端口号
			String remoteIP = s.getInetAddress().getHostAddress();
			String remotePort = String.valueOf(s.getPort());
			String[] newRow = { String.valueOf(seq), name, remoteIP,
					remotePort, Utils.getTimeStr() };
			// 添加新的一行记录
			System.out.println(name+"\t"+remoteIP+"\t"+remotePort+"\t"+Utils.getTimeStr());
			UsersTbl.add(newRow);
			seq++;
		}
	}
	// 内部类，服务器后台线程
	class ServerThread implements Runnable {
		public void start_server() {
			try {
				// 绑定IP地址和端口号.默认在所有接口侦听
				server = new ServerSocket(port);
				bStart = true;
			} catch (IOException e) {
				System.out.println("TCP服务器启动失败,请检查该端口是否已经被占用.");
				bStart = false;
				return;
			}
			addMsg(Utils.getTimeStr() + " 聊天室服务器启动成功,在端口" + port + "侦听...");
		}

		// 线程主体
		public void run() {
			start_server();
			while (bStart) {
				try {
					// 接收客户端连接
					Socket s = server.accept();
					// 为每个客户端分配一个线程
					Thread t = new Thread(new ClientHandler(s));
					t.start();
				} catch (IOException e) {
				}
			}
		}
	}

	// 内部类，用于和客户端交互
	class ClientHandler implements Runnable {
		Socket s; // 客户端套接字
		String username; // 客户端对应的用户名
		// 构造函数
		public ClientHandler(Socket s) {
			this.s = s;
		}
		// 处理客户端的消息报文
		public void handle_msg() throws IOException {
			while (bStart) {
				// 读取客户端的报文
				String msg = null;
				try {
					msg = Utils.recvMsg(s);
				} catch (IOException e) {
					// 客户端异常退出
					addMsg(Utils.getTimeStr() + " 客户端"
							+ s.getRemoteSocketAddress() + "已经退出聊天室");
					// 删除用户
					userList.remove(username);
					updateUsersTbl();
					// 转发该用户退出聊天室的消息给其他用户
					broadcaseMsg(username, "LOGOUT#" + username);
				}
				// 客户端关闭连接
				if (msg == null) {
					s.close();
					return;
				}
				// 记录客户端发送的消息
				addMsg(Utils.getTimeStr() + " 接收到来自客户端"
						+ s.getRemoteSocketAddress() + "报文,内容为:" + msg);

				// 解析报文各个字段
				String[] parts = msg.split("#");
				switch (parts[0]) {
				case "LOGIN": // 登陆报文
					username = parts[1];
					handlLogin();
					break;
				case "TALKTO": // 1对1聊天报文
					// 获取目的用户对应的套接字
					String dstUser = parts[1].split(",")[0];
					Socket dstSocket = userList.get(dstUser);
					Utils.sendMsg(dstSocket, msg);
					break;
				case "TALKTO_ALL": // 广播聊天报文
					// 广播报文给所有用户
					broadcaseMsg(parts[1], msg);
					break;
				case "LOGOUT": // 退出聊天室
					username = parts[1];
					// 转发消息给其他用户
					broadcaseMsg(username, msg);
					// 更新界面
					Socket socket = userList.get(username);
					// 删除用户
					userList.remove(username);
					updateUsersTbl();
					socket.close();
					break;
				default:
					break;
				}
			}
		}
		// 将来自的某个用户的消息转发给用户列表中其他用户
		public void broadcaseMsg(String srcUser, String msg) throws IOException {
			// 遍历用户列表
			for (String name : userList.keySet()) {
				// 如果不是消息来源的用户，则转发消息
				if (!name.equals(srcUser)) {
					// 获取对应的socket
					Socket s = userList.get(name);
					Utils.sendMsg(s, msg);
				}
			}
		}

		// 处理用户登陆报文
		public void handlLogin() throws IOException {
			// 如果存在相同的用户名
			if (userList.containsKey(username)) {
				// 发回"FAIL"登陆失败报文
				Utils.sendMsg(s, "FAIL");
			} else {
				// 登陆成功
				Utils.sendMsg(s, "SUCCESS");
				// 构造用户列表报文
				StringBuffer sb = new StringBuffer();
				sb.append("USERLIST#");
				for (String s : userList.keySet()) {
					sb.append(s + "#");
				}
				sb.deleteCharAt(sb.length() - 1); // 删除最有一个","
				// 发送用户列表给新用户
				Utils.sendMsg(s, sb.toString());
				// 添加新用户到用户列表
				userList.put(username, s);
				// 发送新用户信息给其他用户
				broadcaseMsg(username, "LOGIN#" + username);
				// 更新表格
				updateUsersTbl();
			}
		}

		// 处理客户端事件
		public void run() {
			try {
				handle_msg();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
