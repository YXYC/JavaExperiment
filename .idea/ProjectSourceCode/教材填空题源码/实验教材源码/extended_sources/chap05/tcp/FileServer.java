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

	String savePath; // 文件存放路径
	boolean bStart = false; // 服务器是否启动
	int port = 9000; // 服务器端口号
	ServerSocket server; // 服务器套接字
	String password = "12345"; //默认密码
	int numOfUnploadFile = 0; // 已经上传文件数
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
		// 默认保存在当前目录
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
		// 处理启动服务器按钮事件
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread t = new Thread(new ServerThread());
				t.start();
				// 修改按钮状态
				btnStop.setEnabled(true);
				btnStart.setEnabled(false);
			}
		});
		btnStart.setBounds(280, 48, 153, 27);
		frame.getContentPane().add(btnStart);

		btnStop = new JButton("\u505C\u6B62\u670D\u52A1\u5668");
		// 处理停止服务器事件
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bStart = false;
					btnStart.setEnabled(true);
					btnStop.setEnabled(false);
					addMsgRec(getTimeStr() + " 聊天室服务器已经关闭...");
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
		// 处理选择文件存放目录按钮事件
		btnChoosePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 打开文件选择器
				JFileChooser dirChooser = new JFileChooser();
				// 只允许选择目录
				dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (dirChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
					savePath = dirChooser.getSelectedFile().getPath();
				// 显示选择的路径名称
				addMsgRec(getTimeStr() + " 上传的文件存储路径: " + savePath);
			}
		});
		btnChoosePath.setBounds(280, 13, 153, 27);
		frame.getContentPane().add(btnChoosePath);

		btnOpenDir = new JButton("\u67E5\u770B\u5DF2\u4E0A\u4F20\u6587\u4EF6");
		// 处理查看已上传文件目录事件
		btnOpenDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 运行windows资源管理的命令
				String command = "cmd /c start explorer " + savePath;
				try {
					// 使用Runtime执行命令
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
		// 更换样式
		try {
			String style = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			UIManager.setLookAndFeel(style);
			// 更新窗体样式
			SwingUtilities.updateComponentTreeUI(this.frame);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	// 服务器线程
	class ServerThread implements Runnable {
		// 启动服务器
		public void start_server() throws IOException {
			try {
				// 启动服务器
				server = new ServerSocket(port);
				bStart = true;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(FileServer.this.frame,
						"服务器启动失败,请检查该端口是否已经被占用.");
				bStart = false;
				return;
			}
			addMsgRec(getTimeStr() + " 聊天室服务器启动成功,在端口" + port + "侦听,默认密码为"
					+ password);
			// 修改按钮状态
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
			btnChoosePath.setEnabled(false);
			// 接收客户端连接,并创建客户端处理线程
			while (bStart) {
				Socket s = server.accept();
				// 启动客户端处理线程
				Thread t = new Thread(new ClientHandler(s));
				t.start();
			}
		}

		// 线程主体
		public void run() {
			try {
				start_server();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 处理每个客户端的线程
	class ClientHandler implements Runnable {
		Socket s; // 处理客户端的套接字
		String fileName; // 文件名
		long fileLen; // 文件长度
		long byteOfRecv; // 已经接收到的字节数
		boolean bAuthen = false; // 是否通过验证

		// 构造函数
		public ClientHandler(Socket s) {
			this.s = s;
		}

		// 验证客户端是否合法,并发回验证结果
		public void auth_user() throws IOException {
			// 网络数据输入、输出流
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			DataInputStream dis = new DataInputStream(s.getInputStream());
			// 读取报文类型
			int pktType = dis.readInt();
			if (pktType == PktCmd.PKT_AUTH) {
				// 读取文件名、文件长度和密码
				fileName = dis.readUTF();
				fileLen = dis.readLong();
				String pwd = dis.readUTF();
				// 检查密码是否正确,发回验证结果
				if (pwd.equals(password)) {
					// 密码正确，通过验证
					dos.writeInt(PktCmd.PKT_AUTH_RES);
					dos.writeInt(PktCmd.AUTH_SUCCESS);
					bAuthen = true;
					addMsgRec("\n" + getTimeStr() + " 客戶端"
							+ s.getRemoteSocketAddress() + "验证通过.");
				} else {
					// 密码错误,验证失败
					dos.writeInt(PktCmd.PKT_AUTH_RES);
					dos.writeInt(PktCmd.AUTH_FAIL);
					addMsgRec(getTimeStr() + " 客戶端"
							+ s.getRemoteSocketAddress() + "验证失败.");
				}
				dos.flush();
			}
		}

		// 接收文件
		public void recv_file() throws IOException {
			// 文件传输开始时间
			long startTime = System.currentTimeMillis();
			// 创建网络输入缓冲流
			BufferedInputStream bis = new BufferedInputStream(
					s.getInputStream());
			// 文件输出缓冲流
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(savePath + "\\" + fileName));
			byteOfRecv = 0;
			// 接收缓冲区
			byte[] recvBuf = new byte[4 * 1024];
			addMsgRec(getTimeStr() + " 接收来自于" + s.getRemoteSocketAddress()
					+ "的文件:\n" + fileName + ",共" + fileLen + "字节.");
			// 写入文件数据
			while (byteOfRecv < fileLen) {
				// 读取数据到接收缓冲区
				int len = bis.read(recvBuf);
				// 将接收缓冲区数据写入文件
				bos.write(recvBuf, 0, len);
				bos.flush();
				byteOfRecv += len;
			}
			bos.close();
			double spendTime = (System.currentTimeMillis() - startTime) / 1000.0;
			long rate = (long) (fileLen / spendTime);
			addMsgRec(getTimeStr() + "\n文件" + fileName + "接收完成,共" + fileLen
					+ "字节\n花费" + spendTime + "秒,传输速度为" + rate + "字节/秒"
					+ ".(来自于" + s.getRemoteSocketAddress() + ")");
			numOfUnploadFile++;
			lblFileCount.setText("目前已经上传" + numOfUnploadFile + "个文件");
		}

		// 线程主体
		public void run() {
			try {
				auth_user();
				// 如果验证通过，接收文件
				if (bAuthen) 
					recv_file();
			} catch (IOException e) {
			}
		}
	}
}
