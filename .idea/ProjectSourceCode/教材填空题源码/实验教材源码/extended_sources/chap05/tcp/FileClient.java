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

	File file; // 要发送文件的对象
	Socket client; // 客户端套接字
	int port = 9000; // 服务器端口号
	SimpleDateFormat fm = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
	Timer timer; // 定时器,计算进度
	long byteOfSend; // 已传输字节数
	String defaultPassword="12345"; //默认密码
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
		// 处理选择文件事件
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 创建文件选择器
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					// 添加信息到消息记录框
					addMsgRec(getTimeStr() + " 选择发送的文件为:\n" + file.getName()
							+ ", 大小:" + file.length() + "字节");
					addMsgRec("请点击按钮发送文件!\n");
					btnSendFile.setEnabled(true);
				}
			}
		});
		btnChooseFile.setBounds(386, 14, 99, 27);
		frame.getContentPane().add(btnChooseFile);

		btnSendFile = new JButton("\u53D1\u9001");
		btnSendFile.setEnabled(false);
		// 处理发送文件按钮事件
		btnSendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 创建套接字
				try {
					client = new Socket(txtServerIp.getText(), port);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(FileClient.this.frame,
							"服务器连接失败，请检查网络配置");
					return;
				}
				// 启动文件发送线程
				Thread t = new Thread(new SendFileThread());
				t.start();
				byteOfSend = 0; // 初始化发送字节数
				// 启动定时器计算进度
				timer = new Timer(100, new ActionListener() {
					// 定时执行的任务
					public void actionPerformed(ActionEvent arg0) {
						if (file.length() == byteOfSend) {
							progressBar.setValue(100);
							timer.stop(); // 停止计时器
							btnChooseFile.setEnabled(true);
						} else {
							float percent=(float) byteOfSend*100 / file.length();
							progressBar.setValue((int)percent);
						}
					}
				});
				// 显示进度条
				progressBar.setVisible(true);
				// 启动计时器
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
		// 更换样式
		try {
			String style = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			UIManager.setLookAndFeel(style);
			// 更新窗体样式
			SwingUtilities.updateComponentTreeUI(this.frame);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 隐藏进度条
		progressBar.setVisible(false);
	}
	// 获取当前时间格式化的字符串形式
	public String getTimeStr() {
		// 时间格式化
		SimpleDateFormat fm = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
		return fm.format(new Date());
	}
	// 添加消息到文本记录框,并且将JTextArea滚动到最后一行
	public void addMsgRec(String msg) {
		// 添加消息到文本框
		textAreaRec.append(msg + "\n");
		// 自动滚动到最后一行
		textAreaRec.setCaretPosition(textAreaRec.getText().length());
	}

	// 内部类, 用于发送文件
	class SendFileThread implements Runnable {
		// 发送文件
		public void sendFile() throws IOException{
			btnChooseFile.setEnabled(false);
			DataOutputStream dos=new DataOutputStream(client.getOutputStream());
			DataInputStream dis = new DataInputStream(client.getInputStream());
			//发送文件信息和验证密码
			dos.writeInt(PktCmd.PKT_AUTH);  //报文类型
			dos.writeUTF(file.getName());
			dos.writeLong(file.length());
			dos.writeUTF(txtPassword.getText());
			//发送打包好的报文
			dos.flush();
			//读取服务器验证结果
			int pktType=dis.readInt();
			if(pktType==PktCmd.PKT_AUTH_RES){
				//如果认证失败，返回
				if(dis.readInt()==PktCmd.AUTH_FAIL){
					System.out.println("密码验证失败!!");
					JOptionPane.showMessageDialog(FileClient.this.frame,
							"密码错误，验证失败！");
					return;
				}
				System.out.println("密码验证成功.");
				//认证成功
				byte[] sendBuf= new byte[4*1024];
				//文件输入缓冲流
				BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream bos=new BufferedOutputStream(client.getOutputStream());
				//文件发送开始时间
				long startTime=System.currentTimeMillis();
				//发送文件
				while(byteOfSend<file.length()){
					int len=bis.read(sendBuf);
					//将缓冲区数据写入网络
					bos.write(sendBuf, 0, len);
					bos.flush();
					//修改发送的总字节数
					byteOfSend+=len;  
				}
				//花费时间
				double spendTime = (System.currentTimeMillis() - startTime) / 1000.0;
				long rate = (long)(file.length() / spendTime);
				addMsgRec(getTimeStr() + "\n文件" + file.getName() + "发送完成,共" +  file.length()
						+ "字节\n花费" + spendTime + "秒,传输速度为" + rate + "字节/秒.");
				System.out.println("文件发送完成!");
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
