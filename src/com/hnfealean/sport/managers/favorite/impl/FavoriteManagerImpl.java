package com.hnfealean.sport.managers.favorite.impl;

import java.util.List;

import com.hnfealean.sport.managers.favorite.FavoriteManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.user_acl_module.Favorite;
import com.hnfealean.sport.model.user_acl_module.FavoriteItem;

public class FavoriteManagerImpl extends CommonManager implements FavoriteManager{

	public void addFavorite(Favorite favorite) {
		getHibernateTemplate().save(favorite);
	}

	public void addFavoriteItem(FavoriteItem item) {
		getHibernateTemplate().save(item);
	}

	public void deleteFavorite(int id) {
		getSession().createQuery("update Favorite set user=null,items=null where id=?")
					.setParameter(0, id).executeUpdate();
	}

	public void deleteFavoriteItem(int id) {
		getSession().createQuery("delete from FavoriteItem where id=?")
		.setParameter(0, id).executeUpdate();
	}

	public void deleteFavoriteItems(String ids) {
		getSession().createQuery("delete from FavoriteItem where id in(?)")
		.setParameter(0, ids).executeUpdate();
	}

	public Favorite getFavoriteByUserId(int id) {
		return (Favorite)getSession().createQuery("from Favorite where user.id=?")
		.setParameter(0, id).setMaxResults(1).uniqueResult();
	}

	public FavoriteItem getFavoriteItem(int id) {
		return (FavoriteItem)getHibernateTemplate().get(FavoriteItem.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<FavoriteItem> getFavoriteItemsByUserId(int userId) {
		return (List<FavoriteItem>)getSession().createQuery("select favorite.items from Favorite favorite where favorite.user.id=?")
					.setParameter(0, userId).list();
	}

	public void updateFavorite(Favorite favorite) {
		getHibernateTemplate().update(favorite);
	}

	public void updateFavoriteItem(FavoriteItem item) {
		getHibernateTemplate().update(item);
	}

}
