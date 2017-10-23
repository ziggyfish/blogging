package org.beprogramming.blogging.controller;

import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Offer;
import org.beprogramming.blogging.service.OfferService;
import org.beprogramming.blogging.util.AbstractController;
import org.beprogramming.std.CrudService;

@ManagedBean
@ViewScoped
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class OfferController extends AbstractController<Offer> {
	@Inject
	private OfferService	service;

	private List<Offer>		list;

	@Override
	public void add() {

		super.add();
		list = null;
	}

	@Override
	protected boolean completeCompare(final Offer v, final String query) {

		return v.getName().contains(query);
	}

	@Override
	protected Offer createNew() {

		return new Offer();
	}

	@Override
	public List<Offer> getList() {

		if (list == null)
			list = service.findAll();
		return list;
	}

	public String getOfferHTML(final Integer width, final Integer height, final String placement) {

		final Offer o = service.getAdUnit(width, height, placement);
		if (o == null)
			return "";
		return o.getOfferHTML();
	}

	public String getOfferHTML(final String placement) {

		final Offer o = service.getAdUnit(placement);
		if (o == null)
			return "";
		return o.getOfferHTML();
	}

	@Override
	protected CrudService<Offer> getService() {

		return service;
	}

	public void save(final Offer p) {

		service.update(p);
		list = null;
	}

}
