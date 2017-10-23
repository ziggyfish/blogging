package org.beprogramming.blogging.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Commentor;

@SessionScoped
public class UserSession implements Serializable {

	/**
	 *
	 */
	private static final long	serialVersionUID	= -4852241621540030500L;

	private boolean				isLoggedIn			= false;

	@Inject
	private CommentorService	service;

	private String				userName;

	private Integer				commentorID;

	public boolean checkLoggedIn() {

		return isLoggedIn;

	}

	public void doLogout() {

		isLoggedIn = false;
		userName = null;
		commentorID = null;
	}

	public Integer getCommentorID() {

		return commentorID;
	}

	public String getUserName() {

		return userName;
	}

	public boolean loginUser(final String username, final String password) {

		final Commentor c = service.getCommentor(username, password);
		if (c == null)
			return false;
		userName = c.getUsername();
		commentorID = c.getCommentorID();
		isLoggedIn = true;

		return true;
	}

	public void setCommentorID(final Integer commentorID) {

		this.commentorID = commentorID;
	}

	public void setUserName(final String userName) {

		this.userName = userName;
	}
}
