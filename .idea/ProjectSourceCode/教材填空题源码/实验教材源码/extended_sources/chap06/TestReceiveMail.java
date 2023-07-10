package javamail;

import java.io.*;
import java.util.*;
import javax.mail.*;

public class TestReceiveMail {

	public static void main(String[] args) throws Exception {

		// 创建邮件会话javax.mail.Session对象
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("majunlzu@qq.com",
								"awzlieihkgvrbidj");
					}
				}
		);
		session.setDebug(true);// 启用debug模式，会输出交互信息

		// 获得用户存储空间javax.mail.Store对象
		Store store = session.getStore("imap"); // 指定接收邮件的协议类型
		store.connect("imap.qq.com", null, null); // 连接接收邮件服务器

		// 打开邮件夹收取邮件
		Folder folder = store.getFolder("inbox");
		folder.open(Folder.READ_ONLY); // 指定一只读模式开发

		// 查看邮件数量
		int messageCount = folder.getMessageCount();
		System.out.println("邮件数量：" + messageCount);

		// 获取最近收到的邮件
		Message msg = folder.getMessage(messageCount);

		// 显示邮件头部信息
		System.out.println("Date:" + msg.getSentDate());
		System.out.println("From:" + msg.getFrom()[0]);
		System.out.println("Subject:" + msg.getSubject());

		// 获取邮件正文信息
		Multipart multipart = (Multipart) msg.getContent();
		int partCount = multipart.getCount();
		for (int index = 0; index < partCount; index++) {
			Part part = multipart.getBodyPart(index);
			String fileName = part.getFileName();
			System.out.println("文件名："+fileName);
			if (fileName == null) { //如果不是附件，直接打印内容
				System.out.println(part.getContentType());
				System.out.println(part.getContent());
			} else {	// 如果是附件，则保存到本地磁盘
				File file = new File(fileName);
				InputStream in = new BufferedInputStream(part.getInputStream());
				OutputStream out = new BufferedOutputStream(
						new FileOutputStream(file));
				int b;
				while ((b = in.read()) != -1) {
					out.write(b);
				}
				out.close();
				in.close();
			}
		}
	}
}
