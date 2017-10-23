package org.beprogramming.blogging.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Commentor;
import org.beprogramming.blogging.service.CommentorService;

@ManagedBean
@ViewScoped
public class CommentorController {
	@Inject
	private CommentorService	service;

	public List<Commentor> getList() {

		return service.findAll();
	}

	public Boolean isLogedIn() {

		return false;
	}
}
