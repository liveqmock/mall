package com.hnfealean.sport.managers.impl.user_acl_module;

import java.util.List;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.user_acl_module.FrontUserManager;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.model.user_acl_module.User;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.MD5;

public class FrontUserManagerImpl extends CommonManager implements
		FrontUserManager {

	public List<Order> getAllOrders(int userId) {
		return	getSession().createQuery("from Order order where order.user.id=?")
					.setParameter(0, userId)
					.list();
		
	}

	public List<Order> getDeliveredOrders(int userId) {
		return	getSession().createQuery("from Order order where order.orderState=? and order.user.id=?")
		.setParameter(0, ConstantString.ORDER_DELIVERED)
		.setParameter(1, userId)
		.list();

	}
	public PageModel getAllOrdersByUserId(int userId) {
		
		return searchPaginated("from Order order where order.user.id=?",userId);	
	}
	public PageModel getDeliveredOrdersByUserId(int userId) {
		//Object[] objs = new Object[](2);
		return searchPaginated("from Order order where order.orderState=? and order.user.id=?",
				new Object[]{ConstantString.ORDER_DELIVERED,userId});	
	}

	public ContactInfo getContactInfoByUserId(int userId) {
		return (ContactInfo) getSession().createQuery("select user.contactInfo from User user where user.id=?")
					.setParameter(0, userId)
					.uniqueResult();
	}

	public boolean updateContactInfo(ContactInfo contactInfo) {
		getHibernateTemplate().update(contactInfo);
		return true;
	}

	public boolean updatePassword(String oldPassword,String newPassword,int userId) {
		if(
				getSession().createQuery("update User set password =? where id=? and password=?")
		.setParameter(0, MD5.MD5Encode(newPassword))
		.setParameter(1, userId)
		.setParameter(2, MD5.MD5Encode(oldPassword))
		.executeUpdate()>0){
			return true;
		}
		return false;
	}

	public User getUserById(int id) {
		return(User)getHibernateTemplate().get(User.class, id);
		
	}

	public User getUserByEmail(String email) {
		return (User)getSession().createQuery("from User where email=?")
		.setParameter(0, email)
		.setMaxResults(1)
		.uniqueResult();

	}

	public void updateUser(User user) {
		getHibernateTemplate().update(user);
		
	}

}
