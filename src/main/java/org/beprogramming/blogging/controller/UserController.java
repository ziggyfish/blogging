package org.beprogramming.blogging.controller;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.model.User;
import org.beprogramming.blogging.service.UserService;
import org.beprogramming.blogging.util.AbstractController;
import org.beprogramming.std.CrudService;

@ManagedBean
@ViewScoped
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class UserController extends AbstractController<User> {

	@Inject
	private UserService service;

	@Override
	protected boolean completeCompare(final User v, final String query) {

		return v.getName().contains(query);
	}

	@Override
	protected User createNew() {

		return new User();
	}

	@Override
	protected CrudService<User> getService() {

		return service;
	}

}
