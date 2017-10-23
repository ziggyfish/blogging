package org.beprogramming.blogging.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.beprogramming.blogging.model.Commentor;
import org.beprogramming.blogging.service.CommentorService;
import org.beprogramming.blogging.service.UserSession;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ActiveCommentorController implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private String				currentComment;

	private String				username;

	private String				password;

	private String				email;

	@Inject
	private CommentorService	service;

	@Inject
	private UserSession			session;

	protected void closeDialogByName(final String name) {

		RequestContext.getCurrentInstance().execute("PF('" + name + "').hide()");
	}

	public void doLogin() {

		if (!session.loginUser(username, password)) {
			System.out.println(username);
			messageBox("Login Failed", "Incorrect username or password");
			return;
		}
		closeDialogByName("dlLogin");

	}

	public void doLogout() {

		session.doLogout();
	}

	public void doRegsiter() {

		final Commentor c = new Commentor();
		c.setUsername(username);
		c.setPassword(password);
		c.setEmail(email);
		service.update(c);
		closeDialogByName("dlRegister");
	}

	public void forgotPassword() throws AddressException, MessagingException {

		final Commentor commentor = service.getEmail(email);

		if (commentor == null) {
			messageBox("Login Failed", "Email not found");
			return;
		}
		service.sendEmail(commentor);
		closeDialogByName("dlForgotPassword");
		closeDialogByName("dlLogin");

	}

	/**
	 * @return the commentorID
	 */
	public Integer getCommentorID() {

		return session.getCommentorID();
	}

	/**
	 * @return the commentorName
	 */
	public String getCommentorName() {

		return session.getUserName();
	}

	public String getCurrentComment() {

		return currentComment;
	}

	public String getEmail() {

		return email;
	}

	public boolean getIsLoggedIn() {

		return session.checkLoggedIn();
	}

	public String getPassword() {

		return password;
	}

	public String getUsername() {

		return username;
	}

	public void messageBox(final String title, final String msg) {

		final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);

		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	public void setCurrentComment(final String currentComment) {

		this.currentComment = currentComment;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	public void setPassword(final String password) {

		this.password = password;
	}

	public void setUsername(final String username) {

		this.username = username;
	}
}
