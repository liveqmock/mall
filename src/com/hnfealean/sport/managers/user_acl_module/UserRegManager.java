package com.hnfealean.sport.managers.user_acl_module;

import java.util.List;

import com.hnfealean.sport.model.user_acl_module.User;

public interface UserRegManager {

	public void addUser(User user);
	public List searchUserByName(String username) ;
	public boolean checkEmailAvaliable(String email);
}
