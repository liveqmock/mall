package com.hnfealean.sport.managers.user_acl_module;

import java.util.List;

import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.model.user_acl_module.User;
import com.hnfealean.sport.pageset.PageModel;

public interface FrontUserManager {
	public PageModel getAllOrdersByUserId(int userId);
	public PageModel getDeliveredOrdersByUserId(int userId) ;
	public List<Order> getAllOrders(int userId);
	public List<Order> getDeliveredOrders(int userId);
	public ContactInfo getContactInfoByUserId(int userId);
	public boolean updateContactInfo(ContactInfo contactInfo);
	public boolean updatePassword(String oldPassword,String newPassword,int userId);
	public User getUserById(int id);
	public User getUserByEmail(String email);
	public void updateUser(User user);
}
