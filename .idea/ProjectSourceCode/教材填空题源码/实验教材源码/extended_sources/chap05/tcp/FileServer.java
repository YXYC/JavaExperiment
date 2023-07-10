package net.tcp;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Font;

public class FileServer {

	String savePath; // �ļ����·��
	boolean bStart = false; // �������Ƿ�����
	int port = 9000; // �������˿ں�
	ServerSocket server; // �������׽���
	String password = "12345"; //Ĭ������
	int numOfUnploadFile = 0; // �Ѿ��ϴ��ļ���
	private JFrame frame;
	private JPasswordField txtPassword;
	private JLabel lblFileCount;
	private JButton btnChoosePath;
	private JButton btnOpenDir;
	private JButton btnStart;
	private JButton btnStop;
	private JTextArea textAreaRec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileServer window = new FileServer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileServer() {
		initialize();
		// Ĭ�ϱ����ڵ�ǰĿ¼
		savePath = System.getProperty("user.dir");
		txtPassword.setText(password);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6587\u4EF6\u670D\u52A1\u5668");
		frame.setBounds(100, 100, 645, 419);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel(
				"\u8BBE\u7F6E\u4E0A\u4F20\u5BC6\u7801:");
		lblNewLabel_1.setBounds(14, 17, 98, 18);
		frame.getContentPane().add(lblNewLabel_1);

		txtPassword = new JPasswordField();
		txtPassword.setEchoChar('*');
		txtPassword.setBounds(115, 14, 115, 24);
		frame.getContentPane().add(txtPassword);

		btnStart = new JButton("\u542F\u52A8\u670D\u52A1\u5668");
		// ����������������ť�¼�
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread t = new Thread(new ServerThread());
				t.start();
				// �޸İ�ť״̬
				btnStop.setEnabled(true);
				btnStart.setEnabled(false);
			}
		});
		btnStart.setBounds(280, 48, 153, 27);
		frame.getContentPane().add(btnStart);

		btnStop = new JButton("\u505C\u6B62\u670D\u52A1\u5668");
		// ����ֹͣ�������¼�
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bStart = false;
					btnStart.setEnabled(true);
					btnStop.setEnabled(false);
					addMsgRec(getTimeStr() + " �����ҷ������Ѿ��ر�...");
					server.close();
				} catch (IOException e) {
					// e.printStackTrace();
				}
			}
		});
		btnStop.setEnabled(false);
		btnStop.setBounds(465, 48, 143, 27);
		frame.getContentPane().add(btnStop);

		btnChoosePath = new JButton(
				"\u9009\u62E9\u6587\u4EF6\u5B58\u653E\u8DEF\u5F84");
		// ����ѡ���ļ����Ŀ¼��ť�¼�
		btnChoosePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ���ļ�ѡ����
				JFileChooser dirChooser = new JFileChooser();
				// ֻ����ѡ��Ŀ¼
				dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (dirChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
					savePath = dirChooser.getSelectedFile().getPath();
				// ��ʾѡ���·������
				addMsgRec(getTimeStr() + " �ϴ����ļ��洢·��: " + savePath);
			}
		});
		btnChoosePath.setBounds(280, 13, 153, 27);
		frame.getContentPane().add(btnChoosePath);

		btnOpenDir = new JButton("\u67E5\u770B\u5DF2\u4E0A\u4F20\u6587\u4EF6");
		// ����鿴���ϴ��ļ�Ŀ¼�¼�
		btnOpenDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����windows��Դ���������
				String command = "cmd /c start explorer " + savePath;
				try {
					// ʹ��Runtimeִ������
					Runtime.getRuntime().exec(command);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnOpenDir.setBounds(463, 13, 145, 27);
		frame.getContentPane().add(btnOpenDir);

		JLabel lblNewLabel_2 = new JLabel("\u6D88\u606F\u8BB0\u5F55");
		lblNewLabel_2.setBounds(14, 62, 72, 23);
		frame.getContentPane().add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 88, 594, 263);
		frame.getContentPane().add(scrollPane);

		textAreaRec = new JTextArea();
		textAreaRec.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(textAreaRec);

		lblFileCount = new JLabel(
				"\u76EE\u524D\u5DF2\u7ECF\u4E0A\u4F200\u4E2A\u6587\u4EF6");
		lblFileCount.setBounds(14, 354, 619, 18);
		frame.getContentPane().add(lblFileCount);
		// ������ʽ
		try {
			String style = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			UIManager.setLookAndFeel(style);
			// ���´�����ʽ
			SwingUtilities.updateComponentTreeUI(this.frame);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ȡ��ǰʱ���ʽ�����ַ�����ʽ
	public String getTimeStr() {
		// ʱ���ʽ��
		SimpleDateFormat fm = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
		return fm.format(new Date());
	}

	// �����Ϣ���ı���¼��,���ҽ�JTextArea���������һ��
	public void addMsgRec(String msg) {
		// �����Ϣ���ı���
		textAreaRec.append(msg + "\n");
		// �Զ����������һ��
		textAreaRec.setCaretPosition(textAreaRec.getText().length());
	}

	// �������߳�
	class ServerThread implements Runnable {
		// ����������
		public void start_server() throws IOException {
			try {
				// ����������
				server = new ServerSocket(port);
				bStart = true;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(FileServer.this.frame,
						"����������ʧ��,����ö˿��Ƿ��Ѿ���ռ��.");
				bStart = false;
				return;
			}
			addMsgRec(getTimeStr() + " �����ҷ����������ɹ�,�ڶ˿�" + port + "����,Ĭ������Ϊ"
					+ password);
			// �޸İ�ť״̬
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
			btnChoosePath.setEnabled(false);
			// ���տͻ�������,�������ͻ��˴����߳�
			while (bStart) {
				Socket s = server.accept();
				// �����ͻ��˴����߳�
				Thread t = new Thread(new ClientHandler(s));
				t.start();
			}
		}

		// �߳�����
		public void run() {
			try {
				start_server();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// ����ÿ���ͻ��˵��߳�
	class ClientHandler implements Runnable {
		Socket s; // ����ͻ��˵��׽���
		String fileName; // �ļ���
		long fileLen; // �ļ�����
		long byteOfRecv; // �Ѿ����յ����ֽ���
		boolean bAuthen = false; // �Ƿ�ͨ����֤

		// ���캯��
		public ClientHandler(Socket s) {
			this.s = s;
		}

		// ��֤�ͻ����Ƿ�Ϸ�,��������֤���
		public void auth_user() throws IOException {
			// �����������롢�����
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			DataInputStream dis = new DataInputStream(s.getInputStream());
			// ��ȡ��������
			int pktType = dis.readInt();
			if (pktType == PktCmd.PKT_AUTH) {
				// ��ȡ�ļ������ļ����Ⱥ�����
				fileName = dis.readUTF();
				fileLen = dis.readLong();
				String pwd = dis.readUTF();
				// ��������Ƿ���ȷ,������֤���
				if (pwd.equals(password)) {
					// ������ȷ��ͨ����֤
					dos.writeInt(PktCmd.PKT_AUTH_RES);
					dos.writeInt(PktCmd.AUTH_SUCCESS);
					bAuthen = true;
					addMsgRec("\n" + getTimeStr() + " �͑���"
							+ s.getRemoteSocketAddress() + "��֤ͨ��.");
				} else {
					// �������,��֤ʧ��
					dos.writeInt(PktCmd.PKT_AUTH_RES);
					dos.writeInt(PktCmd.AUTH_FAIL);
					addMsgRec(getTimeStr() + " �͑���"
							+ s.getRemoteSocketAddress() + "��֤ʧ��.");
				}
				dos.flush();
			}
		}

		// �����ļ�
		public void recv_file() throws IOException {
			// �ļ����俪ʼʱ��
			long startTime = System.currentTimeMillis();
			// �����������뻺����
			BufferedInputStream bis = new BufferedInputStream(
					s.getInputStream());
			// �ļ����������
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(savePath + "\\" + fileName));
			byteOfRecv = 0;
			// ���ջ�����
			byte[] recvBuf = new byte[4 * 1024];
			addMsgRec(getTimeStr() + " ����������" + s.getRemoteSocketAddress()
					+ "���ļ�:\n" + fileName + ",��" + fileLen + "�ֽ�.");
			// д���ļ�����
			while (byteOfRecv < fileLen) {
				// ��ȡ���ݵ����ջ�����
				int len = bis.read(recvBuf);
				// �����ջ���������д���ļ�
				bos.write(recvBuf, 0, len);
				bos.flush();
				byteOfRecv += len;
			}
			bos.close();
			double spendTime = (System.currentTimeMillis() - startTime) / 1000.0;
			long rate = (long) (fileLen / spendTime);
			addMsgRec(getTimeStr() + "\n�ļ�" + fileName + "�������,��" + fileLen
					+ "�ֽ�\n����" + spendTime + "��,�����ٶ�Ϊ" + rate + "�ֽ�/��"
					+ ".(������" + s.getRemoteSocketAddress() + ")");
			numOfUnploadFile++;
			lblFileCount.setText("Ŀǰ�Ѿ��ϴ�" + numOfUnploadFile + "���ļ�");
		}

		// �߳�����
		public void run() {
			try {
				auth_user();
				// �����֤ͨ���������ļ�
				if (bAuthen) 
					recv_file();
			} catch (IOException e) {
			}
		}
	}
}
