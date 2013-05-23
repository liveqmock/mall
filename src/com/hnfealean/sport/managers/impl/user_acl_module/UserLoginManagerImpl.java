package com.hnfealean.sport.managers.impl.user_acl_module;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.user_acl_module.UserLoginManager;
import com.hnfealean.sport.model.user_acl_module.User;
import com.hnfealean.sport.web.MD5;

public class UserLoginManagerImpl extends CommonManager implements UserLoginManager {

	public User Login(String email,String password){
		return (User)getSession().createQuery("select new User(id,username,email) from User u where u.email=? and password = ?")
			.setParameter(0, email)
			.setParameter(1, MD5.MD5Encode(password))
			.uniqueResult();
	}

}
