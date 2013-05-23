package com.hnfealean.sport.managers.impl.user_acl_module;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.user_acl_module.AdministratorInitManager;
import com.hnfealean.sport.model.user_acl_module.Administrator;
import com.hnfealean.sport.web.MD5;

public class AdministratorInitManagerImpl extends CommonManager implements
		AdministratorInitManager {

	public boolean getAdministrator() {

		List id = getSession().createQuery("from Administrator").list();
		if(id.size()>0){
			return true;
		}
		return false;
	}

	public boolean addAdministrator() {
		
		Administrator admin = new Administrator();
		admin.setName("angel");
		admin.setPassword(MD5.MD5Encode("angelinthebox"));
		getSession().save(admin);
		return true;
	}

}
