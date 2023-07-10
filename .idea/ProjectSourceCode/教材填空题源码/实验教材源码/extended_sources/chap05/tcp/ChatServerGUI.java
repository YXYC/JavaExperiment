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
	ServerSocket server; // �������׽���
	String serverIP; // ������IP
	int port; // �˿ں�
	HashMap<String, Socket> userList; // �û��б������û����Ͷ�Ӧ��Socket
	boolean bStart = false; // �������Ƿ�����
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
		// ��Ӧ�����������¼�
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ��ȡ�󶨵�IP��ַ�Ͷ˿ں�
				serverIP = textServerIP.getText();
				port = Integer.parseInt(textPort.getText());
				// ������������̨�߳�
				Thread t = new Thread(new ServerThread());
				t.start();
			}
		});
		btnStart.setBounds(479, 23, 95, 27);
		frmTcp.getContentPane().add(btnStart);

		btnStop = new JButton("\u505C\u6B62");
		btnStop.setEnabled(false);
		// ��Ӧֹͣ��ť�¼�
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
		// ������ʽ
		try {
			String style = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			UIManager.setLookAndFeel(style);
			// ���´�����ʽ
			SwingUtilities.updateComponentTreeUI(this.frmTcp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �رշ�����
	public void stop() {
		bStart = false;
		try {
			//����û��б�
			userList.clear();
			updateUsersTbl();
			server.close();
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
			addMsg(Utils.getTimeStr() + " �����ҷ������Ѿ��ر�...");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	// �����Ϣ���ı���textAreaRecord
	public void addMsg(String msg) {
		Utils.addMsgRec(textAreaRecord, msg);
	}
	//���·������û��б�ı��ؼ�
	public void updateUsersTbl() {
		//��ȡJTable������ģ��
		DefaultTableModel tblModel = (DefaultTableModel) tblUsers
				.getModel();
		//���ԭ������
		while(tblModel.getRowCount()>0){
			tblModel.removeRow(tblModel.getRowCount()-1);
		 }
		int seq = 1;
		//�����������
		for (String name : userList.keySet()) {
			Socket s = userList.get(name);
			// ��ȡԶ��IP��ַ�Ͷ˿ں�
			String remoteIP = s.getInetAddress().getHostAddress();
			String remotePort = String.valueOf(s.getPort());
			String[] newRow = { String.valueOf(seq), name, remoteIP,
					remotePort, Utils.getTimeStr() };
			// ����µ�һ�м�¼
			tblModel.addRow(newRow);
			seq++;
		}
	}
	// �ڲ��࣬��������̨�߳�
	class ServerThread implements Runnable {
		public void start_server() {
			try {
				// ��IP��ַ�Ͷ˿ں�.Ĭ�������нӿ�����
				server = new ServerSocket();
				SocketAddress endpoint = new InetSocketAddress(serverIP, port);
				server.bind(endpoint);
				bStart = true;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(ChatServerGUI.this.frmTcp,
						"TCP����������ʧ��,����ö˿��Ƿ��Ѿ���ռ��.");
				bStart = false;
				return;
			}
			addMsg(Utils.getTimeStr() + " �����ҷ����������ɹ�,�ڶ˿�" + port + "����...");
			// �޸İ�ť״̬
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
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
