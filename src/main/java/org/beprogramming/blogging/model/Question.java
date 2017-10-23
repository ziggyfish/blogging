package org.beprogramming.blogging.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {

	@Id
	@GeneratedValue
	private Integer	questionID;

	@Column
	private String	question;

	@Column
	private Date	created;

	public Date getCreated() {

		return created;
	}

	public String getQuestion() {

		return question;
	}

	public Integer getQuestionID() {

		return questionID;
	}

	public void setCreated(final Date created) {

		this.created = created;
	}

	public void setQuestion(final String question) {

		this.question = question;
	}

	public void setQuestionID(final Integer questionID) {

		this.questionID = questionID;
	}

}
