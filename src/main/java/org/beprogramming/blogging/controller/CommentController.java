package org.beprogramming.blogging.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Comment;
import org.beprogramming.blogging.service.CommentService;

@ManagedBean
@ViewScoped
public class CommentController {
	@Inject
	private CommentService	service;

	public List<Comment> getComments(final Integer postID) {

		return service.findByPost(postID);
	}

	public List<Comment> getList() {

		return service.findAll();
	}

	public List<Comment> getSubComments(final Integer commentID) {

		return service.findSubComments(commentID);
	}

	public Boolean hasSubComments(final Integer commentID) {

		return !service.findSubComments(commentID).isEmpty();
	}

}
