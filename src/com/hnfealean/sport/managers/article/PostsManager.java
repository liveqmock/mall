package com.hnfealean.sport.managers.article;

import java.util.List;

import com.hnfealean.sport.model.post.Posts;

public interface PostsManager {
	
	public boolean addPosts(Posts post);
	public boolean delPosts(int id);
	public Posts loadPosts(int id);
	public List searchPosts();
	public boolean updatePosts(Posts post);
}
