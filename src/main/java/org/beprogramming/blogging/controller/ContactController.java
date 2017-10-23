package org.beprogramming.blogging.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.service.ContactService;

@ManagedBean
@ViewScoped
public class ContactController {
	@Inject
	private ContactService	service;

	private String			name;
	private String			email;
	private String			subject;
	private String			message;
	private Boolean			hasContacted	= false;

	public String getEmail() {

		return email;
	}

	/**
	 * @return the hasContacted
	 */
	public Boolean getHasContacted() {

		return hasContacted;
	}

	public String getMessage() {

		return message;
	}

	public String getName() {

		return name;
	}

	public String getSubject() {

		return subject;
	}

	public void send() {

		service.send(name, email, subject, message);
		hasContacted = true;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	/**
	 * @param hasContacted
	 *            the hasContacted to set
	 */
	public void setHasContacted(final Boolean hasContacted) {

		this.hasContacted = hasContacted;
	}

	public void setMessage(final String message) {

		this.message = message;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public void setSubject(final String subject) {

		this.subject = subject;
	}
}
