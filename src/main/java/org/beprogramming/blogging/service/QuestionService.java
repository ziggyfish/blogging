package org.beprogramming.blogging.service;

import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.beprogramming.blogging.model.Commentor;
import org.beprogramming.blogging.model.Question;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class QuestionService extends CrudService<Question> {

	@Override
	public List<Question> findAll() {

		final EntityManager em = getEntityManager();
		final TypedQuery<Question> query = em.createQuery("Select q from Question q order by q.created desc", Question.class);
		return query.getResultList();
	}

	public Question getDailyQuestion() {

		final EntityManager em = getEntityManager();
		final TypedQuery<Question> query = em.createQuery("Select q from Question q order by q.created desc", Question.class);
		query.setMaxResults(1);
		return query.getSingleResult();

	}

	@Override
	protected Class<Question> getTypeClass() {

		return Question.class;
	}

	public Boolean hasAnswered(final Commentor c, final Question q) {

		final EntityManager em = getEntityManager();
		final TypedQuery<Long> query = em.createQuery("Select count(1) from Answer a where :commentor MEMBER OF a.commentorAnswers and a.question = :question", Long.class);
		query.setParameter("question", q);
		query.setParameter("commentor", c);
		final Long i = query.getSingleResult();

		return i != 0;

	}
}
