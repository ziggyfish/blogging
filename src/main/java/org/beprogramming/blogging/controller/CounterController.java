package org.beprogramming.blogging.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.beprogramming.blogging.service.CounterService;

@ManagedBean
@RequestScoped
public class CounterController {

	@Inject
	private CounterService service;

	public Integer getCurrentVisits(final Integer postID) {

		return service.getCurrentVisits(postID);
	}

	public Integer recordVisit(final Integer postID) {

		return service.recordVisit(postID);
	}
}
