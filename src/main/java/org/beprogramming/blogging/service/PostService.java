package org.beprogramming.blogging.service;

import java.util.Date;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;

import org.beprogramming.blogging.model.Post;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class PostService extends CrudService<Post> {

	@Override
	public void create(final Post item) {

		item.setCreated(new Date());
		super.create(item);
	}

	@Override
	protected Class<Post> getTypeClass() {

		return Post.class;
	}

	@Override
	public void update(final Post item) {

		if (item.getCreated() == null)
			item.setCreated(new Date());
		super.update(item);
	}
}
