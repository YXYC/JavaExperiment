package net.tcp;

import java.awt.EventQueue;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import javax.swing.*;


public class ChatClient {
	Socket tcpClient;// 套接字
	String serverIP; // 服务器IP地址
	int serverPort; // 服务器端口号
	String username; // 用户名
	DefaultListModel usersModel; //JList的数据模型,用于JList控件的数据更新
	private JFrame frmtcp;
	private JTextField textServerIP;
	private JTextField textServerPort;
	private JTextArea textAreaRecord;
	private JButton btnSend;
	private JButton btnConnect;
	private JTextField textUsername;
	private JList listUsers;
	private JLabel lblRoomInfo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnBrocast;
	private JRadioButton rdbtnPrivateChat;
	private JTextArea textAreaMsg;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClient window = new ChatClient();
					window.frmtcp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ChatClient() {
		initialize();
		//初始化JList的数据模型
		usersModel = new DefaultListModel();
		listUsers.setModel(usersModel);
		// 随机生成用户名
		Random rand = new Random();
		textUsername.setText("用户" + rand.nextInt(100));

	}
	private void initialize() {
		frmtcp = new JFrame();
		frmtcp.setResizable(false);
		frmtcp.setTitle("TCP\u804A\u5929\u5BA4\u5BA2\u6237\u7AEF");
		frmtcp.setBounds(100, 100, 715, 476);
		frmtcp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmtcp.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("\u670D\u52A1\u5668IP\u5730\u5740:");
		lblNewLabel.setBounds(14, 24, 121, 18);
		frmtcp.getContentPane().add(lblNewLabel);
		textServerIP = new JTextField();
		textServerIP.setText("127.0.0.1");
		textServerIP.setBounds(121, 21, 121, 24);
		frmtcp.getContentPane().add(textServerIP);
		textServerIP.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("\u7AEF\u53E3\u53F7:");
		lblNewLabel_1.setBounds(256, 24, 58, 18);
		frmtcp.getContentPane().add(lblNewLabel_1);
		textServerPort = new JTextField();
		textServerPort.setText("8000");
		textServerPort.setBounds(313, 21, 48, 24);
		frmtcp.getContentPane().add(textServerPort);
		textServerPort.setColumns(10);
		btnConnect = new JButton("\u767B\u5F55");
		//处理登陆按钮事件
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 获取服务器IP地址,端口号,用户名
				serverIP = textServerIP.getText();
				serverPort = Integer.parseInt(textServerPort.getText());
				username = textUsername.getText();
				// 判断当前登录状态
				if (btnConnect.getText().equals("登录")) {
					Thread t = new Thread(new ClientThread());
					System.out.println("启动线程...");
					t.start();
				} else {
					logout();
				}
			}
		});
		btnConnect.setBounds(596, 20, 95, 27);
		frmtcp.getContentPane().add(btnConnect);
		JLabel lblNewLabel_2 = new JLabel("\u6D88\u606F:");
		lblNewLabel_2.setBounds(14, 313, 72, 18);
		frmtcp.getContentPane().add(lblNewLabel_2);
		btnSend = new JButton("\u53D1\u9001");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//发送聊天消息
					sendChatMsg();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnSend.setEnabled(false);
		btnSend.setBounds(444, 409, 88, 27);
		frmtcp.getContentPane().add(btnSend);
		JLabel label = new JLabel("\u6D88\u606F\u8BB0\u5F55");
		label.setBounds(14, 55, 72, 18);
		frmtcp.getContentPane().add(label);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 72, 518, 228);
		frmtcp.getContentPane().add(scrollPane);
		textAreaRecord = new JTextArea();
		textAreaRecord.setEditable(false);
		scrollPane.setViewportView(textAreaRecord);
		JLabel label_1 = new JLabel("\u7528\u6237\u540D:");
		label_1.setBounds(375, 24, 58, 18);
		frmtcp.getContentPane().add(label_1);
		textUsername = new JTextField();
		textUsername.setBounds(433, 21, 95, 24);
		frmtcp.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 332, 518, 73);
		frmtcp.getContentPane().add(scrollPane_1);
		textAreaMsg = new JTextArea();
		scrollPane_1.setViewportView(textAreaMsg);
		JLabel label_2 = new JLabel("\u5728\u7EBF\u7528\u6237");
		label_2.setBounds(543, 55, 72, 18);
		frmtcp.getContentPane().add(label_2);
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(546, 72, 149, 334);
		frmtcp.getContentPane().add(scrollPane_2);
		listUsers = new JList();
		listUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(listUsers);
		lblRoomInfo = new JLabel("\u5171\u4EBA");
		lblRoomInfo.setBounds(546, 413, 145, 18);
		frmtcp.getContentPane().add(lblRoomInfo);
		rdbtnBrocast = new JRadioButton("\u53D1\u9001\u7ED9\u6240\u6709\u4EBA");
		buttonGroup.add(rdbtnBrocast);
		rdbtnBrocast.setBounds(106, 309, 132, 22);
		frmtcp.getContentPane().add(rdbtnBrocast);
		rdbtnPrivateChat = new JRadioButton("\u79C1\u804A");
	    buttonGroup.add(rdbtnPrivateChat);
		rdbtnPrivateChat.setSelected(true);
		rdbtnPrivateChat.setBounds(256, 309, 157, 22);
		frmtcp.getContentPane().add(rdbtnPrivateChat);
		try {
			String style = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			UIManager.setLookAndFeel(style);
			// 更新窗体样式
			SwingUtilities.updateComponentTreeUI(this.frmtcp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendChatMsg() throws IOException {
		String msg; // 消息报文
		String dstUser = "所有人"; // 默认聊天对象是所有人
		// 如果是群聊
		if (rdbtnBrocast.isSelected()) {
			//构造群聊的消息报文
			msg = "TALKTO_ALL#" + username + "#" + textAreaMsg.getText();
		} else {
			// 获取进行私聊的选定用户名
			dstUser = (String) listUsers.getSelectedValue();
			if (dstUser == null) {
				JOptionPane.showMessageDialog(this.frmtcp,
						"请选择需要进行私聊的用户");
				return;
			}
			//构造私聊的消息报文
			msg = "TALKTO#" + dstUser + "," + username + "#"
					+ textAreaMsg.getText();
		}
		//发送聊天报文到服务器
		Utils.sendMsg(tcpClient, msg);
		// 添加到消息记录框
		addMsg(Utils.getTimeStr()+" (我对" +dstUser+")说:\n"+textAreaMsg.getText());
		//清空消息框
		textAreaMsg.setText("");
	}

	// 退出聊天室
	public void logout() {
		try {
			// 发送用户退出聊天室LOGOUT报文
			Utils.sendMsg(tcpClient, "LOGOUT#" + username);
			// 更新界面
			usersModel.clear();
			listUsers.setModel(usersModel);
			lblRoomInfo.setText("已经退出聊天室!");
			btnConnect.setText("登录");
			btnSend.setEnabled(false);
			// 关闭套接字
			tcpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 添加消息到文本框textAreaRecord
	public void addMsg(String msg) {
		Utils.addMsgRec(textAreaRecord, msg);
	}
	// 内部类。客户端线程，负责与服务器交互
	class ClientThread implements Runnable {
		boolean bLogin = false; // 是否成功登录
		// 连接TCP服务器
		public void connet_serv() throws IOException {
			try {
				// 创建套接字
				tcpClient = new Socket(serverIP, serverPort);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(ChatClient.this.frmtcp,
						"服务器连接失败，请检查网络配置");
				return;
			}
			String loginMsg = "LOGIN#" + username;
			// 发送登录报文
			Utils.sendMsg(tcpClient, loginMsg);
			// 读取服务器响应消息
			String response = Utils.recvMsg(tcpClient);
			// 登录失败
			if (response.equals("FAIL")) {
				JOptionPane.showMessageDialog(ChatClient.this.frmtcp,
						"存在相同用户名,登录失败!");
				return;
			} else {
				// 登陆成功,更新界面
				btnConnect.setText("退出");
				btnSend.setEnabled(true);
				textAreaRecord.setText("");
				addMsg(Utils.getTimeStr() + " 聊天室服务器登录成功.");
				//更新JList列表信息
				String[] self = { username };
				updateJList(listUsers, self, "ADD");
				lblRoomInfo.setText("目前聊天室共" + listUsers.getModel().getSize()
						+ "人.");
				bLogin = true;
			}
		}
		// 接收服务器报文
		public void handle_msg() {
			String msg = null; // 报文消息
			String rec;      // 消息记录
			String srcUser; // 发送消息的用户
			while (bLogin == true) {
				try {
					// 接收报文
					msg = Utils.recvMsg(tcpClient);
				} catch (IOException e) {
					// 服务器异常断开连接
					JOptionPane.showMessageDialog(ChatClient.this.frmtcp,
							"服务器连接已经断开,退出聊天室\n");
					return;
				}
				// 如果报文为null,表示服务器关闭连接
				if (msg == null) {
					JOptionPane.showMessageDialog(ChatClient.this.frmtcp,
							"服务器关闭连接");
					return;
				}
				// 分割报文,获取各个字段内容
				String[] parts = msg.split("#");
				//获取报文类型
				String command = parts[0];
				switch (command) {
				case "LOGIN": // 新用户登录
					// 获取用户名，添加到用户列表框
					String[] loginUser = { parts[1] };
					// 更新JList数据
					updateJList(listUsers, loginUser, "ADD");
					break;
				case "USERLIST": // 用户列表
					// 获取用户列表
					String[] users = new String[parts.length - 1];
					// 复制用户名到新数组
					System.arraycopy(parts, 1, users, 0, parts.length - 1);
					// 更新JList数据
					updateJList(listUsers, users, "ADD");
					lblRoomInfo.setText("目前共" + listUsers.getModel().getSize()
							+ "人");
					break;
				case "TALKTO": // 用户消息
					// 获取发送者用户名
					srcUser = parts[1].split(",")[1];
					// 添加到消息记录框
					addMsg(Utils.getTimeStr() + "(" + srcUser + "对我)说:\n"
							+ parts[2]);
					break;
				case "TALKTO_ALL": // 广播消息
					// 获取发送者用户名
					srcUser = parts[1];
					// 添加到消息记录框
					addMsg(Utils.getTimeStr() + "(" + srcUser + "对所有人)说:\n"
							+ parts[2]);
					break;
				case "LOGOUT": // 用户退出
					String[] logoutUser = { parts[1] };
					updateJList(listUsers, logoutUser, "DEL");
					lblRoomInfo.setText("目前共" + listUsers.getModel().getSize()
							+ "人");
					addMsg(Utils.getTimeStr() + " " + parts[1] + "已经退出聊天室");
					break;
				default:
					break;
				}
			}
		}
		public void updateJList(JList jList, String[] items, String op) {
			switch (op) {
			case "ADD": // 添加新数据
				for (int i = 0; i < items.length; ++i)
					usersModel.addElement(items[i]);
				break;
			case "DEL": // 删除数据
				for (int i = 0; i < items.length; ++i)
					usersModel.removeElement(items[i]);
				break;
			default:
				break;
			}
			jList.setModel(usersModel); // 更新数据
		}

		public void run() {
			try {
				connet_serv();
				handle_msg();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
