package org.beprogramming.blogging.controller;

import javax.faces.bean.ViewScoped;

@javax.faces.bean.ManagedBean
@ViewScoped
public class GravatarController {

	private int		size		= 200;

	private String	notFound	= "default";

	private String	username	= "iamadummygravataruser@gmail.com";

	public String getNotFound() {

		return notFound;
	}

	public int getSize() {

		return size;
	}

	public String getUsername() {

		return username;
	}

	public void setNotFound(final String notFound) {

		this.notFound = notFound;
	}

	public void setSize(final int size) {

		this.size = size;
	}

	public void setUsername(final String username) {

		this.username = username;
	}
}
