package javamail;

import java.io.*;
import java.util.*;
import javax.mail.*;

public class TestReceiveMail {

	public static void main(String[] args) throws Exception {

		// �����ʼ��Ựjavax.mail.Session����
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("majunlzu@qq.com",
								"awzlieihkgvrbidj");
					}
				}
		);
		session.setDebug(true);// ����debugģʽ�������������Ϣ

		// ����û��洢�ռ�javax.mail.Store����
		Store store = session.getStore("imap"); // ָ�������ʼ���Э������
		store.connect("imap.qq.com", null, null); // ���ӽ����ʼ�������

		// ���ʼ�����ȡ�ʼ�
		Folder folder = store.getFolder("inbox");
		folder.open(Folder.READ_ONLY); // ָ��һֻ��ģʽ����

		// �鿴�ʼ�����
		int messageCount = folder.getMessageCount();
		System.out.println("�ʼ�������" + messageCount);

		// ��ȡ����յ����ʼ�
		Message msg = folder.getMessage(messageCount);

		// ��ʾ�ʼ�ͷ����Ϣ
		System.out.println("Date:" + msg.getSentDate());
		System.out.println("From:" + msg.getFrom()[0]);
		System.out.println("Subject:" + msg.getSubject());

		// ��ȡ�ʼ�������Ϣ
		Multipart multipart = (Multipart) msg.getContent();
		int partCount = multipart.getCount();
		for (int index = 0; index < partCount; index++) {
			Part part = multipart.getBodyPart(index);
			String fileName = part.getFileName();
			System.out.println("�ļ�����"+fileName);
			if (fileName == null) { //������Ǹ�����ֱ�Ӵ�ӡ����
				System.out.println(part.getContentType());
				System.out.println(part.getContent());
			} else {	// ����Ǹ������򱣴浽���ش���
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
