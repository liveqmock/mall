package com.hnfealean.sport.managers.impl.shopping;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.shopping.PaymentMethodManager;
import com.hnfealean.sport.model.shopping.PaymentMethod;

public class PaymentMethodManagerImpl extends CommonManager implements PaymentMethodManager{

	public void addPaymentMethod(PaymentMethod paymentMethod) {
		getHibernateTemplate().save(paymentMethod);
		
	}

	public void delPaymentMethod(int id) {
		getSession().createQuery("delete from PaymentMethod where id=?")
		.setParameter(0, id).executeUpdate();
		
	}

	public List<PaymentMethod> getAllPaymentMethods() {
		return (List<PaymentMethod>)getSession().createQuery("from PaymentMethod order by orderNo asc")
												.list();
		
	}

	public void updatePaymentMethod(PaymentMethod paymentMethod) {
		getHibernateTemplate().update(paymentMethod);
		
		
	}

	public void updatePaymentMethodEnable(int id, boolean enable) {
		getSession().createQuery("update PaymentMethod set enable=? where id=?")
					.setParameter(0, enable)
					.setParameter(1, id)
					.executeUpdate();
		
	}

	public PaymentMethod getPaymentMethod(int id) {
		return (PaymentMethod)getHibernateTemplate().get(PaymentMethod.class, id);
		
	}


}
