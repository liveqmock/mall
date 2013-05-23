package com.hnfealean.sport.managers.impl.payment;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.payment.PaypalManager;
import com.hnfealean.sport.model.payment.paypal.Paypal;
import com.hnfealean.sport.pageset.PageModel;

public class PaypalManagerImpl extends CommonManager implements PaypalManager {

	public boolean addPaypal(Paypal paypal) {
		getHibernateTemplate().save(paypal);
		return true;
	}

	public boolean deletePaypal(int id) {
		getSession().createQuery("delete from Paypal where id=?").setParameter(0, id).executeUpdate();
		return true;
	}

	public List<Paypal> getPaypalByIds(int[] ids) {
		StringBuffer sf = new StringBuffer();
		sf.append("(");
		for(int i=0;i<ids.length-1;i++){
			sf.append(i+",");
		}
		sf.append(")");
	
		return (List<Paypal>)getSession().createQuery("from Paypal where id in ?")
			.setParameter(0, sf.toString()).list();
	}

	public List<Paypal> searchAllPaypal() {
		return getSession().createQuery("from Paypal").list();
	}

	public PageModel searchPaypal() {
		return searchPaginated("from Paypal");
	}

	public boolean updatePaypal(Paypal paypal, int id) {
		getHibernateTemplate().update(paypal);
		return false;
	}

}
