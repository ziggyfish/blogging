package org.beprogramming.blogging.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Answer {

	@Id
	@GeneratedValue
	private int				answerID;

	@Column
	private String			answer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questionId")
	private Question		question;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "CommentorAnswers", inverseJoinColumns = { @JoinColumn(name = "commentorID", referencedColumnName = "commentorID") }, joinColumns = { @JoinColumn(name = "answerID", referencedColumnName = "answerID") })
	private List<Commentor>	commentorAnswers;

	public String getAnswer() {

		return answer;
	}

	public int getAnswerID() {

		return answerID;
	}

	public List<Commentor> getCommentorAnswers() {

		return commentorAnswers;
	}

	public Question getQuestion() {

		return question;
	}

	public void setAnswer(final String answer) {

		this.answer = answer;
	}

	public void setAnswerID(final int answerID) {

		this.answerID = answerID;
	}

	public void setCommentorAnswers(final List<Commentor> commentorAnswers) {

		this.commentorAnswers = commentorAnswers;
	}

	public void setQuestion(final Question question) {

		this.question = question;
	}
}
