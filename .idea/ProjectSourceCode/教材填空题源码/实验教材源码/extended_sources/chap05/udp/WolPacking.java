package net.udp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WolPacking {

	DatagramSocket ds;
	private JFrame frmwol;
	private JTextField txtDstMac;
	private JTextField txtPacketNum;
	private JTextArea textAreaRec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WolPacking window = new WolPacking();
					window.frmwol.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WolPacking() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmwol = new JFrame();
		frmwol.setResizable(false);
		frmwol.setTitle("\u8FDC\u7A0B\u4E3B\u673A\u5524\u9192WOL");
		frmwol.setBounds(100, 100, 542, 361);
		frmwol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmwol.getContentPane().setLayout(null);

		JLabel lblmac = new JLabel(
				"\u8FDC\u7A0B\u4E3B\u673AMAC\u5730\u5740\uFF1A");
		lblmac.setBounds(14, 13, 136, 18);
		frmwol.getContentPane().add(lblmac);

		txtDstMac = new JTextField();
		txtDstMac.setText("54-ee-75-31-3d-82");
		txtDstMac.setBounds(139, 10, 178, 24);
		frmwol.getContentPane().add(txtDstMac);
		txtDstMac.setColumns(10);

		JLabel label = new JLabel("\u53D1\u9001\u62A5\u6587\u4E2A\u6570:");
		label.setBounds(14, 62, 112, 18);
		frmwol.getContentPane().add(label);

		txtPacketNum = new JTextField();
		txtPacketNum.setText("20");
		txtPacketNum.setBounds(126, 59, 106, 24);
		frmwol.getContentPane().add(txtPacketNum);
		txtPacketNum.setColumns(10);

		JLabel label_1 = new JLabel("\u62A5\u6587\u8BB0\u5F55");
		label_1.setBounds(14, 104, 72, 18);
		frmwol.getContentPane().add(label_1);

		JButton btnSend = new JButton("\u53D1\u9001");
		//处理发送Magic Packet报文事件
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//清空记录
				textAreaRec.setText("");
				try {
					ds = new DatagramSocket();
					// 启动发送线程
					Thread t = new Thread(new sendWolPacket());
					t.start();
				} catch (SocketException e) {
					e.printStackTrace();
				}
			}
		});
		btnSend.setBounds(323, 56, 94, 30);
		frmwol.getContentPane().add(btnSend);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 124, 508, 185);
		frmwol.getContentPane().add(scrollPane);

		textAreaRec = new JTextArea();
		scrollPane.setViewportView(textAreaRec);

		JLabel lblxxxxxxxxxxxx = new JLabel("(\u683C\u5F0F:xx-xx-xx-xx-xx-xx)");
		lblxxxxxxxxxxxx.setBounds(331, 13, 194, 18);
		frmwol.getContentPane().add(lblxxxxxxxxxxxx);

		JButton btnStop = new JButton("\u5173\u95ED");
		// 处理关闭程序事件
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmwol.dispose();
				System.exit(0);
			}
		});
		btnStop.setBounds(431, 56, 94, 30);
		frmwol.getContentPane().add(btnStop);
	}

	// 根据网卡字符串构造远程唤醒的UDP报文
	// 1个广播的mac地址+16个重复的mac地址
	public byte[] buildWolPacket(byte[] dstMac) {
		byte[] broadMac=macStr2Bytes("FF-FF-FF-FF-FF-FF");
		byte[] wolPacket = new byte[6 + 6 * 16];
		
		for (int i = 0; i < 6; i++) {
			wolPacket[i] = broadMac[i];
		}
		for (int i = 6; i < wolPacket.length; i++) {
			wolPacket[i] = dstMac[i % 6];
		}
		return wolPacket;
	}

	// 将xx-xx-xx-xx-xx-xx字符串转换为字节数组
	//
	public byte[] macStr2Bytes(String dstMacStr) {
		byte[] mac = new byte[6];
		String[] parts = dstMacStr.split("-");
		if (parts.length != 6) {
			JOptionPane.showMessageDialog(frmwol, "MAC地址输入错误", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		for (int i = 0; i < mac.length; i++) {
			// 将字符串转换为十六进制字节
			int n = Integer.parseInt(parts[i], 16);
			// 将大于127的数转换为Byte可以表示的负数
			n=(n < Byte.MAX_VALUE) ? n : (n-256);
			mac[i] = (byte) n;
			System.out.println("mac[]" + mac[i]);
		}
		return mac; //
	}

	// 将字节转换为16进制的字符串
	public String byteToHexStr(byte b) {
		int n=0;
		// 如果b为负数，则转换为大于127的整数
		n=(b > 0)? b:(256+ b);
		String s = Integer.toHexString(n).toUpperCase();
		if (s.length() < 2)
			return "0" + s;
		else
			return s;
	}

	// 发送线程
	class sendWolPacket implements Runnable {
		public void run() {
			try {
				byte[] sendBuf = buildWolPacket(macStr2Bytes(txtDstMac
						.getText()));
				// 目的端口号为随机
				DatagramPacket dp = new DatagramPacket(sendBuf, sendBuf.length,
						InetAddress.getByName("255.255.255.255"), 7000);
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				int times = Integer.valueOf(txtPacketNum.getText());
				String dateStr = format.format(new Date());
				StringBuilder sb = new StringBuilder();
				//输出报文内容
				for (int i = 0; i < sendBuf.length; ++i) {
					sb.append(byteToHexStr(sendBuf[i])).append(" ");
				}
				for (int i = 0; i < times; ++i) {
					ds.send(dp);
					String recMsg = String.format("报文%d,时间:%s,内容:%s\n",
							i,
							dateStr, 
							sb.toString());
					textAreaRec.append(recMsg);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
