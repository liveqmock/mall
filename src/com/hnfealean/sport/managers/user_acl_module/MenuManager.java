package com.hnfealean.sport.managers.user_acl_module;

import java.util.List;

import com.hnfealean.sport.model.user_acl_module.MenuGroup;
import com.hnfealean.sport.model.user_acl_module.MenuItem;

public interface MenuManager {

	public void addMenuGroup(MenuGroup menuGroup);
	public MenuGroup getMenuGroup(int id);
	public List<MenuGroup> getAllMenuGroups(int adminId);
	public void updateMeuGroup(MenuGroup menuGroup);
	public void deleteMenuGroup(int id);
	public void deleteMenuGroupByIds(int[] ids);
	
	public void addMenuItem(MenuItem item);
	public MenuItem getMenuItem(int id);
	public List<MenuItem> getAllMenuItems(int groupId);
	public void updateMenuItem(MenuItem item);
	public void deleteMenuItem(int id);
	public void deleteMenuItemByIds(int[] ids);
}
