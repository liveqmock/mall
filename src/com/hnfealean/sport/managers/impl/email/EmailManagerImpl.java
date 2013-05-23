package com.hnfealean.sport.managers.impl.email;

import java.util.List;

import com.hnfealean.sport.managers.email.EmailManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.email.Email;

public class EmailManagerImpl extends CommonManager implements EmailManager {

	public boolean addEmail(Email email) {
		getHibernateTemplate().save(email);
		return  true;
	}

	public boolean deleteEmail(int id) {
		getSession().createQuery("delete Email where id=?").setParameter(0, id).executeUpdate();
		return true;
	}

	public boolean deleteEmails(String ids) {
		getSession().createQuery("delete Email where id in (?)").setParameter(0, ids).executeUpdate();
		return true;
	}

	public Email getEmail(int id) {
		return (Email)getHibernateTemplate().get(Email.class, id);
	}

	public List<Email> getEmails(String ids) {
		return (List<Email>)getSession().createQuery("from Email where id in (?)").setParameter(0, ids).list();
	}

	public boolean updateEmail(Email email) {
		getHibernateTemplate().update(email);
		return true;
	}

	public List<Email> getAllEmails() {
		return (List<Email>)getSession().createQuery("from Email").list();
	}

	public int getDefaultEmail() {
		Integer id = (Integer)getSession().createQuery("select id from Email where isDefault=true order by id asc")
		.uniqueResult();
		if(id==null){
			Integer tempId = (Integer)getSession().createQuery("select max(id) from Email").uniqueResult();
			return tempId;
		}
		return id;
	}

}
