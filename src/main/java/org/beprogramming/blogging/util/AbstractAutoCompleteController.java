package org.beprogramming.blogging.util;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractAutoCompleteController<T> extends AbstractCrudController<T> {

	private List<T> addServiceSuggestions(final String query, final List<T> suggestions) {

		final List<T> rows = getService().findAll();
		for (final T v : rows)
			if (completeCompare(v, query))
				suggestions.add(v);

		return suggestions;
	}

	public List<T> complete(final String query) {

		final List<T> suggestions = new ArrayList<>();

		return addServiceSuggestions(query, suggestions);
	}

	protected abstract boolean completeCompare(T v, String query);
}
