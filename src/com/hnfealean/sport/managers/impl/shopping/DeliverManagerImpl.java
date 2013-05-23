package com.hnfealean.sport.managers.impl.shopping;


import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.shopping.DeliverManager;
import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.model.user_acl_module.User;

public class DeliverManagerImpl extends CommonManager implements DeliverManager {

	public ContactInfo addContactInfo(ContactInfo contactInfo,int userId) {
		User u = new User();
		u = (User) getHibernateTemplate().load(User.class, userId);
		//contactInfo.setUser(u) ;
		getHibernateTemplate().save(contactInfo);
		//getHibernateTemplate().load(Product.class, id);
		getHibernateTemplate().update(u);
		return contactInfo;
	}
	
	public User loadUser(int userId) {
		return	(User) getHibernateTemplate().load("User.class", userId);

	}
	public User loadUserByEmail(String email) {
		
		return (User)getSession().createQuery("select new User(id,username,email) from User user where user.email=?").setParameter(0, email).uniqueResult();
	}

	public boolean checkCompletedContactInfo(int userId){
		Boolean completed = (Boolean)getSession().createQuery("select user.contactInfo.completed from User user where user.id=?")
							.setParameter(0, userId).uniqueResult();
		if(completed==null){
			return false;
		}
		return completed;
		
	}
	public boolean updateUserContactInfo(ContactInfo completeContactInfo,int userId) {
		ContactInfo contactInfo = (ContactInfo)getSession().createQuery("select u.contactInfo from User u where u.id=?").setParameter(0, userId).uniqueResult();
		contactInfo.setCity(completeContactInfo.getCity());
		contactInfo.setCountry(completeContactInfo.getCountry());
		contactInfo.setCounty(completeContactInfo.getCounty());
		contactInfo.setGender(completeContactInfo.getGender());
		contactInfo.setMobile(completeContactInfo.getMobile());
		contactInfo.setName(completeContactInfo.getName());
		contactInfo.setPhone(completeContactInfo.getPhone());
		contactInfo.setPostCode(completeContactInfo.getPostCode());
		contactInfo.setState(completeContactInfo.getState());
		contactInfo.setStreet_address(completeContactInfo.getStreet_address());
		contactInfo.setCompleted(true);
		getHibernateTemplate().update(contactInfo);
		System.out.println("contactInfo.getId()"+contactInfo.getId());
		return true;
	}
	public List<DeliverInfo> getUserDiliverInfos(int userId) {
		 return (List<DeliverInfo>)getSession().createQuery("select distinct deliverInfo from Order o where o.user.id=?")
		 .setMaxResults(10)
		 .setParameter(0, userId).list();
	}

	public DeliverInfo checkDeliverInfoBelongsToUser(int deliverInfoId,int userId) {
		DeliverInfo info = (DeliverInfo)getSession().createQuery("select info from DeliverInfo info,Order order where info.id=? and order.user.id=? and order.deliverInfo.id=info.id")
					.setParameter(0, deliverInfoId)
					.setParameter(1, userId)
					.uniqueResult();
//		DeliverInfo info = (DeliverInfo)getSession().createQuery(
//		"select info from DeliverInfo info where info.id=(select order.deliverInfo.id from Order order where order.user.id=? and order.deliverInfo.id=?)")
//		.setParameter(0, userId)
//		.setParameter(1, deliverInfoId)
//		.uniqueResult();	
		return info;
	}

	public void addDeliverInfo(DeliverInfo info) {
		getHibernateTemplate().save(info);
		//return null;
	}
}
