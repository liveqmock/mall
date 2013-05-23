package com.hnfealean.sport.managers.impl.article;

import java.util.List;

import com.hnfealean.sport.managers.article.PostsManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.post.Posts;

public class PostsManagerImpl extends CommonManager implements PostsManager {

	public boolean addPosts(Posts post) {
		getHibernateTemplate().save(post);
		return false;
	}

	public boolean delPosts(int id) {
		getSession().createQuery("delete from Posts where id=?").setParameter(0, id).executeUpdate();
		return false;
	}

	public Posts loadPosts(int id) {
		getHibernateTemplate().load(Posts.class,id);
		return null;
	}

	public List searchPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updatePosts(Posts post) {
		getHibernateTemplate().update(post);
		return false;
	}

}
