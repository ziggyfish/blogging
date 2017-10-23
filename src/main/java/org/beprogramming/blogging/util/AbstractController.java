package org.beprogramming.blogging.util;

abstract public class AbstractController<T> extends AbstractDialogController<T> {

	private T[] selected;

	public void delete() {

		System.out.println(selected);

	}

	public T[] getSelected() {

		return selected;
	}

	public void setSelected(final T[] selected) {

		this.selected = selected;
	}

}
