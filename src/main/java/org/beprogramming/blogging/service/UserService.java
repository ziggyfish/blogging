package org.beprogramming.blogging.service;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;

import org.beprogramming.blogging.model.User;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class UserService extends CrudService<User> {

	@Override
	public Object getID(final User item) {

		return item.getUserID();
	}

	@Override
	protected Class<User> getTypeClass() {

		return User.class;
	}

}
