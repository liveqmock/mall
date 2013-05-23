package com.hnfealean.sport.managers.user_acl_module;

import com.hnfealean.sport.model.user_acl_module.User;

public interface UserLoginManager {
	
	public User Login(String username,String password);
}
