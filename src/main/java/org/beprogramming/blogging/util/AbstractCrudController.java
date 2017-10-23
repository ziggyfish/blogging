package org.beprogramming.blogging.util;

import java.util.List;

import org.beprogramming.std.CrudService;

abstract public class AbstractCrudController<T> {

	private T		current	= createNew();

	private List<T>	list;

	public void add() {

		getService().update(current);
		clearList();
	}

	public void clear() {

		current = createNew();
	}

	public void clearList() {

		list = null;
	}

	abstract protected T createNew();

	protected void deleteItem(final T item) {

		getService().delete(item);
		clearList();
	}

	public T getCurrent() {

		return current;
	}

	public List<T> getList() {

		if (list == null)
			list = getService().findAll();
		return list;
	}

	abstract protected CrudService<T> getService();

	public void setCurrent(final T current) {

		this.current = current;
	}

	public void update() {

		getService().update(current);
		clearList();
	}

}
