package org.beprogramming.blogging.service;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactService {
	@Resource(mappedName = "java:jboss/mail/Default")
	Session	gmailSession;

	public void send(final String name, final String email, final String subject, final String message) {

		try {
			System.out.println("Message: " + message);

			final Message mailMessage = new MimeMessage(gmailSession);
			final InternetAddress from = new InternetAddress();
			from.setAddress(email);
			from.setPersonal(name);

			final InternetAddress to = new InternetAddress();
			to.setAddress("paul@zanetti.net.au");
			mailMessage.setFrom(from);
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			mailMessage.setSubject(subject);
			mailMessage.setText(message);

			Transport.send(mailMessage);

			System.out.println("Message Sent ok");

		} catch (final MessagingException | UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
	}

}
