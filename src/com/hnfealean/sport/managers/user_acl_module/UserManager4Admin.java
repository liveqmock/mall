package com.hnfealean.sport.managers.user_acl_module;


import java.util.List;

import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.model.user_acl_module.User;
import com.hnfealean.sport.pageset.PageModel;

public interface UserManager4Admin {

public PageModel searchAllUser();

public User getUserDetail(int userId);
public boolean updateUser(User user,int userId) ;
public boolean deleteUser(int userId) ;
public List<Order> getUserOrders(int userId) ;

}
