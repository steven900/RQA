package com.jdog.rqa.user;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtilorz {
	
	public static int sendmsg(String to, String content,String title){
		try{
		String host = "smtp.sina.com.cn";
		String from = "15857339101m0@sina.cn";
		String username = "15857339101m0@sina.cn";
		String passWord = "ming90322";

		Properties props = new Properties();

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true"); //这样才能通过验证

		Session session = Session.getDefaultInstance(props);

		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO,
		new InternetAddress(to));
		message.setSubject(title);
		//message.setText(content);
		message.setContent(content, "text/html;charset=UTF-8");// 发送邮件
		// Send message
		message.saveChanges();
		Transport transport = session.getTransport("smtp");
		transport.connect(host, username, passWord);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close(); 
		}catch(Exception e){
			e.printStackTrace();
		}
	    return 0;
	}
	
	public static void main(String[] args) {
		sendmsg("635388554@qq.com", "<a href='http://www.baidu.com'>nihao </a>","xxx");
	}
}
