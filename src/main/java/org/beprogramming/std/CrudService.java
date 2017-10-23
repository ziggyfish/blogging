package org.beprogramming.std;

import java.util.List;

public class CrudService<T> extends Service<T> {

	public void create(final T item) {

		getEntityManager().persist(item);
		getEntityManager().flush();
	}

	public void delete(final T item) {

		getEntityManager().remove(item);
	}

	public void deleteById(final Object id) {

		delete(find(id));
	}

	public void deleteDetached(final T item) {

		final T attachedItem = find(getID(item));
		delete(attachedItem);
	}

	public T find(final Object id) {

		return getEntityManager().find(getTypeClass(), id);
	}

	public List<T> findAll() {

		return getEntityManager().createQuery("FROM " + getTypeClass().getSimpleName(), getTypeClass()).getResultList();
	}

	public Object getID(final T item) {

		return null;
	}

	protected Class<T> getTypeClass() {

		return null;
	}

	public void update(final T item) {

		getEntityManager().merge(item);
		getEntityManager().flush();
	}
}
