package com.hnfealean.sport.managers.impl.global;

import org.hibernate.Query;

import com.hnfealean.sport.managers.global.CheckUserNameExistsManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;


public class CheckUserNameExistsImpl extends CommonManager implements CheckUserNameExistsManager  {
	
	
	public boolean checkUserNameExists(String username) {
		Query query = getSession().createQuery("from User user where user.name='" + username + "'");
		if(query.list()!= null){
			return true;
		}
		return false;
	} 
}
