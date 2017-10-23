package org.beprogramming.blogging.service;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;

import org.beprogramming.blogging.model.Category;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CategoryService extends CrudService<Category> {

	@Override
	protected Class<Category> getTypeClass() {

		return Category.class;
	}

}
