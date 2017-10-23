package org.beprogramming.blogging.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Answer;
import org.beprogramming.blogging.model.Commentor;
import org.beprogramming.blogging.model.Question;
import org.beprogramming.blogging.service.AnswerService;
import org.beprogramming.blogging.service.CommentorService;
import org.beprogramming.blogging.service.QuestionService;
import org.beprogramming.blogging.service.UserSession;
import org.beprogramming.blogging.util.AbstractController;
import org.beprogramming.blogging.util.Utility;
import org.beprogramming.std.CrudService;

@ManagedBean
@ViewScoped
public class QuestionController extends AbstractController<Question> {

	@Inject
	private QuestionService		service;

	@Inject
	private AnswerService		answerService;

	@Inject
	private CommentorService	commentorService;

	private Question			currentQuestion;

	private String				question;

	private String				answer;

	private Set<Integer>		showResults;

	@Inject
	private UserSession			session;

	private List<Question>		list;

	public void addQuestion() {

		final Question q = new Question();
		q.setQuestion(question);
		q.setCreated(new Date());
		service.create(q);

		final String[] answers = answer.split(",,");
		for (final String a : answers) {
			final Answer an = new Answer();
			an.setAnswer(a.trim());
			an.setQuestion(q);
			answerService.create(an);
		}
		clearList();
		closeDialog();
	}

	@Override
	protected boolean completeCompare(final Question v, final String query) {

		return v.getQuestion().contains(query);
	}

	@Override
	protected Question createNew() {

		return new Question();
	}

	public String displayCreated(final Date d) {

		if (d == null)
			return "";
		return new SimpleDateFormat("EEEE d MMMM yyyy").format(d);
	}

	public String getAnswer() {

		return answer;
	}

	public List<Answer> getAnswers(final Question q) {

		return answerService.findAnswers(q);
	}

	public Question getCurrentQuestion() {

		try {
			if (currentQuestion == null)
				currentQuestion = service.getDailyQuestion();
			return currentQuestion;
		} catch (final Exception e) {
			return null;
		}
	}

	@Override
	public List<Question> getList() {

		if (list == null)
			list = service.findAll();
		return list;
	}

	public String getQuestion() {

		return question;
	}

	public String getQuestionURL(final Question q) {

		return Utility.getQuestionURL(q);
	}

	@Override
	protected CrudService<Question> getService() {

		return service;
	}

	public Set<Integer> getShowResults() {

		if (showResults == null)
			showResults = new TreeSet<>();
		return showResults;
	}

	public Boolean hasAnswered(final Question q) {

		final Integer commentorID = session.getCommentorID();
		if (getShowResults().contains(q.getQuestionID()))
			return true;
		if (commentorID == null)
			return false;

		final Commentor c = commentorService.find(commentorID);
		return service.hasAnswered(c, q);
	}

	public Long numberForResponse(final Answer q) {

		return answerService.numberForResponse(q);
	}

	public Long numberOfAnswers(final Question q) {

		return answerService.numberOfAnswers(q);
	}

	public void setAnswer(final String answer) {

		this.answer = answer;
	}

	public void setQuestion(final String question) {

		this.question = question;
	}

	public void setShowResults(final Set<Integer> showResults) {

		this.showResults = showResults;
	}

	public void showResult(final Question q) {

		getShowResults().add(q.getQuestionID());
	}

	public void submitAnswer(final Integer answerID) {

		final Integer commentorID = session.getCommentorID();
		final Commentor c = commentorService.find(commentorID);
		final Answer a = answerService.find(answerID);
		a.getCommentorAnswers().add(c);
		answerService.update(a);
	}

	public void submitAnswerNew() {

		if (answer == null)
			return;

		if (!session.checkLoggedIn()) {
			openDialogByName("dlLogin");
			return;
		}
		submitAnswer(Integer.parseInt(answer));

	}
}
