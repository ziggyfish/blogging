package org.beprogramming.blogging.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Cartoon;
import org.beprogramming.blogging.service.CartoonService;
import org.beprogramming.blogging.util.Utility;

@ManagedBean
@RequestScoped
public class CartoonClientController {
	private static final Integer	COUNT	= 20;

	@Inject
	private CartoonService			service;

	private List<Cartoon>			list;

	@ManagedProperty("#{param.first}")
	private String					first;

	public void deleteCartoon(final Cartoon c) {

		service.deleteById(c.getStoryID());
	}

	public String getCartoonURL(final Cartoon c) {

		return Utility.getCartoonURL(c);
	}

	public String getFirst() {

		return first;
	}

	public Integer getFirstInt() {

		if (first == null)
			return 0;
		return Integer.parseInt(first);
	}

	public Boolean getIsMoreCartoons() {

		return service.getCartoon(COUNT, getFirstInt() + COUNT).size() > 0;
	}

	public Cartoon getLatestCartoon() {

		return service.getLatestCartoon();
	}

	public List<Cartoon> getList() {

		if (list == null)
			list = service.findAll();
		return list;
	}

	public String getNext() {

		if (first == null)
			return Integer.toString(COUNT);
		return Integer.toString(Integer.parseInt(first) + COUNT);

	}

	public List<Cartoon> getPaginatedList() {

		return service.getCartoon(getFirstInt(), COUNT);
	}

	public void save(final Cartoon c) {

		service.update(c);
	}

	public void setFirst(final String first) {

		this.first = first;
	}

}
