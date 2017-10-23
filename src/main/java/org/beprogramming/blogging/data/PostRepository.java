package org.beprogramming.blogging.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.beprogramming.blogging.model.Post;

@Stateless
public class PostRepository {
	@Inject
	EntityManager	em;

	public List<Post> getAllPosts() {

		final TypedQuery<Post> query = em.createQuery("select p from Post p order by postID desc", Post.class);

		return query.getResultList();
	}

	public List<Post> getMainPosts(final int maxResults, final int skip) {

		final TypedQuery<Post> query = em.createQuery("select p from Post p " + " JOIN FETCH p.author"
				+ " JOIN FETCH p.category  where published = TRUE  order by created desc", Post.class);
		query.setMaxResults(maxResults);
		query.setFirstResult(skip);

		return query.getResultList();
	}

	public List<Post> getNewsPosts(final int maxResults, final int skip) {

		final TypedQuery<Post> query = em
				.createQuery(
						"select p from Post p where published = TRUE  where JOIN FETCH p.author JOIN FETCH p.category  isOpinion = FALSE or isOpinion is null order by created desc",
						Post.class);
		query.setMaxResults(maxResults);
		query.setFirstResult(skip);
		return query.getResultList();
	}

	public List<Post> getOpinionPosts(final int maxResults, final int skip) {

		final TypedQuery<Post> query = em.createQuery(
				"select p from Post p JOIN FETCH p.author JOIN FETCH p.category  where published = TRUE and  isOpinion = TRUE order by created desc",
				Post.class);
		query.setMaxResults(maxResults);
		query.setFirstResult(skip);
		return query.getResultList();
	}

	public List<Post> getPostByCategory(final Integer categoryID, final int maxResults, final int skip) {

		final TypedQuery<Post> query = em
				.createQuery(
						"select p from Post p JOIN FETCH p.author JOIN FETCH p.category where published = TRUE and p.category.categoryID = :catID order by created desc",
						Post.class);
		query.setParameter("catID", categoryID);
		// TODO Auto-generated method stub
		query.setMaxResults(maxResults);
		query.setFirstResult(skip);
		return query.getResultList();
	}
}
