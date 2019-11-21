package com.example.demo.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
* @author：Administrator
* @createDate:2019-11-21 50:17
* @description:SendEmail
*/
public class SendEmail {
	private final static String TAG = "SendEmail";
	private static Logger logger = LoggerFactory.getLogger(SendEmail.class);

	private static final String SMTP_PORT = "25";
	private static final String SMTP_HOST = "smtp.ym.163.com";
	private static final String SMTP_AUTH = "true";
	private static final String SMTP_STARTTLS_ENABLE = "true";

	private static Properties props = new Properties();
	private static Session session;

	private SendEmail() {

	}

	public static void Send(String sendSubject, String sendStr) {
		if (props == null)
			return;
		props.clear();
		props.put("mail.smtp.auth", SMTP_AUTH);
		props.put("mail.smtp.starttls.enable", SMTP_STARTTLS_ENABLE);
		props.put("mail.smtp.host", SMTP_HOST);
		props.put("mail.smtp.port", SMTP_PORT);
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("pms@pinglian.hk","pinglian@2015");
			}
		});
		if (session == null)
			return;
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("pms@pinglian.hk"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("caiyunxin@pinglian.hk"));
//			String[] address_cc = MQConfig.get("pms.log.email.to.addcc").split("----");
			String[] address_cc = {"tengshike@pinglian.hk"};
			if (address_cc != null && address_cc.length > 0) {
				for (int i = 0; i < address_cc.length; i++) {
					if (!address_cc[i].isEmpty()) {
						message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(address_cc[i]));
					}
				}
			}
			message.setSubject(sendSubject);
			message.setText(sendStr);

			Transport.send(message);
			logger.debug(TAG + address_cc + "send Email");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
