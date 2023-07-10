package net.udp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UdpBroadcast {

	DatagramSocket udpClient;  // UDP�׽���
	//�㲥��ַ
	static final String BROADCARD_IP="255.255.255.255";
	//Ŀ�Ķ˿ں�
	int port=8000;
	private JFrame frmUdp;
	private JTextArea textAreaRecvMsg;
	private JTextArea textAreaSendMsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UdpBroadcast window = new UdpBroadcast();
					window.frmUdp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UdpBroadcast() {
		initialize();
		//��ʼ���׽���
		try {
			udpClient=new DatagramSocket(8000);
			udpClient.setBroadcast(true);
		} catch (SocketException e) {
			JOptionPane.showMessageDialog(frmUdp, "�׽��ֳ�ʼ��ʧ�ܣ�����˿��Ƿ�ռ��!");
			e.printStackTrace();
			System.exit(0);
		}
		//�������ձ����߳�
		new Thread(new Receiver()).start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUdp = new JFrame();
		frmUdp.setResizable(false);
		frmUdp.setTitle("UDP\u5E7F\u64AD");
		frmUdp.setBounds(100, 100, 586, 400);
		frmUdp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUdp.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u63A5\u6536\u5230\u7684\u5E7F\u64AD\u6D88\u606F");
		label.setBounds(14, 13, 147, 18);
		frmUdp.getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 32, 554, 182);
		frmUdp.getContentPane().add(scrollPane);
		
		textAreaRecvMsg = new JTextArea();
		textAreaRecvMsg.setEditable(false);
		scrollPane.setViewportView(textAreaRecvMsg);
		
		JLabel label_1 = new JLabel("\u8F93\u5165\u8981\u53D1\u9001\u7684\u5E7F\u64AD\u6D88\u606F");
		label_1.setBounds(14, 227, 157, 18);
		frmUdp.getContentPane().add(label_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 250, 554, 74);
		frmUdp.getContentPane().add(scrollPane_1);
		
		textAreaSendMsg = new JTextArea();
		scrollPane_1.setViewportView(textAreaSendMsg);
		
		JButton btnSend = new JButton("\u53D1\u9001");
		//�����Ͱ�ť�¼�
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//�������͵ı���
				byte[] sendBuf=textAreaSendMsg.getText().getBytes();
				try {
					DatagramPacket dp = new DatagramPacket(sendBuf,
								sendBuf.length,
								InetAddress.getByName(BROADCARD_IP),
								port
								);
					udpClient.send(dp);
					textAreaSendMsg.setText("");
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnSend.setBounds(455, 330, 113, 27);
		frmUdp.getContentPane().add(btnSend);
	}
	
	//�����̣߳�ʹ���ڲ���
	class Receiver implements Runnable{
		public void run(){
			while(true){
				//�������ձ���
				byte[] recvBuf = new byte[8192];
				DatagramPacket dp=new DatagramPacket(recvBuf, recvBuf.length);
				try {
					udpClient.receive(dp);
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
				//�ڼ�¼������������Ϣ
				String msg=new String(dp.getData(), dp.getOffset(),dp.getLength());
				//��ȡ���ĵ�Դ��ַ
				InetAddress remoteAddr=dp.getAddress();
				//��ȡ���ĵ�Դ�˿ڵ�ַ
				int remotePort=dp.getPort();
				//��ʽ��ʱ���ַ���
				String dateStr= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
				String record=String.format("������%s:%d����Ϣ(%s):%s\n", remoteAddr.getHostAddress(),
						remotePort, dateStr, msg);
				textAreaRecvMsg.append(record);
			}
		}
	}
}
