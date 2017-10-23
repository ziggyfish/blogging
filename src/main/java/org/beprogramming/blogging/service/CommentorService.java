package org.beprogramming.blogging.service;

import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.beprogramming.blogging.model.Commentor;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CommentorService extends CrudService<Commentor> {

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session session;

	public Commentor getCommentor(final String username, final String password) {

		try {
			final TypedQuery<Commentor> query = getEntityManager().createQuery("select c from Commentor c where LOWER(username) = LOWER(:user) and LOWER(password) = LOWER(:pass)", Commentor.class);
			query.setParameter("user", username);
			query.setParameter("pass", password);
			return query.getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}

	}

	public Commentor getEmail(final String email) {

		try {
			final TypedQuery<Commentor> query = getEntityManager().createQuery("select c from Commentor c where email = :email", Commentor.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}

	}

	@Override
	protected Class<Commentor> getTypeClass() {

		return Commentor.class;
	}

	public void sendEmail(final Commentor commentor) throws AddressException, MessagingException {

		final Message message = new MimeMessage(session);
		String msg = "You have been sent this email because you have forgotten your password for zanettisview.com\n\n";
		msg += "The following are your username and password. Please store in a file on your computer for future reference\n\n";
		msg += "Username: " + commentor.getUsername() + "\n";
		msg += "Password: " + commentor.getPassword();
		message.setFrom(new InternetAddress("info@zanettisview.com"));
		message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(commentor.getEmail()));
		// message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("bed88012@gmail.com"));
		message.setSubject("Zanetti's View - Forgot password");

		message.setText(msg);

		Transport.send(message);
	}

}
