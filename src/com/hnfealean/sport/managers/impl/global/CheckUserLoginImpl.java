package com.hnfealean.sport.managers.impl.global;


import org.hibernate.Query;

import com.hnfealean.sport.managers.global.CheckUserLoginManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;

/**
 * 检测用户登录
 * @author Administrator
 *
 */
public class CheckUserLoginImpl extends CommonManager implements
		CheckUserLoginManager {

	/**
	 * 此处的username和password需要先用正则过滤一下
	 */
	public boolean checkLogin(String username, String password) {
		

		Query query = getSession().createQuery(" User user where user.name='" + username + "' and password='" + password + "'");
		if(query.list() != null){
			return true;
		}
		return false;
		
	}

}
