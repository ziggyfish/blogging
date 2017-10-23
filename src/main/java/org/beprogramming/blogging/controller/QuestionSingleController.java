package org.beprogramming.blogging.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Question;
import org.beprogramming.blogging.service.QuestionService;

@ManagedBean
@ViewScoped
public class QuestionSingleController {
	private Integer			questionID;

	@Inject
	private QuestionService	service;

	private Question		current;

	public Question getQuestion() {

		if (current != null)
			return current;
		current = service.find(questionID);
		return current;
	}

	public Integer getQuestionID() {

		return questionID;
	}

	public void setQuestionID(final Integer questionID) {

		this.questionID = questionID;
	}
}
