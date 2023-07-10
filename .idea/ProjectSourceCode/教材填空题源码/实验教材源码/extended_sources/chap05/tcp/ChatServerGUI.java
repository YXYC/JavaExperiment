package net.tcp;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class ChatServerGUI {
	ServerSocket server; // 服务器套接字
	String serverIP; // 服务器IP
	int port; // 端口号
	HashMap<String, Socket> userList; // 用户列表，保存用户名和对应的Socket
	boolean bStart = false; // 服务器是否启动
	private JFrame frmTcp;
	private JTextField textServerIP;
	private JTextField textPort;
	private JTable tblUsers;
	private JButton btnStart;
	private JButton btnStop;
	private JTextArea textAreaRecord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatServerGUI window = new ChatServerGUI();
					window.frmTcp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChatServerGUI() {
		userList = new HashMap<String, Socket>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTcp = new JFrame();
		frmTcp.setResizable(false);
		frmTcp.setTitle("TCP\u804A\u5929\u5BA4\u670D\u52A1\u5668");
		frmTcp.setBounds(100, 100, 700, 509);
		frmTcp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTcp.getContentPane().setLayout(null);

		JLabel lblip = new JLabel("\u670D\u52A1\u5668IP\u5730\u5740:");
		lblip.setBounds(10, 27, 105, 18);
		frmTcp.getContentPane().add(lblip);

		textServerIP = new JTextField();
		textServerIP.setText("0.0.0.0");
		textServerIP.setBounds(114, 24, 141, 24);
		frmTcp.getContentPane().add(textServerIP);
		textServerIP.setColumns(10);
		JLabel label = new JLabel("\u7AEF\u53E3\u53F7:");
		label.setBounds(293, 27, 72, 18);
		frmTcp.getContentPane().add(label);
		textPort = new JTextField();
		textPort.setText("8000");
		textPort.setBounds(356, 24, 86, 24);
		frmTcp.getContentPane().add(textPort);
		textPort.setColumns(10);

		btnStart = new JButton("\u542F\u52A8");
		// 响应启动服务器事件
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 获取绑定的IP地址和端口号
				serverIP = textServerIP.getText();
				port = Integer.parseInt(textPort.getText());
				// 启动服务器后台线程
				Thread t = new Thread(new ServerThread());
				t.start();
			}
		});
		btnStart.setBounds(479, 23, 95, 27);
		frmTcp.getContentPane().add(btnStart);

		btnStop = new JButton("\u505C\u6B62");
		btnStop.setEnabled(false);
		// 响应停止按钮事件
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});
		btnStop.setBounds(588, 23, 95, 27);
		frmTcp.getContentPane().add(btnStop);

		JLabel label_1 = new JLabel("\u6D88\u606F\u8BB0\u5F55");
		label_1.setBounds(10, 58, 72, 18);
		frmTcp.getContentPane().add(label_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 673, 159);
		frmTcp.getContentPane().add(scrollPane);

		textAreaRecord = new JTextArea();
		textAreaRecord.setEditable(false);
		scrollPane.setViewportView(textAreaRecord);

		JLabel lblNewLabel = new JLabel("\u5728\u7EBF\u7528\u6237\u5217\u8868");
		lblNewLabel.setBounds(10, 250, 105, 18);
		frmTcp.getContentPane().add(lblNewLabel);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 273, 673, 191);
		frmTcp.getContentPane().add(scrollPane_1);

		tblUsers = new JTable();
		tblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUsers.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u5E8F\u53F7", "\u7528\u6237\u540D",
						"IP\u5730\u5740", "\u7AEF\u53E3\u53F7",
						"\u767B\u5F55\u65F6\u95F4" }) {
			boolean[] columnEditables = new boolean[] { true, true, true,
					false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblUsers.getColumnModel().getColumn(0).setPreferredWidth(56);
		tblUsers.getColumnModel().getColumn(1).setPreferredWidth(118);
		tblUsers.getColumnModel().getColumn(2).setPreferredWidth(126);
		tblUsers.getColumnModel().getColumn(3).setResizable(false);
		tblUsers.getColumnModel().getColumn(3).setPreferredWidth(61);
		tblUsers.getColumnModel().getColumn(4).setPreferredWidth(122);
		scrollPane_1.setViewportView(tblUsers);
		// 更换样式
		try {
			String style = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			UIManager.setLookAndFeel(style);
			// 更新窗体样式
			SwingUtilities.updateComponentTreeUI(this.frmTcp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 关闭服务器
	public void stop() {
		bStart = false;
		try {
			//清空用户列表
			userList.clear();
			updateUsersTbl();
			server.close();
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
			addMsg(Utils.getTimeStr() + " 聊天室服务器已经关闭...");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	// 添加消息到文本框textAreaRecord
	public void addMsg(String msg) {
		Utils.addMsgRec(textAreaRecord, msg);
	}
	//更新服务器用户列表的表格控件
	public void updateUsersTbl() {
		//获取JTable的数据模型
		DefaultTableModel tblModel = (DefaultTableModel) tblUsers
				.getModel();
		//清空原有数据
		while(tblModel.getRowCount()>0){
			tblModel.removeRow(tblModel.getRowCount()-1);
		 }
		int seq = 1;
		//重新添加数据
		for (String name : userList.keySet()) {
			Socket s = userList.get(name);
			// 获取远程IP地址和端口号
			String remoteIP = s.getInetAddress().getHostAddress();
			String remotePort = String.valueOf(s.getPort());
			String[] newRow = { String.valueOf(seq), name, remoteIP,
					remotePort, Utils.getTimeStr() };
			// 添加新的一行记录
			tblModel.addRow(newRow);
			seq++;
		}
	}
	// 内部类，服务器后台线程
	class ServerThread implements Runnable {
		public void start_server() {
			try {
				// 绑定IP地址和端口号.默认在所有接口侦听
				server = new ServerSocket();
				SocketAddress endpoint = new InetSocketAddress(serverIP, port);
				server.bind(endpoint);
				bStart = true;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(ChatServerGUI.this.frmTcp,
						"TCP服务器启动失败,请检查该端口是否已经被占用.");
				bStart = false;
				return;
			}
			addMsg(Utils.getTimeStr() + " 聊天室服务器启动成功,在端口" + port + "侦听...");
			// 修改按钮状态
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
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
