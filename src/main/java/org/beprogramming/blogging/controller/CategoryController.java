package org.beprogramming.blogging.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Category;
import org.beprogramming.blogging.service.CategoryService;
import org.beprogramming.blogging.util.AbstractController;
import org.beprogramming.blogging.util.Utility;
import org.beprogramming.std.CrudService;

@ManagedBean
@ViewScoped
public class CategoryController extends AbstractController<Category> {

	@Inject
	private CategoryService service;

	public String categoryName(final String categoryID) {

		return service.find(Integer.parseInt(categoryID)).getName();
	}

	@Override
	protected boolean completeCompare(final Category v, final String query) {

		return v.getName().contains(query);
	}

	@Override
	protected Category createNew() {

		return new Category();
	}

	public String getCategoryURL(final Integer categoryID) {

		return Utility.getCategoryURL(service.find(categoryID));
	}

	public String getCategoryURLC(final Category cat) {

		return Utility.getCategoryURL(cat);
	}

	@Override
	public List<Category> getList() {

		return super.getList();
	}

	@Override
	protected CrudService<Category> getService() {

		return service;
	}
}
