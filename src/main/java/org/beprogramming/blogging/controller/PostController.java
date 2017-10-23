package org.beprogramming.blogging.controller;

import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.data.PostRepository;
import org.beprogramming.blogging.model.Post;
import org.beprogramming.blogging.model.types.PostGenre;
import org.beprogramming.blogging.service.PostService;
import org.beprogramming.blogging.util.AbstractController;
import org.beprogramming.blogging.util.Utility;
import org.beprogramming.std.CrudService;

@ManagedBean
@ViewScoped
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class PostController extends AbstractController<Post> {

	@Inject
	private PostRepository	postRepository;

	@Inject
	private PostService		service;

	private List<Post>		list;

	@Override
	public void add() {

		super.add();
		list = null;
	}

	@Override
	protected boolean completeCompare(final Post v, final String query) {

		return false;
	}

	@Override
	protected Post createNew() {

		return new Post();
	}

	@Override
	public void delete() {

	}

	public void deleteItem(final Integer itemID) {

		service.deleteById(itemID);
		list = null;
	}

	public PostGenre[] getGenres() {

		return PostGenre.values();
	}

	@Override
	public List<Post> getList() {

		if (list == null)
			list = postRepository.getAllPosts();
		return list;
	}

	public String getPostURL(final Post p) {

		return Utility.getPostURL(p);
	}

	@Override
	protected CrudService<Post> getService() {

		return service;
	}

	public void save(final Post p) {

		service.update(p);
		list = null;
	}

}
