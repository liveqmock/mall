package com.hnfealean.sport.managers.impl.user_acl_module;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.user_acl_module.UserManager4Admin;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.model.user_acl_module.User;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.pageset.SystemContext;

public class UserManager4AdminImpl extends CommonManager implements
		UserManager4Admin {

	public PageModel searchAllUser(){
	//	SystemContext.setPagesize(20);
		return searchPaginated("from User u where u.visible=true",null, 
								SystemContext.getOffset(),
								SystemContext.getPagesize()
								);
	}

	public User getUserDetail(int userId) {
		return (User) getHibernateTemplate().load(User.class, userId);
	}
	public boolean updateUser(User user,int userId) {
		User u = (User) getHibernateTemplate().load(User.class, userId);
		//u.set
		return false;
		
	}

	public boolean deleteUser(int userId) {
		getSession().createQuery("update User set visible=false where id=?")
		.setParameter(0, userId).executeUpdate();
		return true;
	}

	public List<Order> getUserOrders(int userId) {
		List<Order> orders = getSession().createQuery("select orders from User where id=?").setParameter(0, userId).list();
		return orders;
	}
}
