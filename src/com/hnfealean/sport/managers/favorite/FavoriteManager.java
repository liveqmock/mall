package com.hnfealean.sport.managers.favorite;

import java.util.List;

import com.hnfealean.sport.model.user_acl_module.Favorite;
import com.hnfealean.sport.model.user_acl_module.FavoriteItem;

public interface FavoriteManager {

	public void addFavorite(Favorite favorite);
	
	public void addFavoriteItem(FavoriteItem item);
	
	public void deleteFavorite(int id);
	
	public void deleteFavoriteItem(int id);
	
	public void updateFavorite(Favorite favorite);
	
	public void updateFavoriteItem(FavoriteItem item);
	
	public void deleteFavoriteItems(String ids);
	
	public Favorite getFavoriteByUserId(int id);
	
	public FavoriteItem getFavoriteItem(int id);
	
	public List<FavoriteItem> getFavoriteItemsByUserId(int userId);
	
}
