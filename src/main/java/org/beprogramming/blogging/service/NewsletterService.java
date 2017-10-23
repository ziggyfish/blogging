package org.beprogramming.blogging.service;

import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.beprogramming.blogging.model.Newsletter;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class NewsletterService extends CrudService<Newsletter> {

	@Resource(mappedName = "java:jboss/mail/Default")
	Session gmailSession;

	@Override
	public void create(final Newsletter item) {

		super.create(item);
		try {

			final Message mailMessage = new MimeMessage(gmailSession);
			final InternetAddress from = new InternetAddress();
			from.setAddress("contact@zanettisview.com");

			final InternetAddress to = new InternetAddress();
			to.setAddress(item.getEmail());
			mailMessage.setFrom(from);
			mailMessage.setRecipient(Message.RecipientType.TO, to);

			mailMessage.setSubject("Welcome To Zanettis View");
			String message = "Thanks for subscribing to the Zanetti’s View newsletter.\n\n";
			message += "We’ll be sending you an alternative look at the news on a weekly basis. If  at any time want to unsubscribe from the email newsletter, it’s easy to do so by clicking on the little ‘unsubscribe’ link at the bottom of the newsletter.\n\n";
			message += "Feel free to join in on the conversation, comment, post and share.\n\n";
			message += "If you’re on facebook and you want post on the site there’s no need to register on the site. As long as you’re on facebook and logged in you can comment.\n\n";
			message += "If you don’t have a facebook account, simply click on the comment box and you’ll be directed to facebook to create an account.\n\n";
			message += "Check in regularly for updates on news, opinions and cartoons.\n\n";
			message += "Paul Zanetti";
			mailMessage.setText(message);

			Transport.send(mailMessage);

			System.out.println("Message Sent ok");

		} catch (final MessagingException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Object getID(final Newsletter item) {

		// TODO Auto-generated method stub
		return item.getNewsletterID();
	}

	@Override
	protected Class<Newsletter> getTypeClass() {

		return Newsletter.class;
	}

}
