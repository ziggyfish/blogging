package org.beprogramming.blogging.service;

import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.beprogramming.blogging.model.Answer;
import org.beprogramming.blogging.model.Question;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class AnswerService extends CrudService<Answer> {

	public List<Answer> findAnswers(final Question q) {

		final EntityManager em = getEntityManager();
		final TypedQuery<Answer> query = em.createQuery("Select a from Answer a where a.question = :question order by a.id", Answer.class);
		query.setParameter("question", q);
		return query.getResultList();

	}

	@Override
	protected Class<Answer> getTypeClass() {

		return Answer.class;
	}

	public Long numberForResponse(final Answer q) {

		final EntityManager em = getEntityManager();
		final TypedQuery<Long> query = em.createQuery("Select count(1) from Answer a join a.commentorAnswers where a = :answer", Long.class);
		query.setParameter("answer", q);
		return query.getSingleResult();

	}

	public Long numberOfAnswers(final Question q) {

		final EntityManager em = getEntityManager();
		final TypedQuery<Long> query = em.createQuery("Select count(1) from Answer a join a.commentorAnswers where a.question = :question", Long.class);
		query.setParameter("question", q);
		return query.getSingleResult();

	}
}
