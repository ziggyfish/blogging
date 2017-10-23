package org.beprogramming.std;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class Service<T> {

	@Inject
	private EntityManager em;

	protected EntityManager getEntityManager() {

		return em;
	}
}
