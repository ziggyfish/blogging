package org.beprogramming.blogging.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Cartoon;
import org.beprogramming.blogging.service.CartoonService;

@ManagedBean
@RequestScoped
public class CartoonSingleController {

	@ManagedProperty("#{param.cartoonid}")
	private Integer			postid;

	private Cartoon			cartoon;

	@Inject
	private CartoonService	service;

	public Cartoon getCartoon() {

		if (cartoon == null)
			cartoon = service.find(postid);
		return cartoon;
	}

	public Integer getPostid() {

		return postid;
	}

	public void setPostid(final Integer postid) {

		this.postid = postid;
	}

}
