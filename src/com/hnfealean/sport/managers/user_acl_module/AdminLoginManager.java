package com.hnfealean.sport.managers.user_acl_module;

import com.hnfealean.sport.model.user_acl_module.Administrator;

public interface AdminLoginManager {

	public Administrator login(String adminName,String password);

}
