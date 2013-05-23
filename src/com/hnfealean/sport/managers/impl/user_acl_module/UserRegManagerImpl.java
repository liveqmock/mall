package com.hnfealean.sport.managers.impl.user_acl_module;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.user_acl_module.UserRegManager;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.model.user_acl_module.User;
import com.hnfealean.sport.web.MD5;

public class UserRegManagerImpl extends CommonManager implements UserRegManager {

	public void addUser(User user) {
		if(user.getPassword()==null||user.getPassword().trim().length()==0){
			user.setPassword("hnfealean");
		}
		user.setPassword(MD5.MD5Encode(user.getPassword()));
		getHibernateTemplate().save(user);

	}

	public List searchUserByName(String username) {
		List user= getSession().createQuery("select u.id from User u where u.username='"+ username.trim() +"'").list();
		return user;
	}
	public void addContactInfo(int userId,ContactInfo info){
		User  user = (User)getHibernateTemplate().load(User.class, userId);
	//	Set s =user.getContactInfo();
	//	s.add(info);
		getHibernateTemplate().update(user);
	}

	public boolean checkEmailAvaliable(String email) {
		Integer num = (Integer)getSession().createQuery("select id from User where email=?").setParameter(0, email).uniqueResult();
		if(num==null)	return true;
		return false;
	}
}

/*	

public void addUser(User user){
Category c = new Category();
c.setDescription("hello kugoo");
c.setImageUrl("http://www.baidu.com");
c.setName("gggg");
getHibernateTemplate().save(c);
//MD5.MD5Encode(user.getPassword());
user.setPassword(MD5.MD5Encode(user.getPassword()));
User s = new User();
s.setEmail("hnfelaean@gmail.com");
s.setCreateDate(new Date());
s.setExpireDate(new Date());
s.setPassword("hhhh");
s.setRealName("amen");
s.setUsername("zhuzhu");
s.setVisible(true);
Session session = getSession();
//session.setFlushMode(arg0)
session.setFlushMode(FlushMode.AUTO); 
session.saveOrUpdate(user);
//getHibernateTemplate().save(user);
session.flush();
//session.close();
//User u = (User)getHibernateTemplate().load(User.class, 1);
//System.out.print(u.getEmail());
}

*/