package net.udp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*******************************************************************
 * UdpChat.java 描述:TODO
 * 
 * @Date:2015-6-1
 * @Version:1.0.0 *
 * *******************************************************************/

public class UdpP2PChatting {

	// udp套接字
	DatagramSocket ds;
	InetAddress remoteAddr;
	int remotePort;
	String nickName; // 个人昵称
	private JFrame frmUdp;
	private JTextField textRemoteIp;
	private JTextArea textAreaRecord;
	private JTextArea textAreaMsg;
	private JButton btnSendMsg;
	private JButton btnConfirm;
	private JLabel lblNewLabel_1;
	private JTextField textRemotePort;
	private JLabel lblNewLabel_2;
	private JTextField textNickName;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UdpP2PChatting window = new UdpP2PChatting();
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
	public UdpP2PChatting() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * 
	 */
	/**
	 * 
	 */
	private void initialize() {
		frmUdp = new JFrame();

		frmUdp.setResizable(false);
		frmUdp.setTitle("UDP\u70B9\u5BF9\u70B9\u804A\u5929");
		frmUdp.setBounds(100, 100, 718, 481);
		frmUdp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUdp.getContentPane().setLayout(null);

		JLabel lblIp = new JLabel("\u8FDC\u7A0BIP:");
		lblIp.setBounds(14, 17, 84, 18);
		frmUdp.getContentPane().add(lblIp);

		textRemoteIp = new JTextField();
		textRemoteIp.setText("10.10.10.20");
		textRemoteIp.setBounds(81, 14, 113, 24);
		frmUdp.getContentPane().add(textRemoteIp);
		textRemoteIp.setColumns(10);

		btnConfirm = new JButton("\u786E\u5B9A");
		// 处理确定按钮事件
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// 创建udp套接字对象
					ds = new DatagramSocket(5000);
					// 获取远程IP地址,端口,昵称
					remoteAddr = InetAddress.getByName(textRemoteIp.getText());
					remotePort = Integer.parseInt(textRemotePort.getText());
					nickName = textNickName.getText();
					// 启动接收线程
					Thread myThread = new Thread(new RecvMsgHandler());
					myThread.start();
					btnSendMsg.setEnabled(true);
					btnConfirm.setEnabled(false);
				} catch (SocketException e) {
					JOptionPane.showMessageDialog(UdpP2PChatting.this.frmUdp, 
							"请检查该端口是否已经被占用.");
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		});

		btnConfirm.setBounds(581, 13, 110, 31);
		frmUdp.getContentPane().add(btnConfirm);

		JLabel lblNewLabel = new JLabel("\u6D88\u606F\u8BB0\u5F55");
		lblNewLabel.setBounds(14, 59, 72, 18);
		frmUdp.getContentPane().add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 90, 680, 242);
		frmUdp.getContentPane().add(scrollPane);

		textAreaRecord = new JTextArea();
		textAreaRecord.setLocation(16, 0);
		textAreaRecord.setEditable(false);
		scrollPane.setViewportView(textAreaRecord);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 376, 546, 51);
		frmUdp.getContentPane().add(scrollPane_1);

		textAreaMsg = new JTextArea();
		textAreaMsg.setLocation(14, 0);
		scrollPane_1.setViewportView(textAreaMsg);

		btnSendMsg = new JButton("\u53D1\u9001");
		btnSendMsg.setEnabled(false);
		// 处理发送消息事件
		btnSendMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 报文格式为"昵称#聊天消息"
				String content = nickName + "#" + textAreaMsg.getText();
				byte[] sendBuf = content.getBytes();
				// 构造报文对象
				DatagramPacket dp = new DatagramPacket(sendBuf, sendBuf.length,
						remoteAddr, remotePort);
				try {
					ds.send(dp);
					addMsgToRec("我", textAreaMsg.getText());
					textAreaMsg.setText("");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		btnSendMsg.setBounds(581, 376, 113, 40);
		frmUdp.getContentPane().add(btnSendMsg);

		lblNewLabel_1 = new JLabel("\u8FDC\u7A0B\u7AEF\u53E3:");
		lblNewLabel_1.setBounds(229, 17, 84, 18);
		frmUdp.getContentPane().add(lblNewLabel_1);

		textRemotePort = new JTextField();
		textRemotePort.setText("5000");
		textRemotePort.setBounds(303, 14, 72, 24);
		frmUdp.getContentPane().add(textRemotePort);
		textRemotePort.setColumns(10);

		lblNewLabel_2 = new JLabel("\u6635\u79F0:");
		lblNewLabel_2.setBounds(411, 17, 72, 18);
		frmUdp.getContentPane().add(lblNewLabel_2);

		textNickName = new JTextField();
		textNickName.setBounds(452, 14, 86, 24);
		frmUdp.getContentPane().add(textNickName);
		textNickName.setColumns(10);

		label = new JLabel("\u53D1\u9001\u6D88\u606F");
		label.setBounds(14, 345, 72, 18);
		frmUdp.getContentPane().add(label);

	}

	// 添加消息到聊天记录框
	public void addMsgToRec(String username, String msg) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = format.format(new Date());
		String title = String.format("来自于%s (%s:%d)# %s\n", username,
				remoteAddr.getHostAddress(), remotePort, dateStr);
		textAreaRecord.append(title);
		textAreaRecord.append(msg + "\n");
	}

	// 接收报文线程类
	class RecvMsgHandler implements Runnable {
		public void run() {
			while (true) {
				byte[] recvBuf = new byte[8192];
				DatagramPacket dp = new DatagramPacket(recvBuf, recvBuf.length);
				try {
					ds.receive(dp);
					// 转换为字符串文本
					String s = new String(dp.getData(), dp.getOffset(),
							dp.getLength());
					// 根据'#'分隔符分割字符串,参数2表示分割为2部分
					String parts[] = s.split("#", 2);
					addMsgToRec(parts[0], parts[1]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

}
