package net.tcp;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.awt.Font;

public class FileClient {

	File file; // Ҫ�����ļ��Ķ���
	Socket client; // �ͻ����׽���
	int port = 9000; // �������˿ں�
	SimpleDateFormat fm = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
	Timer timer; // ��ʱ��,�������
	long byteOfSend; // �Ѵ����ֽ���
	String defaultPassword="12345"; //Ĭ������
	private JFrame frame;
	private JTextField txtServerIp;
	private JPasswordField txtPassword;
	private JButton btnChooseFile;
	private JButton btnSendFile;
	private JProgressBar progressBar;
	private JTextArea textAreaRec;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileClient window = new FileClient();
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
	public FileClient() {
		initialize();
		txtPassword.setText(defaultPassword);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u6587\u4EF6\u5BA2\u6237\u7AEF");
		frame.setBounds(100, 100, 630, 367);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\u670D\u52A1\u5668IP\u5730\u5740:");
		lblNewLabel.setBounds(14, 18, 99, 18);
		frame.getContentPane().add(lblNewLabel);

		txtServerIp = new JTextField();
		txtServerIp.setText("127.0.0.1");
		txtServerIp.setBounds(114, 15, 86, 24);
		frame.getContentPane().add(txtServerIp);
		txtServerIp.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801:");
		lblNewLabel_2.setBounds(218, 18, 38, 18);
		frame.getContentPane().add(lblNewLabel_2);

		txtPassword = new JPasswordField();
		txtPassword.setEchoChar('*');
		txtPassword.setBounds(257, 15, 80, 24);
		frame.getContentPane().add(txtPassword);

		btnChooseFile = new JButton("\u9009\u62E9\u6587\u4EF6");
		// ����ѡ���ļ��¼�
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// �����ļ�ѡ����
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					// �����Ϣ����Ϣ��¼��
					addMsgRec(getTimeStr() + " ѡ���͵��ļ�Ϊ:\n" + file.getName()
							+ ", ��С:" + file.length() + "�ֽ�");
					addMsgRec("������ť�����ļ�!\n");
					btnSendFile.setEnabled(true);
				}
			}
		});
		btnChooseFile.setBounds(386, 14, 99, 27);
		frame.getContentPane().add(btnChooseFile);

		btnSendFile = new JButton("\u53D1\u9001");
		btnSendFile.setEnabled(false);
		// �������ļ���ť�¼�
		btnSendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// �����׽���
				try {
					client = new Socket(txtServerIp.getText(), port);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(FileClient.this.frame,
							"����������ʧ�ܣ�������������");
					return;
				}
				// �����ļ������߳�
				Thread t = new Thread(new SendFileThread());
				t.start();
				byteOfSend = 0; // ��ʼ�������ֽ���
				// ������ʱ���������
				timer = new Timer(100, new ActionListener() {
					// ��ʱִ�е�����
					public void actionPerformed(ActionEvent arg0) {
						if (file.length() == byteOfSend) {
							progressBar.setValue(100);
							timer.stop(); // ֹͣ��ʱ��
							btnChooseFile.setEnabled(true);
						} else {
							float percent=(float) byteOfSend*100 / file.length();
							progressBar.setValue((int)percent);
						}
					}
				});
				// ��ʾ������
				progressBar.setVisible(true);
				// ������ʱ��
				timer.start();
			}
		});
		btnSendFile.setBounds(512, 14, 99, 27);
		frame.getContentPane().add(btnSendFile);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(14, 295, 597, 27);
		frame.getContentPane().add(progressBar);

		JLabel lblNewLabel_1 = new JLabel("\u6D88\u606F\u8BB0\u5F55");
		lblNewLabel_1.setBounds(14, 49, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 68, 597, 214);
		frame.getContentPane().add(scrollPane);

		textAreaRec = new JTextArea();
		textAreaRec.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(textAreaRec);
		// ������ʽ
		try {
			String style = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			UIManager.setLookAndFeel(style);
			// ���´�����ʽ
			SwingUtilities.updateComponentTreeUI(this.frame);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ���ؽ�����
		progressBar.setVisible(false);
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

	// �ڲ���, ���ڷ����ļ�
	class SendFileThread implements Runnable {
		// �����ļ�
		public void sendFile() throws IOException{
			btnChooseFile.setEnabled(false);
			DataOutputStream dos=new DataOutputStream(client.getOutputStream());
			DataInputStream dis = new DataInputStream(client.getInputStream());
			//�����ļ���Ϣ����֤����
			dos.writeInt(PktCmd.PKT_AUTH);  //��������
			dos.writeUTF(file.getName());
			dos.writeLong(file.length());
			dos.writeUTF(txtPassword.getText());
			//���ʹ���õı���
			dos.flush();
			//��ȡ��������֤���
			int pktType=dis.readInt();
			if(pktType==PktCmd.PKT_AUTH_RES){
				//�����֤ʧ�ܣ�����
				if(dis.readInt()==PktCmd.AUTH_FAIL){
					System.out.println("������֤ʧ��!!");
					JOptionPane.showMessageDialog(FileClient.this.frame,
							"���������֤ʧ�ܣ�");
					return;
				}
				System.out.println("������֤�ɹ�.");
				//��֤�ɹ�
				byte[] sendBuf= new byte[4*1024];
				//�ļ����뻺����
				BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream bos=new BufferedOutputStream(client.getOutputStream());
				//�ļ����Ϳ�ʼʱ��
				long startTime=System.currentTimeMillis();
				//�����ļ�
				while(byteOfSend<file.length()){
					int len=bis.read(sendBuf);
					//������������д������
					bos.write(sendBuf, 0, len);
					bos.flush();
					//�޸ķ��͵����ֽ���
					byteOfSend+=len;  
				}
				//����ʱ��
				double spendTime = (System.currentTimeMillis() - startTime) / 1000.0;
				long rate = (long)(file.length() / spendTime);
				addMsgRec(getTimeStr() + "\n�ļ�" + file.getName() + "�������,��" +  file.length()
						+ "�ֽ�\n����" + spendTime + "��,�����ٶ�Ϊ" + rate + "�ֽ�/��.");
				System.out.println("�ļ��������!");
			}
		}
		public void run() {
			try {
				sendFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
