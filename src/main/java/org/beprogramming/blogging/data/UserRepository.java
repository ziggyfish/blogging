package org.beprogramming.blogging.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.beprogramming.blogging.model.User;

@ApplicationScoped
public class UserRepository {
	@Inject
	EntityManager	em;

	public User findUserById(final int id) {

		// TODO Auto-generated method stub

		return em.find(User.class, id);
	}

	public List<User> getAllUsers() {

		final TypedQuery<User> query = em.createQuery("from User", User.class);

		return query.getResultList();
	}

}
