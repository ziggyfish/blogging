package org.beprogramming.blogging.util;

import java.lang.reflect.ParameterizedType;

abstract public class AbstractDialogController<T> extends AbstractAutoCompleteController<T> {

	@Override
	public void add() {

		super.add();
		closeDialog();
	}

	protected void closeDialog() {

		closeDialogByName(getDialogName());

	}

	protected void closeDialogByName(final String name) {

		// RequestContext.getCurrentInstance().execute("PF('" + name + "').hide()");
	}

	@SuppressWarnings("unchecked")
	protected String getDialogName() {

		return "dlg" + ((Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
	}

	protected void openDialogByName(final String name) {

		// RequestContext.getCurrentInstance().execute("PF('" + name + "').show()");
	}

}
