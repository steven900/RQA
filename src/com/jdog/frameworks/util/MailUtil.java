package com.jdog.frameworks.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

// TODO 整体改造

public class MailUtil {

	private static final String host = "smtp.126.com";
	private static final String user = "lijvwang@126.com";
	private static final String from = "lijvwang@126.com";
	private static final String pwd = "123456c";
	private static final int success = 1;
	private static final int fail = 0;

	public MailUtil() {

	}

	public static int send(String txt, String to) {
		System.out.println(txt);
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(user, pwd);
					}
				});
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			Multipart multipart = new MimeMultipart();
			BodyPart contentPart = new MimeBodyPart();

			message.setSubject("立居网邮箱");
			// contentPart.setText(txt);
			contentPart.setContent(txt, "text/html; charset=utf-8");
			multipart.addBodyPart(contentPart);
			message.setContent(multipart);
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(host, user, pwd);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
			return fail;
		}
		System.out.println("mail--send");
		return success;
	}

}