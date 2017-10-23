package org.beprogramming.blogging.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Offer;
import org.beprogramming.blogging.model.Post;
import org.beprogramming.blogging.service.CommentService;
import org.beprogramming.blogging.service.CommentorService;
import org.beprogramming.blogging.service.OfferService;
import org.beprogramming.blogging.service.PostService;
import org.beprogramming.blogging.service.UserSession;

@ManagedBean
@ViewScoped
public class PostSingleController {

	@Inject
	private PostService			service;

	private Integer				postid;

	private String				currentComment;

	private Post				post;

	@Inject
	private UserSession			session;
	@Inject
	private CommentorService	commentorService;
	@Inject
	private CommentService		commentService;

	@Inject
	OfferService				offerService;

	public void addComment() {

		commentService.addComment(commentorService.find(session.getCommentorID()), getPost(), currentComment);

	}

	public String getCurrentComment() {

		return currentComment;
	}

	public Post getPost() {

		if (post == null)
			post = service.find(postid);
		return post;
	}

	public String getPostContent() {

		final Post p = getPost();
		if (p == null)
			return "";
		final String content = p.getContent();
		final Offer addUnit = offerService.getAdUnit("in_article");
		String u = "";
		if (addUnit != null)
			u = "<div id=\"inAd\">" + addUnit.getOfferHTML() + "</div>";

		return content.replace("[[google_ads]]", u);
	}

	public Integer getPostid() {

		return postid;
	}

	public void setCurrentComment(final String currentComment) {

		this.currentComment = currentComment;
	}

	public void setPostid(final Integer postid) {

		this.postid = postid;
	}

}