package org.beprogramming.blogging.controller;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Newsletter;
import org.beprogramming.blogging.service.NewsletterService;

@ManagedBean
@RequestScoped
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class NewsletterController {

	@Inject
	private NewsletterService	service;

	private String				email;

	private Boolean				hasSubscribed	= false;

	public void add() {

		final Newsletter n = new Newsletter();
		n.setEmail(email);
		service.create(n);
		hasSubscribed = true;
	}

	public String getEmail() {

		return email;
	}

	public Boolean getHasSubscribed() {

		return hasSubscribed;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	public void setHasSubscribed(final Boolean hasSubscribed) {

		this.hasSubscribed = hasSubscribed;
	}
}
