package org.beprogramming.blogging.service;

import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.beprogramming.blogging.model.Comment;
import org.beprogramming.blogging.model.Commentor;
import org.beprogramming.blogging.model.Post;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CommentService extends CrudService<Comment> {

	public void addComment(final Commentor author, final Post post, final String comment) {

		final Comment c = new Comment();
		c.setAuthor(author);
		c.setPost(post);
		c.setComment(comment);
		create(c);
	}

	public List<Comment> findByPost(final Integer postID) {

		final EntityManager em = getEntityManager();
		final TypedQuery<Comment> query = em.createQuery("Select c from Comment c where c.post.postID = :postid ORDER BY c.commentID DESC", Comment.class);
		query.setParameter("postid", postID);
		return query.getResultList();

	}

	public List<Comment> findSubComments(final Integer commentID) {

		final EntityManager em = getEntityManager();
		final TypedQuery<Comment> query = em.createQuery("Select c from Comment c where c.replyTo.commentID = :commentID", Comment.class);
		query.setParameter("commentID", commentID);
		return query.getResultList();
	}

	@Override
	protected Class<Comment> getTypeClass() {

		return Comment.class;
	}

}
