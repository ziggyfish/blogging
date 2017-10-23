package org.beprogramming.blogging.service;

import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.beprogramming.blogging.model.Cartoon;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CartoonService extends CrudService<Cartoon> {

	@Override
	public List<Cartoon> findAll() {

		final TypedQuery<Cartoon> query = getEntityManager().createQuery("select c from Cartoon c order by created desc", Cartoon.class);
		return query.getResultList();
	}

	public List<Cartoon> getCartoon(final Integer start, final Integer count) {

		final TypedQuery<Cartoon> query = getEntityManager().createQuery("select c from Cartoon c order by created desc", Cartoon.class);
		query.setFirstResult(start);
		query.setMaxResults(count);

		return query.getResultList();
	}

	public Cartoon getLatestCartoon() {

		final EntityManager em = getEntityManager();
		final TypedQuery<Cartoon> query = em.createQuery("select c from Cartoon c order by created desc", Cartoon.class);
		query.setMaxResults(1);
		return query.getSingleResult();
	}

	@Override
	protected Class<Cartoon> getTypeClass() {

		return Cartoon.class;
	}
}
