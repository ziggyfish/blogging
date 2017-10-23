package org.beprogramming.blogging.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Commentor {
	@Id
	@GeneratedValue
	private int		commentorID;

	@Column(unique = true)
	private String	username;

	@Column
	@Email
	@NotEmpty
	private String	email;

	@Column
	private String	password;

	@Column
	private String	website;

	public int getCommentorID() {

		return commentorID;
	}

	public String getEmail() {

		return email;
	}

	public String getPassword() {

		return password;
	}

	public String getUsername() {

		return username;
	}

	public String getWebsite() {

		return website;
	}

	public void setCommentorID(final int commentorID) {

		this.commentorID = commentorID;
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

	public void setWebsite(final String website) {

		this.website = website;
	}
}
