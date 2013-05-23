package com.hnfealean.sport.managers.impl.user_acl_module;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.user_acl_module.AdminLoginManager;
import com.hnfealean.sport.model.user_acl_module.Administrator;
import com.hnfealean.sport.web.MD5;

public class AdminLoginManagerImpl extends CommonManager implements AdminLoginManager{

	public Administrator login(String adminName,String password) {

		Administrator admin = (Administrator) getSession().createQuery("from Administrator admin where admin.name=? and admin.password=?")
		.setParameter(0, adminName.trim())
		.setParameter(1, MD5.MD5Encode(password.trim())).uniqueResult();
		if(admin!=null){
			return admin;
		}
		return null;
	}

}
