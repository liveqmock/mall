package com.hnfealean.sport.managers.impl.deliver;

import java.util.List;

import com.hnfealean.sport.managers.deliver.DeliverTypeManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.deliver.DeliverType;

public class DeliverTypeManagerImpl extends CommonManager implements DeliverTypeManager {
	public List<DeliverType> getAllDeliverTypes() {
		return (List<DeliverType>)getSession().createQuery("from DeliverType").list();

	}
	public DeliverType getDeliverTypeById(int id) {
		
		return (DeliverType)getHibernateTemplate().load(DeliverType.class, id);
	}
	public void addDeliverType(DeliverType deliverType) {
		getHibernateTemplate().save(deliverType);

	}
	public boolean checkDeliverTypeExists(int id) {
		Integer temp = (Integer)getSession().createQuery("select id from DeliverType where id=?")
		.setParameter(0, id)
		.uniqueResult();
		if(temp==null)return false;
		if(temp>0&&temp==id)return true;
		return false;
	}
	public void updateDeliverType(DeliverType type) {
		getHibernateTemplate().update(type);
		
	}
	public void deleteDeliverType(int id) {
		getSession().createQuery("delete from DeliverType where id=?").setParameter(0, id).executeUpdate();
		getSession().createQuery("update GlobalDeliverModule set deliverType=null where deliverType.id=?").setParameter(0, id)
		.executeUpdate();
		getSession().createQuery("update DeliverModule set deliverType=null where deliverType.id=?").setParameter(0, id).executeUpdate();
		
	}
	
}
