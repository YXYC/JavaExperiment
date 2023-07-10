package net.tcp;

import java.awt.EventQueue;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import javax.swing.*;


public class ChatClient {
	Socket tcpClient;// �׽���
	String serverIP; // ������IP��ַ
	int serverPort; // �������˿ں�
	String username; // �û���
	DefaultListModel usersModel; //JList������ģ��,����JList�ؼ������ݸ���
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
		//��ʼ��JList������ģ��
		usersModel = new DefaultListModel();
		listUsers.setModel(usersModel);
		// ��������û���
		Random rand = new Random();
		textUsername.setText("�û�" + rand.nextInt(100));

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
		//�����½��ť�¼�
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ��ȡ������IP��ַ,�˿ں�,�û���
				serverIP = textServerIP.getText();
				serverPort = Integer.parseInt(textServerPort.getText());
				username = textUsername.getText();
				// �жϵ�ǰ��¼״̬
				if (btnConnect.getText().equals("��¼")) {
					Thread t = new Thread(new ClientThread());
					System.out.println("�����߳�...");
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
					//����������Ϣ
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
			// ���´�����ʽ
			SwingUtilities.updateComponentTreeUI(this.frmtcp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendChatMsg() throws IOException {
		String msg; // ��Ϣ����
		String dstUser = "������"; // Ĭ�����������������
		// �����Ⱥ��
		if (rdbtnBrocast.isSelected()) {
			//����Ⱥ�ĵ���Ϣ����
			msg = "TALKTO_ALL#" + username + "#" + textAreaMsg.getText();
		} else {
			// ��ȡ����˽�ĵ�ѡ���û���
			dstUser = (String) listUsers.getSelectedValue();
			if (dstUser == null) {
				JOptionPane.showMessageDialog(this.frmtcp,
						"��ѡ����Ҫ����˽�ĵ��û�");
				return;
			}
			//����˽�ĵ���Ϣ����
			msg = "TALKTO#" + dstUser + "," + username + "#"
					+ textAreaMsg.getText();
		}
		//�������챨�ĵ�������
		Utils.sendMsg(tcpClient, msg);
		// ��ӵ���Ϣ��¼��
		addMsg(Utils.getTimeStr()+" (�Ҷ�" +dstUser+")˵:\n"+textAreaMsg.getText());
		//�����Ϣ��
		textAreaMsg.setText("");
	}

	// �˳�������
	public void logout() {
		try {
			// �����û��˳�������LOGOUT����
			Utils.sendMsg(tcpClient, "LOGOUT#" + username);
			// ���½���
			usersModel.clear();
			listUsers.setModel(usersModel);
			lblRoomInfo.setText("�Ѿ��˳�������!");
			btnConnect.setText("��¼");
			btnSend.setEnabled(false);
			// �ر��׽���
			tcpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// �����Ϣ���ı���textAreaRecord
	public void addMsg(String msg) {
		Utils.addMsgRec(textAreaRecord, msg);
	}
	// �ڲ��ࡣ�ͻ����̣߳����������������
	class ClientThread implements Runnable {
		boolean bLogin = false; // �Ƿ�ɹ���¼
		// ����TCP������
		public void connet_serv() throws IOException {
			try {
				// �����׽���
				tcpClient = new Socket(serverIP, serverPort);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(ChatClient.this.frmtcp,
						"����������ʧ�ܣ�������������");
				return;
			}
			String loginMsg = "LOGIN#" + username;
			// ���͵�¼����
			Utils.sendMsg(tcpClient, loginMsg);
			// ��ȡ��������Ӧ��Ϣ
			String response = Utils.recvMsg(tcpClient);
			// ��¼ʧ��
			if (response.equals("FAIL")) {
				JOptionPane.showMessageDialog(ChatClient.this.frmtcp,
						"������ͬ�û���,��¼ʧ��!");
				return;
			} else {
				// ��½�ɹ�,���½���
				btnConnect.setText("�˳�");
				btnSend.setEnabled(true);
				textAreaRecord.setText("");
				addMsg(Utils.getTimeStr() + " �����ҷ�������¼�ɹ�.");
				//����JList�б���Ϣ
				String[] self = { username };
				updateJList(listUsers, self, "ADD");
				lblRoomInfo.setText("Ŀǰ�����ҹ�" + listUsers.getModel().getSize()
						+ "��.");
				bLogin = true;
			}
		}
		// ���շ���������
		public void handle_msg() {
			String msg = null; // ������Ϣ
			String rec;      // ��Ϣ��¼
			String srcUser; // ������Ϣ���û�
			while (bLogin == true) {
				try {
					// ���ձ���
					msg = Utils.recvMsg(tcpClient);
				} catch (IOException e) {
					// �������쳣�Ͽ�����
					JOptionPane.showMessageDialog(ChatClient.this.frmtcp,
							"�����������Ѿ��Ͽ�,�˳�������\n");
					return;
				}
				// �������Ϊnull,��ʾ�������ر�����
				if (msg == null) {
					JOptionPane.showMessageDialog(ChatClient.this.frmtcp,
							"�������ر�����");
					return;
				}
				// �ָ��,��ȡ�����ֶ�����
				String[] parts = msg.split("#");
				//��ȡ��������
				String command = parts[0];
				switch (command) {
				case "LOGIN": // ���û���¼
					// ��ȡ�û�������ӵ��û��б��
					String[] loginUser = { parts[1] };
					// ����JList����
					updateJList(listUsers, loginUser, "ADD");
					break;
				case "USERLIST": // �û��б�
					// ��ȡ�û��б�
					String[] users = new String[parts.length - 1];
					// �����û�����������
					System.arraycopy(parts, 1, users, 0, parts.length - 1);
					// ����JList����
					updateJList(listUsers, users, "ADD");
					lblRoomInfo.setText("Ŀǰ��" + listUsers.getModel().getSize()
							+ "��");
					break;
				case "TALKTO": // �û���Ϣ
					// ��ȡ�������û���
					srcUser = parts[1].split(",")[1];
					// ��ӵ���Ϣ��¼��
					addMsg(Utils.getTimeStr() + "(" + srcUser + "����)˵:\n"
							+ parts[2]);
					break;
				case "TALKTO_ALL": // �㲥��Ϣ
					// ��ȡ�������û���
					srcUser = parts[1];
					// ��ӵ���Ϣ��¼��
					addMsg(Utils.getTimeStr() + "(" + srcUser + "��������)˵:\n"
							+ parts[2]);
					break;
				case "LOGOUT": // �û��˳�
					String[] logoutUser = { parts[1] };
					updateJList(listUsers, logoutUser, "DEL");
					lblRoomInfo.setText("Ŀǰ��" + listUsers.getModel().getSize()
							+ "��");
					addMsg(Utils.getTimeStr() + " " + parts[1] + "�Ѿ��˳�������");
					break;
				default:
					break;
				}
			}
		}
		public void updateJList(JList jList, String[] items, String op) {
			switch (op) {
			case "ADD": // ���������
				for (int i = 0; i < items.length; ++i)
					usersModel.addElement(items[i]);
				break;
			case "DEL": // ɾ������
				for (int i = 0; i < items.length; ++i)
					usersModel.removeElement(items[i]);
				break;
			default:
				break;
			}
			jList.setModel(usersModel); // ��������
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
