package org.beprogramming.blogging.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private int			commentID;

	@Lob
	private String		comment;

	@Column
	private Date		created	= new Date();

	@ManyToOne(fetch = FetchType.LAZY)

	@JoinColumn(name = "postID")

	private Post		post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "repliedID")
	private Comment		replyTo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commentorID")
	private Commentor	author;

	public Commentor getAuthor() {

		return author;
	}

	public String getComment() {

		return comment;
	}

	public int getCommentID() {

		return commentID;
	}

	public Date getCreated() {

		return created;
	}

	// Grabs post as a Post object
	public Post getPost() {

		return post;
	}

	public Comment getReplyTo() {

		return replyTo;
	}

	public void setAuthor(final Commentor author) {

		this.author = author;
	}

	public void setComment(final String comment) {

		this.comment = comment;
	}

	public void setCommentID(final int commentID) {

		this.commentID = commentID;
	}

	public void setCreated(final Date created) {

		this.created = created;
	}

	public void setPost(final Post post) {

		this.post = post;
	}

	public void setReplyTo(final Comment replyTo) {

		this.replyTo = replyTo;
	}

}
