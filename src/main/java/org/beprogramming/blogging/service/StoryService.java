package org.beprogramming.blogging.service;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;

import org.beprogramming.blogging.model.Post;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class StoryService extends CrudService<Post> {

	@Override
	protected Class<Post> getTypeClass() {

		return Post.class;
	}
}
