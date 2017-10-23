package org.beprogramming.blogging.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Newsletter {
	@Id
	@GeneratedValue
	private int		newsletterID;

	@Column
	private String	email;

	public String getEmail() {

		return email;
	}

	public int getNewsletterID() {

		return newsletterID;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	public void setNewsletterID(final int newsletterID) {

		this.newsletterID = newsletterID;
	}

}
