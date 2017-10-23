package org.beprogramming.blogging.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Cacheable
public class User {

	@Id
	@GeneratedValue
	private int		userID;

	@Column(unique = true)
	private String	username;

	@Column
	private String	email;

	@Column
	private String	password;

	@Column
	private String	website;

	@Column
	private String	name;

	@Column
	@Lob
	private String	caption;

	public String getCaption() {

		return caption;
	}

	public String getEmail() {

		return email;
	}

	public String getName() {

		return name;
	}

	public String getPassword() {

		return password;
	}

	public int getUserID() {

		return userID;
	}

	public String getUsername() {

		return username;
	}

	public String getWebsite() {

		return website;
	}

	public void setCaption(final String caption) {

		this.caption = caption;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public void setPassword(final String password) {

		this.password = password;
	}

	public void setUserID(final int userID) {

		this.userID = userID;
	}

	public void setUsername(final String username) {

		this.username = username;
	}

	public void setWebsite(final String website) {

		this.website = website;
	}

	@Override
	public String toString() {

		return "User [userID=" + userID + "]";
	}

}
