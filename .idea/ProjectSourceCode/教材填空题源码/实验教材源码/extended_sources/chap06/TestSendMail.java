package javamail;

import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class TestSendMail {

	public static void main(String[] args)  throws Exception{
		
		//ָ��SMTP�ʼ�����������֤ģʽ
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.auth", true);
			
		//�����ʼ��Ựjavax.mail.Session����
		Session session = Session.getDefaultInstance(props, new MailAuthenticator());
		session.setDebug(true);	// ����debugģʽ�������������Ϣ
		
		//�����ʼ�javax.mail.Message����
		Message msg = new MimeMessage(session);
		
		//�����ʼ�ͷ���������ʼ��������ַ�������ʼ��������ַ���ʼ����⣬��������
		msg.setFrom(new InternetAddress("majunlzu@qq.com"));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress("majun@lzu.edu.cn"));		
		msg.setSubject("�ҵ�Java�ʼ�");
		msg.setSentDate(new Date());
		
		//�����ʼ����ģ������ı����ͺ͸������͵Ĳ���
		Multipart multipart = new MimeMultipart();
		MimeBodyPart part1 = new MimeBodyPart(); //�ı�����
		part1.setText("�ʼ������е��ı�����","GBK");

		multipart.addBodyPart(part1);
		MimeBodyPart part2 = new MimeBodyPart(); //��������
		FileDataSource fds = new FileDataSource("attach.txt");
		part2.setDataHandler(new DataHandler(fds));
		part2.setFileName(fds.getName());
		multipart.addBodyPart(part2);
		msg.setContent(multipart); //�����ʼ�����

		//�����ʼ�
		Transport.send(msg);
	}
}

class MailAuthenticator extends Authenticator {
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("majunlzu@qq.com", "wmreihicugflbjai");
	}
}
