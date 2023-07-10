package javamail;

import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class TestSendMail {

	public static void main(String[] args)  throws Exception{
		
		//指定SMTP邮件服务器和认证模式
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.auth", true);
			
		//创建邮件会话javax.mail.Session对象
		Session session = Session.getDefaultInstance(props, new MailAuthenticator());
		session.setDebug(true);	// 启用debug模式，会输出交互信息
		
		//创建邮件javax.mail.Message对象
		Message msg = new MimeMessage(session);
		
		//设置邮件头部：发送邮件的邮箱地址，接收邮件的邮箱地址，邮件标题，发送日期
		msg.setFrom(new InternetAddress("majunlzu@qq.com"));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress("majun@lzu.edu.cn"));		
		msg.setSubject("我的Java邮件");
		msg.setSentDate(new Date());
		
		//设置邮件正文：包含文本类型和附件类型的部件
		Multipart multipart = new MimeMultipart();
		MimeBodyPart part1 = new MimeBodyPart(); //文本类型
		part1.setText("邮件正文中的文本内容","GBK");

		multipart.addBodyPart(part1);
		MimeBodyPart part2 = new MimeBodyPart(); //附件类型
		FileDataSource fds = new FileDataSource("attach.txt");
		part2.setDataHandler(new DataHandler(fds));
		part2.setFileName(fds.getName());
		multipart.addBodyPart(part2);
		msg.setContent(multipart); //设置邮件正文

		//发送邮件
		Transport.send(msg);
	}
}

class MailAuthenticator extends Authenticator {
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("majunlzu@qq.com", "wmreihicugflbjai");
	}
}
