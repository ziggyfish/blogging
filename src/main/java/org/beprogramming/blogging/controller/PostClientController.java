package org.beprogramming.blogging.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

import org.beprogramming.blogging.data.PostRepository;
import org.beprogramming.blogging.model.Post;

@ManagedBean
@RequestScoped
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class PostClientController {
	private static final int				COUNT			= 15;

	@ManagedProperty("#{param.first}")
	private String							first;

	@Inject
	private PostRepository					postRepository;

	private final Map<String, List<Post>>	mainPostList	= new HashMap<>();

	public String getFirst() {

		return first;
	}

	public Integer getFirstInt() {

		if (first == null)
			return 1;
		return Integer.parseInt(first);
	}

	public Boolean getIsMoreCateogryStories(final Integer categoryID) {

		return postRepository.getPostByCategory(categoryID, COUNT, getFirstInt() + COUNT).size() > 0;
	}

	public Boolean getIsMoreHomeStories() {

		return postRepository.getMainPosts(COUNT, getFirstInt() + COUNT).size() > 0;
	}

	public List<Post> getMainPostByCategory(final Integer categoryID) {

		return postRepository.getPostByCategory(categoryID, 1, 0);
	}

	public List<Post> getMainPostList() {

		if (!mainPostList.containsKey("mainS"))
			mainPostList.put("mainS", postRepository.getMainPosts(1, 0));
		return mainPostList.get("mainS");
	}

	public String getNext() {

		if (first == null)
			return Integer.toString(COUNT + 1);
		return Integer.toString(Integer.parseInt(first) + COUNT);

	}

	public List<Post> getPostByCategory(final Integer categoryID) {

		if (!mainPostList.containsKey("category"))
			mainPostList.put("category", postRepository.getPostByCategory(categoryID, COUNT, getFirstInt()));
		return mainPostList.get("category");
	}

	public List<Post> getPosts() {

		if (!mainPostList.containsKey("post"))
			mainPostList.put("post", postRepository.getMainPosts(COUNT, getFirstInt()));
		return mainPostList.get("post");
	}

	public List<Post> getTrendingPosts() {

		if (!mainPostList.containsKey("trendingPost"))
			mainPostList.put("trendingPost", postRepository.getMainPosts(5, 0));
		return mainPostList.get("trendingPost");
	}

	public void setFirst(final String first) {

		this.first = first;
	}

}
