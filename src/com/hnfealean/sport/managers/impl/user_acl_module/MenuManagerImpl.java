package com.hnfealean.sport.managers.impl.user_acl_module;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.user_acl_module.MenuManager;
import com.hnfealean.sport.model.user_acl_module.MenuGroup;
import com.hnfealean.sport.model.user_acl_module.MenuItem;

public class MenuManagerImpl extends CommonManager implements MenuManager {

	public void addMenuGroup(MenuGroup menuGroup) {
	getHibernateTemplate().save(menuGroup);

	}

	public void addMenuItem(MenuItem item) {
		getHibernateTemplate().save(item);

	}

	public void deleteMenuGroup(int id) {
		getSession().createQuery("update MenuGroup set admin=null where id=?")
		.setParameter(0, id).executeUpdate();
		getSession().flush();
		getSession().createQuery("update MenuItem item set item.group=null where item.group.id=?")
		.setParameter(0, id).executeUpdate();
		getSession().flush();
		getSession().createQuery("delete MenuGroup where id=?")
		.setParameter(0, id).executeUpdate();

	}

	public void deleteMenuGroupByIds(int[] ids) {
		StringBuffer idsb = new StringBuffer();
		
		for(int id:ids){
			idsb.append(id).append(",");
		}

		if(idsb.charAt(idsb.length()-1)==",".charAt(0)){
			idsb.deleteCharAt(idsb.length()-1);
		}
		getSession().createQuery("delete MenuGroup where id in (?)")
		.setParameter(0, idsb).executeUpdate();
	}

	public void deleteMenuItem(int id) {
		getSession().createQuery("delete MenuItem where id =?").setParameter(0, id).executeUpdate();

	}

	public void deleteMenuItemByIds(int[] ids) {
	StringBuffer idsb = new StringBuffer();
		
		for(int id:ids){
			idsb.append(id).append(",");
		}

		if(idsb.charAt(idsb.length()-1)==",".charAt(0)){
			idsb.deleteCharAt(idsb.length()-1);
		}
		getSession().createQuery("delete MenuItem where id in (?)")
		.setParameter(0, idsb).executeUpdate();
	}

	public List<MenuGroup> getAllMenuGroups(int adminId) {
		return getSession().createQuery("from MenuGroup g where g.admin.id=?")
		.setParameter(0, adminId).list();
		
	}

	public List<MenuItem> getAllMenuItems(int groupId) {	
		
		return getSession().createQuery("select menuItems from MenuGroup where id=?")
		.setParameter(0, groupId)
		.list();
	}

	public MenuGroup getMenuGroup(int id) {
		return (MenuGroup)getHibernateTemplate().get(MenuGroup.class, id);
		
	}

	public MenuItem getMenuItem(int id) {
		return (MenuItem)getHibernateTemplate().get(MenuItem.class, id);
	}

	public void updateMenuItem(MenuItem item) {
		getHibernateTemplate().update(item);

	}

	public void updateMeuGroup(MenuGroup menuGroup) {
		
		getHibernateTemplate().update(menuGroup);
	}

}
